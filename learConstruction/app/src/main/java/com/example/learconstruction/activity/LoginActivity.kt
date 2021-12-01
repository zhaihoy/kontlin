package com.example.learconstruction.activity

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.example.learconstruction.R
import kotlinx.android.synthetic.main.login_activity.*
import java.time.Duration

/**
 * @Author :  hongyuan3
 * @Time : 2021/11/30 4:49 下午
 * @Description : 用于登录and注册的页面
 */
class LoginActivity : BaseActivity() {
    private var user_name = "";
    private var psd = ""
    override fun processConstruction() {
        et_username.setOnClickListener {
            user_name = et_username.text.toString()
        }
        et_password.setOnClickListener(View.OnClickListener {
            psd = et_password.text.toString()
        })

        btn_login.setOnClickListener {
            if (TextUtils.isEmpty(user_name) || TextUtils.isEmpty(psd)) {
                Toast.makeText(this, "检查一下，输的什么玩意？", Toast.LENGTH_LONG).show()
                if (TextUtils.isEmpty(user_name) && et_username != null) {
                    nope(et_username)
                }
                if (TextUtils.isEmpty(psd) && et_password != null) {
                    nope(et_password)
                }
            }
        }
    }

    override fun getLayout(): Int {
        return R.layout.login_activity
    }

      fun nope(view: View?) {
        val pvhX: PropertyValuesHolder = PropertyValuesHolder.ofFloat(
            "alpha",
            1f,
            0.9f,
            0.9f,
            0.91f,
            0.92f,
            0.93f,
            0.94f,
            0.95f,
            0.96f,
            0.97f,
            0.98f,
            0.99f,
            1f
        )
        val pvhY: PropertyValuesHolder = PropertyValuesHolder.ofFloat(
            "scaleX",
            1f,
            0.9f,
            0.9f,
            0.91f,
            0.92f,
            0.93f,
            0.94f,
            0.95f,
            0.96f,
            0.97f,
            0.98f,
            0.99f,
            1f
        )
        val pvhZ: PropertyValuesHolder = PropertyValuesHolder.ofFloat(
            "scaleY",
            1f,
            0.9f,
            0.9f,
            0.91f,
            0.92f,
            0.93f,
            0.94f,
            0.95f,
            0.96f,
            0.97f,
            0.98f,
            0.99f,
            1f
        )
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY, pvhZ).setDuration(1000).start()
    }
}