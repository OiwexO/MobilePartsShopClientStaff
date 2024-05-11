package com.iwex.mobilepartsshopstaff.presentation.fragment.management

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.iwex.mobilepartsshopstaff.R

class ManagementFragment : Fragment() {

    private lateinit var btnManageManufacturers: Button
    private lateinit var btnManageParts: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_management, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        setClickListeners()
    }

    private fun initViews(view: View) {
        btnManageManufacturers = view.findViewById(R.id.btnManageManufacturers)
        btnManageParts = view.findViewById(R.id.btnManageParts)
    }

    private fun setClickListeners() {
        btnManageManufacturers.setOnClickListener {
            navigateToManageManufacturersFragment()
        }
        btnManageParts.setOnClickListener {
            navigateToManagePartsFragment()
        }
    }

    private fun navigateToManageManufacturersFragment() {
        findNavController().navigate(R.id.action_managementFragment_to_manageManufacturersFragment)
    }

    private fun navigateToManagePartsFragment() {
        findNavController().navigate(R.id.action_managementFragment_to_managePartsFragment)
    }
}