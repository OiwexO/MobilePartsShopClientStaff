package com.iwex.mobilepartsshopstaff.presentation.fragment.management.manufacturers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.iwex.mobilepartsshopstaff.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddManufacturerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddManufacturerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var editTextManufacturerName: EditText
    private lateinit var btnSelectManufacturerLogo: Button
    private lateinit var btnSaveManufacturer: Button
    private lateinit var btnCancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_manufacturer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        setClickListeners()
    }

    private fun initViews(view: View) {
        editTextManufacturerName = view.findViewById(R.id.editTextManufacturerName)
        btnSelectManufacturerLogo = view.findViewById(R.id.btnSelectManufacturerLogo)
        btnSaveManufacturer = view.findViewById(R.id.btnSaveManufacturer)
        btnCancel = view.findViewById(R.id.btnCancel)
    }

    private fun setClickListeners() {
        btnSelectManufacturerLogo.setOnClickListener {

        }
        btnSaveManufacturer.setOnClickListener {
            Toast.makeText(requireContext(), "Saved", Toast.LENGTH_SHORT).show()
            navigateToManageManufacturersFragment()
        }
        btnCancel.setOnClickListener {
            navigateToManageManufacturersFragment()
        }
    }

    private fun navigateToManageManufacturersFragment() {
        findNavController().navigate(R.id.action_addManufacturerFragment_to_manageManufacturersFragment)
    }

    private fun addManufacturer() {
        val name = editTextManufacturerName.text.toString()
//        val logo
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ManageManufacturersFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddManufacturerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}