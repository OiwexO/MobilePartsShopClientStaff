package com.iwex.mobilepartsshopstaff.presentation.fragment.management.parts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.signature.ObjectKey
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.iwex.mobilepartsshopstaff.R
import com.iwex.mobilepartsshopstaff.domain.entity.part.Part
import com.iwex.mobilepartsshopstaff.domain.entity.part.PartRequest
import com.iwex.mobilepartsshopstaff.domain.entity.part.device_type.DeviceType
import com.iwex.mobilepartsshopstaff.domain.entity.part.manufacturer.Manufacturer
import com.iwex.mobilepartsshopstaff.domain.entity.part.part_type.PartType
import com.iwex.mobilepartsshopstaff.presentation.fragment.ImagePickerFragment
import com.iwex.mobilepartsshopstaff.presentation.fragment.management.parts.adapters.DeviceTypeSpinnerAdapter
import com.iwex.mobilepartsshopstaff.presentation.fragment.management.parts.adapters.ManufacturerSpinnerAdapter
import com.iwex.mobilepartsshopstaff.presentation.fragment.management.parts.adapters.PartTypeSpinnerAdapter
import com.iwex.mobilepartsshopstaff.presentation.utils.LocalizationHelper
import com.iwex.mobilepartsshopstaff.presentation.viewmodel.management.parts.AddPartViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class AddPartFragment : ImagePickerFragment() {

    private val args by navArgs<AddPartFragmentArgs>()

    private val viewModel: AddPartViewModel by viewModels()

    private var selectedImageFile: File? = null

    private lateinit var tilName: TextInputLayout
    private lateinit var editTextName: TextInputEditText
    private lateinit var tilPrice: TextInputLayout
    private lateinit var editTextPrice: TextInputEditText
    private lateinit var tilQuantity: TextInputLayout
    private lateinit var editTextQuantity: TextInputEditText
    private lateinit var tilDeviceModels: TextInputLayout
    private lateinit var editTextDeviceModels: TextInputEditText
    private lateinit var tilSpecifications: TextInputLayout
    private lateinit var editTextSpecifications: TextInputEditText
    private lateinit var spinnerManufacturer: Spinner
    private lateinit var spinnerDeviceType: Spinner
    private lateinit var spinnerPartType: Spinner
    private lateinit var btnSelectPartImage: Button
    private lateinit var imageViewPartImagePreview: ImageView
    private lateinit var btnSavePart: Button
    private lateinit var btnCancel: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_part, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        setClickListeners()
        val part = args.part
        if (part != null) {
            setPartData(part)
        }
        observeViewModel()
    }

    private fun initViews(view: View) {
        tilName = view.findViewById(R.id.tilName)
        editTextName = view.findViewById(R.id.editTextName)
        tilPrice = view.findViewById(R.id.tilPrice)
        editTextPrice = view.findViewById(R.id.editTextPrice)
        tilQuantity = view.findViewById(R.id.tilQuantity)
        editTextQuantity = view.findViewById(R.id.editTextQuantity)
        tilDeviceModels = view.findViewById(R.id.tilDeviceModels)
        editTextDeviceModels = view.findViewById(R.id.editTextDeviceModels)
        tilSpecifications = view.findViewById(R.id.tilSpecifications)
        editTextSpecifications = view.findViewById(R.id.editTextSpecifications)
        spinnerManufacturer = view.findViewById(R.id.spinnerManufacturer)
        spinnerDeviceType = view.findViewById(R.id.spinnerDeviceType)
        spinnerPartType = view.findViewById(R.id.spinnerPartType)
        btnSelectPartImage = view.findViewById(R.id.btnSelectPartImage)
        imageViewPartImagePreview = view.findViewById(R.id.imageViewPartImagePreview)
        btnSavePart = view.findViewById(R.id.btnSavePart)
        btnCancel = view.findViewById(R.id.btnCancel)
        progressBar = view.findViewById(R.id.progressBarAddPartFragment)
    }

    private fun setClickListeners() {
        btnSelectPartImage.setOnClickListener {
            checkPermissionAndPickImage()
        }
        btnSavePart.setOnClickListener {
            savePart()
        }
        btnCancel.setOnClickListener {
            navigateToManagePartsFragment()
        }
    }

    private fun savePart() {
        clearErrors()
        val name = editTextName.text.toString().trim()
        val price = editTextPrice.text.toString().toDoubleOrNull() ?: 0.0
        val quantity = editTextQuantity.text.toString().toIntOrNull() ?: 0
        val deviceModels =
            editTextDeviceModels.text.toString().split(DEVICE_MODELS_DELIMITER).map { it.trim() }
        val specifications = editTextSpecifications.text.toString().trim()
        val manufacturerId = (spinnerManufacturer.selectedItem as? Manufacturer)?.id ?: 0
        val deviceTypeId = (spinnerDeviceType.selectedItem as? DeviceType)?.id ?: 0
        val partTypeId = (spinnerPartType.selectedItem as? PartType)?.id ?: 0
        val image = selectedImageFile
        val partRequest = PartRequest(
            price = price,
            quantity = quantity,
            name = name,
            deviceModels = deviceModels,
            specifications = specifications,
            manufacturerId = manufacturerId,
            deviceTypeId = deviceTypeId,
            partTypeId = partTypeId,
            image = image
        )
        val part = args.part
        if (part == null) {
            viewModel.createPart(partRequest)
        } else {
            viewModel.updatePart(part.id, partRequest)
        }
    }

    private fun clearErrors() {
        tilName.error = null
        tilPrice.error = null
        tilQuantity.error = null
        tilDeviceModels.error = null
        tilSpecifications.error = null
    }

    private fun navigateToManagePartsFragment() {
        findNavController().navigate(R.id.action_addPartFragment_to_managePartsFragment)
    }

    private fun setPartData(part: Part) {
        editTextName.setText(part.name)
        editTextPrice.setText(part.price.toString())
        editTextQuantity.setText(part.quantity.toString())
        val deviceModels = part.deviceModels.joinToString(separator = DEVICE_MODELS_DELIMITER)
        editTextDeviceModels.setText(deviceModels)
        editTextSpecifications.setText(part.specifications)
        Glide.with(requireContext())
            .load(part.imageUrl)
            .into(imageViewPartImagePreview)
    }

    private fun observeViewModel() {
        viewModel.deviceTypes.observe(viewLifecycleOwner) {
            setupDeviceTypesSpinner(it)
        }
        viewModel.manufacturers.observe(viewLifecycleOwner) {
            setupManufacturersSpinner(it)
        }
        viewModel.partTypes.observe(viewLifecycleOwner) {
            setupPartTypesSpinner(it)
        }
        viewModel.addPartFormState.observe(viewLifecycleOwner) { state ->
            if (!state.isDataValid) {
                state.nameError?.let { showError(it, tilName) }
                state.priceError?.let { showError(it, tilPrice) }
                state.quantityError?.let { showError(it, tilQuantity) }
                state.deviceModelsError?.let { showError(it, tilDeviceModels) }
                state.specificationsError?.let { showError(it, tilSpecifications) }
                state.manufacturerError?.let { showError(it) }
                state.deviceTypeError?.let { showError(it) }
                state.partTypeError?.let { showError(it) }
                state.imageError?.let { showError(it) }
            }
        }
        viewModel.onSuccess.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), R.string.saved, Toast.LENGTH_SHORT).show()
            navigateToManagePartsFragment()
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            switchProgressBarVisibility(it)
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }
        viewModel.loadData()
    }

    private fun setupDeviceTypesSpinner(deviceTypes: List<DeviceType>) {
        spinnerDeviceType.adapter = DeviceTypeSpinnerAdapter(
            requireContext(),
            deviceTypes,
            LocalizationHelper.isUkrainianLocale(resources)
        )
    }

    private fun setupManufacturersSpinner(manufacturers: List<Manufacturer>) {
        spinnerManufacturer.adapter = ManufacturerSpinnerAdapter(requireContext(), manufacturers)
    }

    private fun setupPartTypesSpinner(partTypes: List<PartType>) {
        spinnerPartType.adapter = PartTypeSpinnerAdapter(
            requireContext(),
            partTypes,
            LocalizationHelper.isUkrainianLocale(resources)
        )
    }

    private fun showError(stringId: Int, textInputLayout: TextInputLayout? = null) {
        if (textInputLayout == null) {
            Toast.makeText(requireContext(), stringId, Toast.LENGTH_LONG).show()
            return
        }
        textInputLayout.error = getString(stringId)
    }

    private fun switchProgressBarVisibility(isVisible: Boolean) {
        progressBar.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    override fun onImagePicked(imageFile: File) {
        selectedImageFile = imageFile
        initPartImagePreview(imageFile)
    }

    private fun initPartImagePreview(imageFile: File) {
        Glide.with(requireContext())
            .load(imageFile)
            .signature(ObjectKey(imageFile.lastModified()))
            .into(imageViewPartImagePreview)
    }

    companion object {

        private const val DEVICE_MODELS_DELIMITER = ","
    }
}