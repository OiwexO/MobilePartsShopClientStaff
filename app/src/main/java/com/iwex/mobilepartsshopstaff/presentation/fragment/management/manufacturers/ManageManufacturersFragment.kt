package com.iwex.mobilepartsshopstaff.presentation.fragment.management.manufacturers

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.iwex.mobilepartsshopstaff.R
import com.iwex.mobilepartsshopstaff.domain.entity.part.manufacturer.Manufacturer
import com.iwex.mobilepartsshopstaff.presentation.viewmodel.management.manufacturers.ManageManufacturersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ManageManufacturersFragment : Fragment() {

    private val viewModel: ManageManufacturersViewModel by viewModels()

    private lateinit var manufacturersListAdapter: ManufacturersListAdapter
    private lateinit var recyclerViewManufacturers: RecyclerView
    private lateinit var btnAddManufacturer: Button
    private lateinit var btnBack: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_manage_manufacturers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        setupRecyclerView()
        setClickListeners()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.manufacturers.observe(viewLifecycleOwner) { manufacturers ->
            manufacturersListAdapter.submitList(manufacturers)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                showProgressBar()
            } else {
                hideProgressBar()
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            Log.e(TAG, errorMessage)
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
        }
        viewModel.getAllManufacturers()
    }

    private fun showProgressBar() {
        // Show progress bar
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        // Hide progress bar
        progressBar.visibility = View.GONE
    }

    private fun initViews(view: View) {
        recyclerViewManufacturers = view.findViewById(R.id.recyclerViewManufacturers)
        btnAddManufacturer = view.findViewById(R.id.btnAddManufacturer)
        btnBack = view.findViewById(R.id.btnBack)
        progressBar = view.findViewById(R.id.progressBarManageManufacturersFragment)
    }

    private fun setupRecyclerView() {
        manufacturersListAdapter = ManufacturersListAdapter()
        recyclerViewManufacturers.adapter = manufacturersListAdapter
        setOnEditManufacturerClickListener()
        setOnDeleteManufacturerClickListener()
    }

    private fun setOnEditManufacturerClickListener() {
        manufacturersListAdapter.onEditManufacturerClickListener = {
            navigateToAddManufacturerFragment(it)
        }
    }
    private fun setOnDeleteManufacturerClickListener() {
        manufacturersListAdapter.onDeleteManufacturerClickListener = {
            viewModel.deleteManufacturerById(it.id)
        }
    }

    private fun setClickListeners() {
        btnAddManufacturer.setOnClickListener {
            navigateToAddManufacturerFragment()
        }
        btnBack.setOnClickListener {
            navigateToManagementFragment()
        }
    }

    private fun navigateToAddManufacturerFragment(manufacturer: Manufacturer? = null) {
        findNavController().navigate(ManageManufacturersFragmentDirections
            .actionManageManufacturersFragmentToAddManufacturerFragment(manufacturer))
    }

    private fun navigateToManagementFragment() {
        findNavController().navigate(R.id.action_manageManufacturersFragment_to_managementFragment)
    }

    companion object {

        private const val TAG = "ManageManufacturersFr"
    }
}