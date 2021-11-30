package com.example.learconstruction

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import kotlinx.android.synthetic.main.splash_activity.*
import java.util.*
import kotlin.random.Random
import android.view.animation.Animation.AnimationListener as AnimationListener1

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    companion object {
        init {
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
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        setImageAnimation()
    }

    private fun setImageAnimation() {
        val alphaAnimation = AlphaAnimation(0.0f, 0.1f)
        alphaAnimation.duration = 3000
        val randoms = (0..7).random()
        im_splash.animation = alphaAnimation
        // 匿名内部类的对象类型必须是obJect 才行
        alphaAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                im_splash.setBackgroundResource(randoms)
            }

            override fun onAnimationEnd(animation: Animation?) {
                print("hello end animation")
            }

            override fun onAnimationRepeat(animation: Animation?) {
                val intent = Intent()
                intent.setClass(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
            }
        })
    }
}