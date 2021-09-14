package com.example.lby

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lby.adapter.BottomSheetAdapter
import com.example.lby.model.Resource
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheet(private val resourceList: ArrayList<Resource>, private val ctx: Context): BottomSheetDialogFragment() {

    private var adapter: BottomSheetAdapter = BottomSheetAdapter(ArrayList(), ctx, this::dismissDialog)
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottom_sheet, container, false)

        initRecyclerView(view)

        return view
    }

    private fun initRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.recycler_view)
        adapter = BottomSheetAdapter(resourceList, ctx, this::dismissDialog)
        recyclerView.layoutManager = LinearLayoutManager(ctx)
        recyclerView.adapter = adapter
    }

    fun dismissDialog() {
        this.dismiss()
    }
}