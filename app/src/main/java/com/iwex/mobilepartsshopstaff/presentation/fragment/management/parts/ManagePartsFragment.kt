package com.iwex.mobilepartsshopstaff.presentation.fragment.management.parts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.iwex.mobilepartsshopstaff.R
import com.iwex.mobilepartsshopstaff.domain.entity.part.Part
import com.iwex.mobilepartsshopstaff.presentation.fragment.management.parts.adapters.PartsListAdapter
import com.iwex.mobilepartsshopstaff.presentation.utils.LocalizationHelper
import com.iwex.mobilepartsshopstaff.presentation.viewmodel.management.parts.ManagePartsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ManagePartsFragment : Fragment() {

    private val viewModel: ManagePartsViewModel by viewModels()

    private lateinit var partsListAdapter: PartsListAdapter
    private lateinit var recyclerViewParts: RecyclerView
    private lateinit var btnAddPart: Button
    private lateinit var btnBack: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_manage_parts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        setupRecyclerView()
        setClickListeners()
        observeViewModel()
    }

    private fun initViews(view: View) {
        recyclerViewParts = view.findViewById(R.id.recyclerViewParts)
        btnAddPart = view.findViewById(R.id.btnAddPart)
        btnBack = view.findViewById(R.id.btnBack)
        progressBar = view.findViewById(R.id.progressBarManagePartsFragment)
    }

    private fun setupRecyclerView() {
        partsListAdapter = PartsListAdapter()
        partsListAdapter.isUkrainianLocale = LocalizationHelper.isUkrainianLocale(resources)
        partsListAdapter.onEditPartClickListener = {
            navigateToAddPartFragment(it)
        }
        partsListAdapter.onDeletePartClickListener = {
            viewModel.deletePartById(it.id)
        }
        recyclerViewParts.adapter = partsListAdapter
    }

    private fun navigateToAddPartFragment(part: Part? = null) {
        findNavController().navigate(
            ManagePartsFragmentDirections
                .actionManagePartsFragmentToAddPartFragment(part)
        )
    }

    private fun setClickListeners() {
        btnAddPart.setOnClickListener {
            navigateToAddPartFragment()
        }
        btnBack.setOnClickListener {
            navigateToManagementFragment()
        }
    }

    private fun navigateToManagementFragment() {
        findNavController().navigate(R.id.action_managePartsFragment_to_managementFragment)
    }

    private fun observeViewModel() {
        viewModel.parts.observe(viewLifecycleOwner) {
            partsListAdapter.submitList(it)
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            switchProgressBarVisibility(it)
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }
        viewModel.getAllParts()
    }

    private fun switchProgressBarVisibility(isVisible: Boolean) {
        progressBar.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}