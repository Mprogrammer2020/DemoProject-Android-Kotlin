package com.exa.mvvmdemo.ui.view.activity

import android.app.Activity
import com.exa.mvvmdemo.R
import com.exa.mvvmdemo.base.BaseActivity
import com.exa.mvvmdemo.databinding.ActivityMainContainerBinding
import com.exa.mvvmdemo.ui.view.fragments.login.LoginFragment

class MainContainerActivity : BaseActivity<ActivityMainContainerBinding>() {

    override fun setContainerLayout(): Int=R.id.flContainer

    override fun getLayoutId(): Int = R.layout.activity_main_container

    override fun getCurrentActivity(): Activity = this

    override fun onResume() {
        super.onResume()
        addFragment(LoginFragment())
    }
}