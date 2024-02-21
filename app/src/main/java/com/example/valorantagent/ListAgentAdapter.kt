package com.example.valorantagent

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.valorantagent.databinding.ItemRowAgentBinding

class ListAgentAdapter(private val listAgent: ArrayList<Agent>): RecyclerView.Adapter<ListAgentAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =  ItemRowAgentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_agent, parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listAgent.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, icon) = listAgent[position]
        Glide.with(holder.itemView.context)
            .load(icon)
                .into(holder.binding.imgAgentIcon)
        holder.binding.tvItemName.text = name
        holder.binding.tvItemDescription.text = description
        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra("extra_detail", listAgent[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    class ListViewHolder(var binding: ItemRowAgentBinding) : RecyclerView.ViewHolder(binding.root) {
//        val imgPhoto: ImageView = itemView.findViewById(R.id.img_agent_icon)
//        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
//        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }

}