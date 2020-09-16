package com.example.carsensorapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.carsensorapp.MainActivity
import com.example.carsensorapp.R
import com.example.carsensorapp.databinding.MainFragmentBinding
import com.example.carsensorapp.services.models.BrandModel
import com.example.carsensorapp.services.models.CodeNamePair
import com.example.carsensorapp.services.models.PrefModel
import com.example.carsensorapp.services.models.UsedCarModel
import com.example.carsensorapp.ui.adaptors.BrandSpinnerAdapter
import com.example.carsensorapp.ui.adaptors.ColorSpinnerAdapter
import com.example.carsensorapp.ui.adaptors.PrefSpinnerAdapter
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
            mainButtonShowHide.setOnClickListener { openAndCloseSpinnerArea(it as Button, mainLinearLayoutSpinnerArea, _binding) }
            mainButtonSearch.setOnClickListener {
                _viewModel.fetchUsedCarLiveData(
                    mainEdittextUsedCarName.text.toString(),
                    (mainSpinnerBrand.selectedItem as BrandModel).code,
                    (mainSpinnerPref.selectedItem as PrefModel).code,
                    (mainSpinnerColor.selectedItem as CodeNamePair).code
                )
            }
            isLoading = true;
        }

        _layoutInflater = inflater
        _binding.isOpen = false;

        return _binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        _viewModel.carsLiveData.observe(viewLifecycleOwner, Observer { cars ->
            cars?.let {
                _binding.isLoading = false
                _usedCarAdapter.setUsedCars(cars)
            }
        })
        _viewModel.brandsLiveData.observe(viewLifecycleOwner, Observer { brands ->
            brands?.let {
                val adapter = BrandSpinnerAdapter(_layoutInflater, brands)
                main__spinner_brand.adapter = adapter
            }
        })

        _viewModel.prefLiveData.observe(viewLifecycleOwner, Observer { prefs ->
            prefs?.let {
                val adapter = PrefSpinnerAdapter(_layoutInflater, prefs)
                main__spinner_pref.adapter = adapter
            }
        })

        _viewModel.colorLiveData.observe(viewLifecycleOwner, Observer { colors ->
            colors?.let {
                val adapter = ColorSpinnerAdapter(_layoutInflater, colors)
                main__spinner_color.adapter = adapter
                _binding.run {
                    isLoading = false
                }
            }
        })

        _binding.run {
            isLoading = true
        }
    }

    private fun openAndCloseSpinnerArea(button: Button, view: View, binding: MainFragmentBinding) {
        binding.isOpen = !binding.isOpen
        val visibility = if (binding.isOpen) {
            View.VISIBLE
        } else {
            View.GONE
        }

        button.text = if (binding.isOpen) {
            getString(R.string.close)
        } else {
            getString(R.string.open)
        }

        view.visibility = visibility
    }
}