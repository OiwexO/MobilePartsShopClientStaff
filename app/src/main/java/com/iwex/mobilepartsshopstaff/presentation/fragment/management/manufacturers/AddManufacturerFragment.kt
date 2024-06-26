package com.iwex.mobilepartsshopstaff.presentation.fragment.management.manufacturers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.signature.ObjectKey
import com.iwex.mobilepartsshopstaff.R
import com.iwex.mobilepartsshopstaff.domain.entity.part.manufacturer.Manufacturer
import com.iwex.mobilepartsshopstaff.domain.entity.part.manufacturer.ManufacturerRequest
import com.iwex.mobilepartsshopstaff.presentation.fragment.ImagePickerFragment
import com.iwex.mobilepartsshopstaff.presentation.viewmodel.management.manufacturers.AddManufacturerViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

//TODO rewrite error handling and form state check
@AndroidEntryPoint
class AddManufacturerFragment : ImagePickerFragment() {

    private val args by navArgs<AddManufacturerFragmentArgs>()

    private val viewModel: AddManufacturerViewModel by viewModels()

    private var selectedImageFile: File? = null

    private lateinit var editTextManufacturerName: EditText
    private lateinit var btnSelectManufacturerLogo: Button
    private lateinit var imageViewManufacturerLogoPreview: ImageView
    private lateinit var btnSaveManufacturer: Button
    private lateinit var btnCancel: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_manufacturer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        setClickListeners()
        val manufacturer = args.manufacturer
        if (manufacturer != null) {
            setManufacturerData(manufacturer)
        }
        observeViewModel()
    }

    private fun initViews(view: View) {
        editTextManufacturerName = view.findViewById(R.id.editTextManufacturerName)
        btnSelectManufacturerLogo = view.findViewById(R.id.btnSelectManufacturerLogo)
        imageViewManufacturerLogoPreview = view.findViewById(R.id.imageViewManufacturerLogoPreview)
        btnSaveManufacturer = view.findViewById(R.id.btnSaveManufacturer)
        btnCancel = view.findViewById(R.id.btnCancel)
        progressBar = view.findViewById(R.id.progressBarAddManufacturerFragment)
    }

    private fun setClickListeners() {
        btnSelectManufacturerLogo.setOnClickListener {
            checkPermissionAndPickImage()
        }
        btnSaveManufacturer.setOnClickListener {
            saveManufacturer()
        }
        btnCancel.setOnClickListener {
            navigateToManageManufacturersFragment()
        }
    }

    private fun saveManufacturer() {
        val name = editTextManufacturerName.text.toString()
        val manufacturer = args.manufacturer
        val manufacturerRequest = ManufacturerRequest(name, selectedImageFile)
        if (manufacturer != null) {
            viewModel.updateManufacturer(manufacturer.id, manufacturerRequest)
        } else {
            viewModel.createManufacturer(manufacturerRequest)
        }
    }

    private fun navigateToManageManufacturersFragment() {
        findNavController().navigate(R.id.action_addManufacturerFragment_to_manageManufacturersFragment)
    }

    private fun setManufacturerData(manufacturer: Manufacturer) {
        editTextManufacturerName.setText(manufacturer.name)
        Glide.with(requireContext())
            .load(manufacturer.logoUrl)
            .into(imageViewManufacturerLogoPreview)
    }

    private fun observeViewModel() {
        viewModel.addManufacturerFormState.observe(viewLifecycleOwner) { state ->
            if (!state.isDataValid) {
                state.nameError?.let { showError(it, editTextManufacturerName) }
                state.logoError?.let { showError(it) }
            }
        }
        viewModel.onSuccess.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), R.string.saved, Toast.LENGTH_SHORT).show()
            navigateToManageManufacturersFragment()
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            switchProgressBarVisibility(it)
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }
    }

    private fun showError(stringId: Int, editText: EditText? = null) {
        if (editText == null) {
            Toast.makeText(requireContext(), stringId, Toast.LENGTH_LONG).show()
            return
        }
        editText.error = getString(stringId)
    }

    private fun switchProgressBarVisibility(isVisible: Boolean) {
        progressBar.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    override fun onImagePicked(imageFile: File) {
        selectedImageFile = imageFile
        initManufacturerLogoPreview(imageFile)
    }

    private fun initManufacturerLogoPreview(imageFile: File) {
        Glide.with(requireContext())
            .load(imageFile)
            .signature(ObjectKey(imageFile.lastModified()))
            .into(imageViewManufacturerLogoPreview)
    }
}