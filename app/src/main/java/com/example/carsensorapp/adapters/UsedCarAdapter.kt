package com.example.carsensorapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.carsensorapp.R
import com.example.carsensorapp.services.api.responses.UsedCarModel

class UsedCarAdapter(private val usedCarClickCallback: UsedCarClckCallback) : RecyclerView.Adapter<UsedCarAdapter.UsedCarHelper>() {

        private var _usedCars: List<UsedCarModel>? = null

        fun setProjectList(usedCars: List<UsedCarModel>) {

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

        override fun onCreateViewHolder(parent: ViewGroup, viewtype: Int): ProjectViewHolder {
            val binding =
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.project_list_item, parent,
                    false) as ProjectListItemBinding

            binding.callback = projectClickCallback

            return ProjectViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
            holder.binding.project = _usedCars?.get(position)
            holder.binding.executePendingBindings()
        }

        override fun getItemCount(): Int {
            return projectList?.size ?: 0
        }
    }
    open class ProjectViewHolder(val binding: ProjectListItemBinding) : RecyclerView.ViewHolder(binding.root)
}