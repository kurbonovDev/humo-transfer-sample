package com.example.humo_transfer_sample.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.humo_transfer_sample.R
import com.example.humo_transfer_sample.activities.TransferActivity

import com.example.humo_transfer_sample.models.CountryItem


class CountryAdapter(
    private var listCountry: List<CountryItem>,
    private val isPopularXml: Boolean = false
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class PopularCountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.popular_country_image)
        val tvText: TextView = view.findViewById(R.id.country_txt)
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.country_image)
        val tvText: TextView = view.findViewById(R.id.country_txt_2)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (isPopularXml) {
            true -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.popular_country_item, parent, false)
                PopularCountryViewHolder(itemView)
            }

            false -> {
                val itemView2 = LayoutInflater.from(parent.context)
                    .inflate(R.layout.country_item, parent, false)
                MyViewHolder(itemView2)
            }
        }
    }

    override fun getItemCount(): Int = listCountry.size


    fun setFilterList(list: List<CountryItem>) {
        this.listCountry = list
        notifyDataSetChanged()

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PopularCountryViewHolder -> {
                holder.imageView.setImageResource(listCountry[position].image)
                holder.tvText.text = listCountry[position].nameCountry
                holder.itemView.setOnClickListener {
                    val intent=Intent(holder.itemView.context,TransferActivity::class.java)
                    intent.putExtra("name_country",listCountry[position].nameCountryForTransfer)
                    intent.putExtra("initial_country",listCountry[position].initial)
                    intent.putExtra("course_country",listCountry[position].course)
                    holder.itemView.context.startActivity(intent)
                }
            }

            is MyViewHolder -> {
                holder.imageView.setImageResource(listCountry[position].image)
                holder.tvText.text = listCountry[position].nameCountry
                holder.itemView.setOnClickListener {
                    val intent=Intent(holder.itemView.context,TransferActivity::class.java)
                    intent.putExtra("name_country",listCountry[position].nameCountryForTransfer)
                    intent.putExtra("initial_country",listCountry[position].initial)
                    intent.putExtra("course_country",listCountry[position].course)
                    holder.itemView.context.startActivity(intent)
                }
            }
        }
    }

}