package com.stevechulsdev.myapplication

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.stevechulsdev.scdisplayutils.ScDisplayUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt_show.setOnClickListener {
            ScDisplayUtils.showProgressBar(this)
        }

        bt_hide.setOnClickListener {
            ScDisplayUtils.hideProgressBar()
        }
    }
}
