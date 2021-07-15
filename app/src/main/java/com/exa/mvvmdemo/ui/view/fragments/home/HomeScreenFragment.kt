package com.exa.mvvmdemo.ui.view.fragments.home

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.exa.mvvmdemo.R
import com.exa.mvvmdemo.base.BaseFragment
import com.exa.mvvmdemo.databinding.FragmentHomeScreenBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeScreenFragment : BaseFragment<FragmentHomeScreenBinding>() {

    private var param1: String? = null
    private var param2: String? = null

    private val homeScreenViewModel by lazy {
        ViewModelProvider(this).get(HomeScreenViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeScreenFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun getLayoutId(): Int = R.layout.fragment_home_screen
}