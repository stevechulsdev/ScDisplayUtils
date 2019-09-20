package com.stevechulsdev.scdisplayutils

import android.app.Activity
import android.content.Context
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import org.jetbrains.anko.alert
import java.lang.Exception

object ScDisplayUtils {
    private var TAG: String = javaClass.simpleName

    private var mProgressBar: ProgressBar? = null
    private var rl: RelativeLayout? = null
    private var parent: ViewGroup? = null

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
                setBackgroundColor(ContextCompat.getColor(activity, android.R.color.transparent))
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
            mProgressBar = ProgressBar(activity.applicationContext, null, android.R.attr.progressBarStyleInverse).apply {
                isIndeterminate = true
                setBackgroundColor(ContextCompat.getColor(activity, android.R.color.transparent))
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

        parent = activity.window.decorView.rootView as ViewGroup
        activity.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT)

        rl = RelativeLayout(activity.applicationContext)
        rl!!.gravity = Gravity.CENTER
        rl!!.addView(mProgressBar)
        rl!!.isClickable = true
//        rl!!.setBackgroundColor(Color.parseColor("#80000000"))

        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT)

        parent!!.addView(rl, layoutParams)
    }

    /**
     * Hide ProgressBar
     */
    fun hideProgressBar() {
//        mProgressBar?.let { it.apply {
//            if(visibility == View.VISIBLE) visibility = View.GONE
//        } }

        parent?.let { viewGroup ->
            rl?.let {
                viewGroup.removeView(it)
            }
        }

        mProgressBar = null
        rl = null
        parent = null
    }

    fun toast(context: Context, toastMsg: String, showLong: Boolean = false) {
        if(showLong) Toast.makeText(context, toastMsg, Toast.LENGTH_LONG).show()
        else Toast.makeText(context, toastMsg, Toast.LENGTH_SHORT).show()
    }

    fun snackBar(mainView: View, snackbarMsg: String, showLong: Boolean = false) {
        if(showLong) Snackbar.make(mainView, snackbarMsg, Snackbar.LENGTH_LONG).show()
        else Snackbar.make(mainView, snackbarMsg, Snackbar.LENGTH_SHORT).show()
    }

    fun snackBarButton(mainView: View, snackbarMsg: String, buttonMsg: String) {
        Snackbar.make(mainView, snackbarMsg, Snackbar.LENGTH_INDEFINITE).apply {
            setAction(buttonMsg) {
                dismiss()
            }
            show()
        }
    }

    fun showDialog(context: Context, dialogTitle: String, dialogMsg: String, dialogOneButtonClickListener: DialogOneButtonClickListener? = null, buttonMsg: String = "확인", dismissOutSideTouch: Boolean = false) {
        context.alert(dialogMsg, dialogTitle) {
            isCancelable = dismissOutSideTouch
            positiveButton(buttonMsg) {
                dialogOneButtonClickListener?.let {
                    it.onClick()
                }
            }
        }.show()
    }

    interface DialogOneButtonClickListener {
        fun onClick()
    }
}