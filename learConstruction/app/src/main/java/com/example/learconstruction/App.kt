package com.example.learconstruction

import android.app.Application
import com.example.learconstruction.App

/**
 * @Author :  hongyuan3
 * @Time : 2021/11/30 5:40 下午
 * @Description : 用于全局的Context
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private var instance: App? = null
    }
}