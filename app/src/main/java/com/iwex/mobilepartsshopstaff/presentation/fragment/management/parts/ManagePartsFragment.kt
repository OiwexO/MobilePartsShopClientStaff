package com.iwex.mobilepartsshopstaff.presentation.fragment.management.parts

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

    private fun observeViewModel() {
        viewModel.parts.observe(viewLifecycleOwner) { parts ->
            partsListAdapter.submitList(parts)
        }
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            switchProgressBarVisibility(isLoading)
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            Log.e(TAG, errorMessage)
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
        }
        viewModel.getAllParts()
    }

    private fun switchProgressBarVisibility(isVisible: Boolean) {
        progressBar.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    private fun initViews(view: View) {
        recyclerViewParts = view.findViewById(R.id.recyclerViewParts)
        btnAddPart = view.findViewById(R.id.btnAddPart)
        btnBack = view.findViewById(R.id.btnBack)
        progressBar = view.findViewById(R.id.progressBarManagePartsFragment)
    }

    private fun setupRecyclerView() {
        partsListAdapter = PartsListAdapter()
        partsListAdapter.getLocalizedPriceString = {
            getString(R.string.price_placeholder, it.toString())
        }
        partsListAdapter.isUkrainianLocale = LocalizationHelper.isUkrainianLocale(resources)
        recyclerViewParts.adapter = partsListAdapter
        setOnEditPartClickListener()
        setOnDeletePartClickListener()
    }

    private fun setOnEditPartClickListener() {
        partsListAdapter.onEditPartClickListener = {
            navigateToAddPartFragment(it)
        }
    }

    private fun setOnDeletePartClickListener() {
        partsListAdapter.onDeletePartClickListener = {
            viewModel.deletePartById(it.id)
        }
    }

    private fun setClickListeners() {
        btnAddPart.setOnClickListener {
            navigateToAddPartFragment()
        }
        btnBack.setOnClickListener {
            navigateToManagementFragment()
        }
    }

    private fun navigateToAddPartFragment(part: Part? = null) {
        findNavController().navigate(
            ManagePartsFragmentDirections
                .actionManagePartsFragmentToAddPartFragment(part)
        )
    }

    private fun navigateToManagementFragment() {
        findNavController().navigate(R.id.action_managePartsFragment_to_managementFragment)
    }

    companion object {

        private const val TAG = "ManagePartsFr"
    }
}