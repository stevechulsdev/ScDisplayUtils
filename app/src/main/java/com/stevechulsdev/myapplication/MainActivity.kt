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

//        ScDisplayUtils.snackBar(mainView, "Show Snack Bar", true)

//        ScDisplayUtils.snackBarButton(mainView, "Show Button Snack Bar", "확인")

//        ScDisplayUtils.showDialog(this, "안내", "로그인 하시겠습니까?")

        bt_show.setOnClickListener {
            ScDisplayUtils.showProgressBar(this)
        }

        bt_hide.setOnClickListener {
            ScDisplayUtils.hideProgressBar()
        }
    }
}
