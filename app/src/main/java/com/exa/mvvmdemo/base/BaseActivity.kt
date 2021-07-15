package com.exa.mvvmdemo.base

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

abstract class BaseActivity<T : ViewDataBinding?> : AppCompatActivity(),

    FragmentManager.OnBackStackChangedListener {

    abstract fun setContainerLayout(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    private var mViewDataBinding: T? = null

    open fun getViewDataBinding(): T {
        return mViewDataBinding!!
    }

    fun replaceFragmentWithBackStack(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(setContainerLayout(), fragment)
            .addToBackStack(fragment::getActivity.name).commit()
    }

    fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(setContainerLayout(), fragment)
            .addToBackStack(fragment::getActivity.name).commit()
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(setContainerLayout(), fragment).commit()
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getCurrentActivity(): Activity?

    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
    }

    override fun onBackStackChanged() {
        val localFragmentManager = supportFragmentManager
        val i = localFragmentManager.backStackEntryCount
        if (i == 1 || i == 0) {
            finish()
        } else {
            Handler(Looper.getMainLooper()).postDelayed({
                localFragmentManager.popBackStack()
            }, 100)
        }
    }

}