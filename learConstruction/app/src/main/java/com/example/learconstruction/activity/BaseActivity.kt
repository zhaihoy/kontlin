package com.example.learconstruction.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * @Author :  hongyuan3
 * @Time : 2021/11/30 4:52 下午
 * @Description : 用于页面Base
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        setContentView(getLayout())
        processConstruction()

    }

    /**
     * 用于处理我们我们inflate之后的逻辑
     */
    abstract fun processConstruction()

    /**
     * 展示我们所用的布局
     */
    protected abstract fun getLayout(): Int

}