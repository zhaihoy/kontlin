package com.example.learconstruction

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import kotlinx.android.synthetic.main.splash_activity.*
import java.util.*
import kotlin.random.Random
import android.view.animation.Animation.AnimationListener as AnimationListener1

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        setContentView(R.layout.splash_activity)
        setImageAnimation()
    }

    private fun setImageAnimation() {
        val alphaAnimation = AlphaAnimation(0.5f, 1f)
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
                val intent = Intent()
                intent.setClass(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }
        })
    }
}