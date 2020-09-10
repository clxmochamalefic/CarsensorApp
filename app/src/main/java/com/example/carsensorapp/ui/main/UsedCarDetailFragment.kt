package com.example.carsensorapp.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.carsensorapp.R
import com.example.carsensorapp.databinding.UsedCarDetailFragmentBinding

class UsedCarDetailFragment : Fragment() {

    companion object {
        const val KEY = "usedCarId"
        fun newInstance() = UsedCarDetailFragment()
        fun forUsedCar(usedCarId: String) = UsedCarDetailFragment().apply {
            arguments = Bundle().apply {
                putString(KEY, usedCarId)
            }
        }
    }

    private val usedCarId by lazy {
        requireNotNull(
            arguments?.getString(KEY)
        ) {

        }
    }

    private val viewModel by lazy {
//        ViewModelProvider(
//            this,
//            UsedCarDetailViewModel.Factory(requireActivity().application, usedCarId)
//        ).get(UsedCarDetailViewModel::class.java)
    }

    private lateinit var binding: UsedCarDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.used_car_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(UsedCarDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}