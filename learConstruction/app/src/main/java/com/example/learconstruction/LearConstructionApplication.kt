package com.example.learconstruction

import android.app.Application
import android.content.Context
import kotlin.properties.Delegates

/**
 * @Author :  hongyuan3
 * @Time : 2021/11/30 11:34 上午
 * @Description : Kotlin 学习笔记 -单例
 */
class LearConstructionApplication : Application() {

    /**
     * 这两个模型是相似的，Delegates.notNull（）（api参考）基于委托属性，是原始的
     *，后来是lateinit（后期初始化属性）。不覆盖所有可以能的用例，除非你能够控制类的生命周期，并确保它们在使用前初始化。
     */
    //todo  lateinit 修饰类属性的时候，实际上在告诉编译器：这个属性的初始化的时机和方式由我亲自把控
    // 有点像java中的 volatile 防止指令重排序导致单例为空的情况
//    companion object {
//
//        var context: Context by Delegates.notNull()
//
//        lateinit var instance: Application   f**K 慎用 lateinit！！
//    }
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
         var instance: LearConstructionApplication? = null
    }
}