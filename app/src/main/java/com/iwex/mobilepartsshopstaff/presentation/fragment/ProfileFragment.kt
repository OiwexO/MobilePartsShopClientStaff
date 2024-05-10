package com.iwex.mobilepartsshopstaff.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.iwex.mobilepartsshopstaff.R
import com.iwex.mobilepartsshopstaff.presentation.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by viewModels()

    private lateinit var imageViewAvatar: ImageView
    private lateinit var textViewUserId: TextView
    private lateinit var textViewUsername: TextView
    private lateinit var textViewFullName: TextView
    private lateinit var textViewAuthority: TextView
    private lateinit var btnChangePersonalInfo: Button
    private lateinit var btnManageAssignedOrders: Button
    private lateinit var btnGenerateReport: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        setClickListeners()
        observeViewModel()
    }

    private fun initViews(view: View) {
        imageViewAvatar = view.findViewById(R.id.imageViewAvatar)
        textViewUserId = view.findViewById(R.id.textViewUserId)
        textViewUsername = view.findViewById(R.id.textViewUsername)
        textViewFullName = view.findViewById(R.id.textViewFullName)
        textViewAuthority = view.findViewById(R.id.textViewAuthority)
        btnChangePersonalInfo = view.findViewById(R.id.btnChangePersonalInfo)
        btnManageAssignedOrders = view.findViewById(R.id.btnManageAssignedOrders)
        btnGenerateReport = view.findViewById(R.id.btnGenerateReport)
        progressBar = view.findViewById(R.id.progressBarProfileFragment)
    }

    private fun setClickListeners() {
        btnChangePersonalInfo.setOnClickListener {
            navigateToPersonalInfoFragment()
        }
        btnManageAssignedOrders.setOnClickListener {
            navigateToAssignedOrdersFragment()
        }
        btnGenerateReport.setOnClickListener {
            navigateToGenerateReportFragment()
        }
    }

    private fun observeViewModel() {
        viewModel.user.observe(viewLifecycleOwner) {

        }
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
        viewModel.getUser()
    }

    private fun navigateToPersonalInfoFragment() {

    }

    private fun navigateToAssignedOrdersFragment() {

    }

    private fun navigateToGenerateReportFragment() {

    }

    companion object {
    }
}