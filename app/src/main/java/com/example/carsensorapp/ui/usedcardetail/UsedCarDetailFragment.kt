package com.example.carsensorapp.ui.usedcardetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.carsensorapp.R
import com.example.carsensorapp.databinding.UsedCarDetailFragmentBinding
import com.example.carsensorapp.services.models.UsedCarDetailModel

class UsedCarDetailFragment : Fragment() {

    companion object {
        const val KEY = "UsedCarDetailModel"
        fun forUsedCar(model: UsedCarDetailModel) = UsedCarDetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(KEY, model)
            }
        }
    }

    private val usedCarDetailModel by lazy {
        requireNotNull(
            arguments?.getParcelable<UsedCarDetailModel>(KEY)
        )
    }

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            UsedCarDetailViewModel.Factory(
                requireActivity().application,
                usedCarDetailModel
            )
        ).get(UsedCarDetailViewModel::class.java)
    }

    private lateinit var binding: UsedCarDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.used_car_detail_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            vm = viewModel
        }

        viewModel.usedCarLiveData.observe(viewLifecycleOwner, Observer { usedCar ->
            usedCar.let {
                viewModel.setUsedCarDetailModel(it)
            }
        })
    }
}