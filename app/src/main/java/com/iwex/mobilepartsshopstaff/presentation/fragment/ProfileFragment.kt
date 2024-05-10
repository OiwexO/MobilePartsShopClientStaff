package com.iwex.mobilepartsshopstaff.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.iwex.mobilepartsshopstaff.R
import com.iwex.mobilepartsshopstaff.domain.entity.user.User
import com.iwex.mobilepartsshopstaff.domain.entity.user.UserAuthority
import com.iwex.mobilepartsshopstaff.presentation.OnLoggedOutListener
import com.iwex.mobilepartsshopstaff.presentation.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by viewModels()

    private lateinit var onLoggedOutListener: OnLoggedOutListener

    private lateinit var imageViewAvatar: ImageView
    private lateinit var textViewUserId: TextView
    private lateinit var textViewUsername: TextView
    private lateinit var textViewFullName: TextView
    private lateinit var textViewAuthority: TextView
    private lateinit var btnChangePersonalInfo: Button
    private lateinit var btnManageAssignedOrders: Button
    private lateinit var btnGenerateReport: Button
    private lateinit var btnLogout: Button
    private lateinit var progressBar: ProgressBar

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnLoggedOutListener) {
            onLoggedOutListener = context
        } else {
            throw RuntimeException("Activity ${context::class.java.canonicalName} should implement OnLoggedOutListener")
        }
    }

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
        btnLogout = view.findViewById(R.id.btnLogout)
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
        btnLogout.setOnClickListener {
            viewModel.logout()
        }
    }

    private fun observeViewModel() {
        viewModel.user.observe(viewLifecycleOwner) {
            updateUi(it)
        }
        viewModel.onLogout.observe(viewLifecycleOwner) {
            onLoggedOutListener.onLoggedOut()
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

    private fun updateUi(user: User) {
        textViewUserId.text = getString(R.string.user_id_placeholder, user.id)
        textViewUsername.text = getString(R.string.username_placeholder, user.username)
        textViewFullName.text =
            getString(R.string.full_name_placeholder, user.firstname, user.lastname)
        @StringRes val authorityResId = when (user.authority) {
            UserAuthority.STAFF -> R.string.authority_staff
            UserAuthority.ADMIN -> R.string.authority_admin
            UserAuthority.CUSTOMER -> R.string.authority_customer
        }
        textViewAuthority.text = getString(authorityResId)
        btnManageAssignedOrders.isEnabled = user.authority == UserAuthority.STAFF
        btnGenerateReport.isEnabled = user.authority == UserAuthority.ADMIN
    }
}