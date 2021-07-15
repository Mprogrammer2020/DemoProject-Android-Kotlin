package com.exa.mvvmdemo.ui.view.fragments.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.exa.mvvmdemo.R
import com.exa.mvvmdemo.base.BaseFragment
import com.exa.mvvmdemo.databinding.FragmentLoginBinding
import com.exa.mvvmdemo.ui.view.fragments.home.HomeScreenFragment

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val loginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewDataBinding().login.setOnClickListener {
            loginViewModel.login(
                getViewDataBinding().username.text.toString(),
                getViewDataBinding().password.text.toString()
            )
        }
        
        handleCallBackObserver()
    }

    private fun handleCallBackObserver() {
        loginViewModel.loginFormState.observe(viewLifecycleOwner, Observer {
            val loginState = it ?: return@Observer

            if (loginState.usernameError != null) {
                getViewDataBinding().username.error = getString(loginState.usernameError)
                return@Observer
            }

            if (loginState.passwordError != null) {
                getViewDataBinding().password.error = getString(loginState.passwordError)
                return@Observer
            }

            if (loginState.isDataValid) {
                getParentActivity().replaceFragment(HomeScreenFragment())
            }
        })
    }

    override fun getLayoutId(): Int = R.layout.fragment_login
}