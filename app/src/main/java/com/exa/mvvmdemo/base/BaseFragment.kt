package com.exa.mvvmdemo.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.exa.mvvmdemo.R
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<T : ViewDataBinding?> : Fragment() {

    private var mActivity: BaseActivity<*>? = null
    private var mViewDataBinding: T? = null
    private var mRootView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mViewDataBinding == null) {
            mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
            mRootView = mViewDataBinding!!.root
        }
        return mRootView
    }

    fun getParentActivity():BaseActivity<*> = mActivity?:activity as BaseActivity<*>

    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*>) {
            mActivity = context
        }
    }

    override fun onDetach() {
        mActivity = null
        super.onDetach()
    }

    fun getViewDataBinding(): T {
        return mViewDataBinding!!
    }


    fun showSnakBar(string: String) {
        try {
            val snackBar: Snackbar =
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    string,
                    Snackbar.LENGTH_SHORT
                )

            val snackBarView = snackBar.view
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.design_default_color_error
                )
            )
            snackBar.show()
        } catch (e: java.lang.Exception) {

        }

    }

}