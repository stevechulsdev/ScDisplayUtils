package com.stevechulsdev.scdisplayutils

import android.app.Activity
import android.graphics.*
import android.os.Build
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import java.lang.Exception

object ScDisplayUtils {
    private var TAG: String = javaClass.simpleName

    private var mProgressBar: ProgressBar? = null

    /**
     * Show Progress Bar (Circle)
     * @param activity Activity
     * @param largeCircle true 큰 동그라미, false 작은 동그라미
     * @param colorResourceId null이면 기본 값 ex) R.color.xxx
     */
    fun showProgressBar(activity: Activity, largeCircle: Boolean = false, colorResourceId: Int? = null) {
        if(largeCircle) {
            mProgressBar = ProgressBar(activity.applicationContext, null, android.R.attr.progressBarStyleLarge).apply {
                isIndeterminate = true
                visibility = View.VISIBLE

                try {
                    colorResourceId?.let {
                        if(Build.VERSION.SDK_INT >= 29) indeterminateDrawable.colorFilter = BlendModeColorFilter(colorResourceId, BlendMode.SRC_IN)
                        else indeterminateDrawable.setColorFilter(colorResourceId, PorterDuff.Mode.SRC_IN)
                    }
                }
                catch (e: Exception) {
                    Log.e(TAG, "colorResourceId setting error : ${e.message}")
                }
            }
        }
        else {
            mProgressBar = ProgressBar(activity.applicationContext, null, android.R.attr.progressBarStyle).apply {
                isIndeterminate = true
                visibility = View.VISIBLE

                try {
                    colorResourceId?.let {
                        if(Build.VERSION.SDK_INT >= 29) indeterminateDrawable.colorFilter = BlendModeColorFilter(colorResourceId, BlendMode.SRC_IN)
                        else indeterminateDrawable.setColorFilter(colorResourceId, PorterDuff.Mode.SRC_IN)
                    }
                }
                catch (e: Exception) {
                    Log.e(TAG, "colorResourceId setting error : ${e.message}")
                }
            }
        }

        val parent = activity.window.decorView.rootView as ViewGroup
        val layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT)

        val rl = RelativeLayout(activity.applicationContext)
        rl!!.gravity = Gravity.CENTER
        rl!!.addView(mProgressBar)

        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT)

        parent!!.addView(rl, layoutParams)
    }

    /**
     * Hide ProgressBar
     */
    fun hideProgressBar() {
        mProgressBar?.let {it.apply {
            if(visibility == View.VISIBLE) visibility = View.GONE
            mProgressBar = null
        } }
    }
}