package com.iwex.mobilepartsshopstaff.presentation.fragment.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.iwex.mobilepartsshopstaff.R
import com.iwex.mobilepartsshopstaff.domain.entity.authentication.AuthenticationRequest
import com.iwex.mobilepartsshopstaff.domain.entity.user.User
import com.iwex.mobilepartsshopstaff.presentation.OnLoggedInListener
import com.iwex.mobilepartsshopstaff.presentation.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()

    private lateinit var onLoggedInListener: OnLoggedInListener

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var progressBar: ProgressBar

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnLoggedInListener) {
            onLoggedInListener = context
        } else {
            throw RuntimeException("Activity ${context::class.java.canonicalName} should implement OnLoggedInListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        setListeners()
        observeViewModel()
    }

    private fun initViews(view: View) {
        usernameEditText = view.findViewById(R.id.editTextUsername)
        passwordEditText = view.findViewById(R.id.editTextPassword)
        loginButton = view.findViewById(R.id.btnLogin)
        progressBar = view.findViewById(R.id.progressBarLoginFragment)
    }

    private fun setListeners() {
        passwordEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                login()
            }
            false
        }
        loginButton.setOnClickListener {
            login()
        }
    }

    private fun observeViewModel() {
        viewModel.loginFormState.observe(viewLifecycleOwner) { state ->
            if (!state.isDataValid) {
                state.usernameError?.let {
                    usernameEditText.error = getString(it)
                }
                state.passwordError?.let {
                    passwordEditText.error = getString(it)
                }
            }
        }
        viewModel.user.observe(viewLifecycleOwner) {
            onLoginSuccessful(it)
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            switchProgressBarVisibility(it)
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            onLoginFailed(it)
        }
    }

    private fun login() {
        val username = usernameEditText.text.toString()
        val password = passwordEditText.text.toString()
        viewModel.login(AuthenticationRequest(username, password))
    }

    private fun onLoginSuccessful(user: User) {
        val welcome = getString(R.string.welcome, user.firstname)
        showToast(welcome)
        onLoggedInListener.onLoggedIn()
    }

    private fun onLoginFailed(errorMessage: String) {
        showToast(errorMessage)
    }

    private fun showToast(string: String) {
        context?.let {
            Toast.makeText(it, string, Toast.LENGTH_LONG).show()
        }
    }

    private fun switchProgressBarVisibility(isVisible: Boolean) {
        progressBar.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}