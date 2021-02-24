package com.example.fa_lession9_ex1_2_chinhld4.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fa_lession9_ex1_2_chinhld4.R
import com.example.fa_lession9_ex1_2_chinhld4.model.Contacts
import kotlinx.android.synthetic.main.custom_row_contacts.view.*

class ContactAdapter(item: List<Contacts>, ctx: Context) :
    RecyclerView.Adapter<ContactAdapter.ViewHolder>() {
    private var list = item
    private var context = ctx
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.custom_row_contacts, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ContactAdapter.ViewHolder, position: Int) {
        val currentItem = list[position]
        holder.name.text = currentItem.name
        holder.number.text = currentItem.number
        if (list[position].image != null) {
            holder.profile.setImageBitmap(list[position].image)
        }
        holder.profile.setImageResource(R.drawable.ic_baseline_person_24)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.txtName!!
        val number: TextView = view.txtNumber!!
        val profile: ImageView = view.imgProfile!!
    }
}