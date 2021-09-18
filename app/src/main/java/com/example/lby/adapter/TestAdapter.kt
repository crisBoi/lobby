package com.example.lby.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.lby.R
import com.example.lby.model.Base

class TestAdapter(
        val baseList: ArrayList<Base>,
        val ctx: Context): RecyclerView.Adapter<TestAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val urlTv: TextView = itemView.findViewById(R.id.url_tv)
        val selectedRow: ConstraintLayout = itemView.findViewById(R.id.selected_row_cv)

        fun bind(base: Base, ctx: Context) {
            urlTv.text = base.html_url

            selectedRow.setOnClickListener(View.OnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(base.html_url));
                ctx.startActivity(intent)
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_test, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(baseList.get(position), ctx)
    }

    override fun getItemCount(): Int = baseList.size
}