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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_part, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        setClickListeners()
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
        viewModel.isSuccess.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) {
                Toast.makeText(requireContext(), "Saved", Toast.LENGTH_SHORT).show()
                navigateToManagePartsFragment()
            }
        }
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            switchProgressBarVisibility(isLoading)
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) { stringId ->
            Toast.makeText(requireContext(), stringId, Toast.LENGTH_LONG).show()
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

    private fun navigateToManagePartsFragment() {
        findNavController().navigate(R.id.action_addPartFragment_to_managePartsFragment)
    }

    private fun switchProgressBarVisibility(isVisible: Boolean) {
        progressBar.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    private fun savePart() {
    }

    companion object {

        private const val TAG = "AddPartFr"
    }
}