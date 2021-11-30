package com.example.learconstruction.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.example.learconstruction.R
import com.example.learconstruction.activity.constants.Constant
import com.example.learconstruction.utils.SPUtils
import kotlinx.android.synthetic.main.splash_activity.*
import java.util.*

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {
    //    companion object {
//        init {
    //Kotlin中取消了静态代码块  kotlin伴生对象在类中只能存在一个
    val numbers: IntArray = intArrayOf(
        R.drawable.be,
        R.drawable.el,
        R.drawable.old,
        R.drawable.old_,
        R.drawable.old_m,
        R.drawable.old_ma,
        R.drawable.wu,
        R.drawable.xie
    )
//        }
//    }

    override fun processConstruction() {
        setImageAnimation()
    }

    override fun getLayout(): Int {
        return R.layout.splash_activity
    }

    private fun setImageAnimation() {
        val alphaAnimation = AlphaAnimation(0f, 1f)
        alphaAnimation.duration = 6000
        val randoms = (0..7).random()
        im_splash.animation = alphaAnimation
        // 匿名内部类的对象类型必须是obJect 才行
        alphaAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                im_splash.setBackgroundResource(numbers[randoms])
            }

            override fun onAnimationEnd(animation: Animation?) {
                print("hello end animation")
                val isLogin: Boolean by SPUtils(Constant.LOGIN_KEY, false)
                if (isLogin) {
                    processJump(MainActivity::class.java)
                } else {
                    processJump(LoginActivity::class.java)
                }
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }
        })
    }

    private fun processJump(cla: Class<*>) {
        val intent = Intent()
        intent.setClass(this@SplashActivity, cla)
        startActivity(intent)
        finish()
    }
}