package com.example.lby

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lby.adapter.TestAdapter
import com.example.lby.model.Base
import com.example.lby.respository.MyRetrofit
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TestActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        callAPI()
    }

    private fun callAPI() {

        CoroutineScope(Dispatchers.IO).launch {
            val base = MyRetrofit.getInstance().getRepos()
            println("Data received: ${base.get(0).id.toString()}")

            withContext(Main) {
                setAdapter(base)
            }
        }

    }

    private fun setAdapter(baseList: ArrayList<Base>) {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = TestAdapter(baseList, this)


    }
}