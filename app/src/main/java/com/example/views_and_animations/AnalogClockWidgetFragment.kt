package com.example.views_and_animations

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.views_and_animations.databinding.FragmentWidgetAnalogClockBinding
import java.util.Locale

class AnalogClockWidgetFragment : Fragment() {
    private var _binding: FragmentWidgetAnalogClockBinding? = null
    private val binding get() = _binding!!

    var seconds: Long = 0
    var hour = seconds / 3600
    var min = (seconds % 3600) / 60
    var sec = seconds % 60
    var pressedStart = false
    var time = String.format(Locale.getDefault(), "%02d:%02d:%02d", hour, min, sec)
    var timer: CountDownTimer? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWidgetAnalogClockBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnStart = binding.buttonStart
        btnStart.setOnClickListener {
            pressedStart = !pressedStart
            if (pressedStart) {
                btnStart.text = "Stop"
            } else {
                btnStart.text = "Start"
            }
            runTimer()
        }
        val btnReset =  binding.buttonReset
        btnReset.setOnClickListener {
            pressedStart = false
            btnStart.text = "Start"
            seconds = 0
            update()
        }

    }
    fun runTimer() {
        timer?.cancel()
        var end: Long = 86400000
        timer = object : CountDownTimer(end, 1000) {
            override fun onTick(p0: Long) {
                if (pressedStart) {
                    seconds++
                    end += 1000
                    if (seconds >= 86400) {
                        seconds = 0
                    }
                     update()
                }
            }

            override fun onFinish() {}
        }.start()
    }
    private fun update() {
        val digitalTime = binding.timerDigital
        val secClock = view?.findViewById<ImageView>(R.id.clock_sec)
        val minClock = view?.findViewById<ImageView>(R.id.clock_min)
        val hourClock = view?.findViewById<ImageView>(R.id.clock_hour)

        hour = seconds / 3600
        min = (seconds % 3600) / 60
        sec = seconds % 60
        secClock!!.rotation = (sec * 6).toFloat()
        minClock!!.rotation = (min * 6).toFloat()
        hourClock!!.rotation = (hour * 6).toFloat()
        time = String.format(Locale.getDefault(), "%02d:%02d:%02d", hour, min, sec)
        digitalTime.text = time
    }

    override fun onDestroyView() {
        Log.d("AnalogClockWidgetFragment", "AnalogClockWidgetFragment DestroyView")
        super.onDestroyView()
        _binding = null
    }

}