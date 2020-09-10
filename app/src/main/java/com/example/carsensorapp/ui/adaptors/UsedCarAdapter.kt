package com.example.carsensorapp.ui.adaptors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.carsensorapp.R
import com.example.carsensorapp.databinding.UsedCarDetailFragmentBinding
import com.example.carsensorapp.databinding.UsedCarListItemBinding
import com.example.carsensorapp.services.models.UsedCarModel
import com.example.carsensorapp.ui.callbacks.UsedCarClickCallback

class UsedCarAdapter(
    private val usedCarClickCallback: UsedCarClickCallback
) : RecyclerView.Adapter<UsedCarAdapter.UsedCarViewHolder>() {

    private var _usedCars: List<UsedCarModel>? = null

    fun setUsedCars(usedCars: List<UsedCarModel>) {

        if (this._usedCars == null) {
            this._usedCars = usedCars

            notifyItemRangeInserted(0, usedCars.size)

        } else {

            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return requireNotNull(this@UsedCarAdapter._usedCars).size
                }

                override fun getNewListSize(): Int {
                    return usedCars.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val oldList = this@UsedCarAdapter._usedCars
                    return oldList?.get(oldItemPosition)?.id == usedCars[newItemPosition].id
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val old = this@UsedCarAdapter._usedCars!![oldItemPosition]
                    val new = this@UsedCarAdapter._usedCars!![newItemPosition]
                    return old.id == new.id
                }
            })
            this._usedCars = usedCars

            result.dispatchUpdatesTo(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewtype: Int): UsedCarViewHolder {
        val binding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.used_car_list_item,
                parent,
                false
            ) as UsedCarListItemBinding

        binding.callback = usedCarClickCallback

        return UsedCarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsedCarViewHolder, position: Int) {
        holder.binding.usedCar = _usedCars?.get(position)
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return _usedCars?.size ?: 0
    }

    open class UsedCarViewHolder(val binding: UsedCarListItemBinding) : RecyclerView.ViewHolder(binding.root)
}