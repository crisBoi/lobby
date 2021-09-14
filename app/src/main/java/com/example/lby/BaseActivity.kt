package com.example.lby

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_base.*

class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        join_btn.setOnClickListener(View.OnClickListener { v ->
            startActivity(Intent(this, LobbyActivity::class.java))
        })
    }
}