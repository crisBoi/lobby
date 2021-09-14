package com.example.lby.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.lby.R
import com.example.lby.model.Resource

class BottomSheetAdapter(
    private val resourceList: ArrayList<Resource>,
    val ctx: Context,
    val kFunction0: () -> Unit
): RecyclerView.Adapter<BottomSheetAdapter.MyViewHolder>() {



    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.imageView)
        val titleTv: TextView = itemView.findViewById(R.id.title_tv)
        val selectedRowCv: ConstraintLayout = itemView.findViewById(R.id.selected_row_cv)

        fun bind(resource: Resource, ctx: Context, kFunction0: () -> Unit) {
            img.setImageDrawable(ctx.getDrawable(resource.image))
            titleTv.text = resource.title

            selectedRowCv.setOnClickListener(View.OnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setData(Uri.parse(resource.url))
                ctx.startActivity(intent)
                kFunction0()
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_bottom_sheet, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(resourceList[position], ctx, kFunction0)
    }

    override fun getItemCount(): Int = resourceList.size


}