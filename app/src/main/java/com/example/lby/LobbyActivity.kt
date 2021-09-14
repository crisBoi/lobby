package com.example.lby

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.lby.viewModel.LobbyViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.String
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class LobbyActivity : AppCompatActivity() {

    private lateinit var viewModel:LobbyViewModel
    private var bottomSheet: BottomSheet = BottomSheet(ArrayList(), this)
    val BOTTOM_SHEET_TAG = "BOTTOM_SHEET_TAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        viewModel = ViewModelProvider(this).get(LobbyViewModel::class.java)

        viewModel.init()
        viewModel.getResource()?.observe(this, Observer {
            bottomSheet = BottomSheet(it, this)
        })

        resource_cv.setOnClickListener(View.OnClickListener { v ->
            bottomSheet.show(supportFragmentManager, BOTTOM_SHEET_TAG)
        })


        sessionTime()
        estimatedWaitingTime()


    }


    private fun sessionTime() {
        object : CountDownTimer(30 * 60 * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                val text = String.format(
                    Locale.getDefault(), "%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished) % 60,
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60,
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60,

                    )

                timer_tv.text = text
            }
            override fun onFinish() {
            }
        }.start()
    }

    private fun estimatedWaitingTime() {
        object : CountDownTimer(5 * 60 * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                val text = String.format(
                    Locale.getDefault(), "Estimated waiting time %02d min: %02d sec",
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60,
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60
                )

                textView2.text = text
            }
            override fun onFinish() {
            }
        }.start()
    }

}