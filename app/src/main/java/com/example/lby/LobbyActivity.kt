package com.example.lby

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.lby.respository.MyRetrofit
import com.example.lby.viewModel.LobbyViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

import java.lang.String
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
import kotlin.math.log


class LobbyActivity : AppCompatActivity(){

    private lateinit var viewModel:LobbyViewModel
    private var bottomSheet: BottomSheet = BottomSheet(ArrayList(), this)
    val BOTTOM_SHEET_TAG = "BOTTOM_SHEET_TAG"
    private var seconds = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()


    }

    @SuppressLint("ClickableViewAccessibility")
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

        callAPI()

    }



    private fun callAPI() {

        CoroutineScope(IO).launch {
            val base = MyRetrofit.getInstance().getRepos()
            println("Data received: ${base.get(0).id.toString()}")
        }
    }

    private fun sessionTime() {

        val handler = Handler()
        handler.post(object : Runnable {
            override fun run() {
                val hours: Int = seconds / 3600
                val minutes: Int = seconds % 3600 / 60
                val secs: Int = seconds % 60


                val time = String
                    .format(
                        Locale.getDefault(),
                        "%d:%02d:%02d", hours,
                        minutes, secs
                    )

                timer_tv.setText(time)

                seconds++
                handler.postDelayed(this, 1000)
            }
        })
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