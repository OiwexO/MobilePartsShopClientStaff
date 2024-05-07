package com.iwex.mobilepartsshopstaff.presentation.fragment.management.manufacturers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.signature.ObjectKey
import com.iwex.mobilepartsshopstaff.R
import com.iwex.mobilepartsshopstaff.domain.entity.part.manufacturer.Manufacturer
import com.iwex.mobilepartsshopstaff.presentation.fragment.ImagePickerFragment
import com.iwex.mobilepartsshopstaff.presentation.viewmodel.management.manufacturers.AddManufacturerViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class AddManufacturerFragment : ImagePickerFragment() {

    private val args by navArgs<AddManufacturerFragmentArgs>()

    private val viewModel: AddManufacturerViewModel by viewModels()

    private lateinit var editTextManufacturerName: EditText
    private lateinit var btnSelectManufacturerLogo: Button
    private lateinit var imageViewManufacturerLogoPreview: ImageView
    private lateinit var btnSaveManufacturer: Button
    private lateinit var btnCancel: Button

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

    private fun observeViewModel() {
        viewModel.isSuccess.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) {
                Toast.makeText(requireContext(), "Saved", Toast.LENGTH_SHORT).show()
                navigateToManageManufacturersFragment()
            }
        }
    }

    private fun initViews(view: View) {
        editTextManufacturerName = view.findViewById(R.id.editTextManufacturerName)
        btnSelectManufacturerLogo = view.findViewById(R.id.btnSelectManufacturerLogo)
        imageViewManufacturerLogoPreview = view.findViewById(R.id.imageViewManufacturerLogoPreview)
        btnSaveManufacturer = view.findViewById(R.id.btnSaveManufacturer)
        btnCancel = view.findViewById(R.id.btnCancel)
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

    private fun navigateToManageManufacturersFragment() {
        findNavController().navigate(R.id.action_addManufacturerFragment_to_manageManufacturersFragment)
    }

    private fun setManufacturerData(manufacturer: Manufacturer) {
        editTextManufacturerName.setText(manufacturer.name)
        Glide.with(requireContext())
            .load(manufacturer.logoUrl)
            .into(imageViewManufacturerLogoPreview)
    }

    override fun onImagePicked(imageFile: File) {
        viewModel.setSelectedImageFile(imageFile)
        initManufacturerLogoPreview(imageFile)
    }

    private fun initManufacturerLogoPreview(imageFile: File) {
        Glide.with(requireContext())
            .load(imageFile)
            .signature(ObjectKey(imageFile.lastModified()))
            .into(imageViewManufacturerLogoPreview)
    }

    private fun saveManufacturer() {
        val name = editTextManufacturerName.text.toString()
        viewModel.createManufacturer(name)
    }
}