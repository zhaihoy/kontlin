package com.example.learconstruction

import android.app.Application

/**
 * @Author :  hongyuan3
 * @Time : 2021/11/30 11:34 上午
 * @Description : Kotlin 学习笔记 -单例
 */
class LearConstructionApplication : Application() {
    object context {
        //todo  lateinit 修饰类属性的时候，实际上在告诉编译器：这个属性的初始化的时机和方式由我亲自把控
        // 有点像java中的 volatile 防止指令重排序导致单例为空的情况
        @JvmStatic
        lateinit var instance: LearConstructionApplication
    }
}