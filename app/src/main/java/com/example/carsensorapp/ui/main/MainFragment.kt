package com.example.carsensorapp.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import com.example.carsensorapp.MainActivity
import com.example.carsensorapp.R
import com.example.carsensorapp.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val _viewModel: MainViewModel by viewModels()
    private lateinit var _binding: MainFragmentBinding
    private lateinit var _projectAdapter: ProjectAdapter

//    private val projectClickCallback = object : ProjectClickCallback {
//        override fun onClick(project: Project) {
//            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED) && activity is MainActivity) {
//                (activity as MainActivity).show(project)
//            }
//        }
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) {
        _binding = MainFragmentBinding.inflate(inflater, container, false)


        return _binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}