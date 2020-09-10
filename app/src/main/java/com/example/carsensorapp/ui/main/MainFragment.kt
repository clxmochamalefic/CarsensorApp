package com.example.carsensorapp.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import com.example.carsensorapp.MainActivity
import com.example.carsensorapp.R
import com.example.carsensorapp.databinding.MainFragmentBinding
import com.example.carsensorapp.services.models.UsedCarModel
import com.example.carsensorapp.ui.adaptors.BrandSpinnerAdapter
import com.example.carsensorapp.ui.adaptors.UsedCarAdapter
import com.example.carsensorapp.ui.callbacks.UsedCarClickCallback
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val _viewModel: MainViewModel by viewModels()

    private lateinit var _binding: MainFragmentBinding

    private lateinit var _layoutInflater: LayoutInflater

    private val _usedCarAdapter: UsedCarAdapter = UsedCarAdapter(object : UsedCarClickCallback {
        override fun onClick(usedCar: UsedCarModel) {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED) && activity is MainActivity) {
                (activity as MainActivity).show(usedCar)
            }
        }
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        _binding.apply {
            mainRecyclerviewUsedCarList.adapter = _usedCarAdapter
            isLoading = false;
        }

        _layoutInflater = inflater

        return _binding.root
        return null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        _viewModel.brandsLiveData.observe(viewLifecycleOwner, Observer { brands ->
            brands?.let {
                val adapter = BrandSpinnerAdapter(_layoutInflater, brands)
                main__spinner_brand.adapter = adapter
            }
        })
    }

}