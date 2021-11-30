package com.example.learconstruction.utils

import android.content.Context
import android.graphics.Typeface
import android.widget.TextView

/**
 * 字体相关操作工具类
 *
 */
class TypefaceUtil(private val mContext: Context, ttfPath: String?) {
    private var mTypeface: Typeface

    /**
     * 从ttf文件创建Typeface对象
     *
     * @ttfPath "fonts/XXX.ttf"
     */
    private fun getTypefaceFromTTF(ttfPath: String?): Typeface {
        return if (ttfPath == null) {
            Typeface.DEFAULT
        } else {
            Typeface.createFromAsset(mContext.assets, ttfPath)
        }
    }

    /**
     * 设置TextView的字体
     *
     * @tv TextView对象
     * @ttfPath ttf文件路径
     * @isBold 是否加粗字体
     */
    fun setTypeface(tv: TextView, isBold: Boolean) {
        tv.typeface = mTypeface
        setBold(tv, isBold)
    }

    /**
     * 设置字体加粗
     */
    private fun setBold(tv: TextView, isBold: Boolean) {
        val tp = tv.paint
        tp.isFakeBoldText = isBold
    }

    /**
     * 设置TextView的字体为系统默认字体
     *
     */
    fun setDefaultTypeFace(tv: TextView, isBold: Boolean) {
        tv.typeface = Typeface.DEFAULT
        setBold(tv, isBold)
    }

    /**
     * 设置当前工具对象的字体
     *
     */
    fun setTypeface(ttfPath: String?) {
        mTypeface = getTypefaceFromTTF(ttfPath)
    }

    /**
     * 如果ttfPath为null那么mTypeface就为系统默认值
     *
     * @param context
     * @param ttfPath
     */
    init {
        mTypeface = getTypefaceFromTTF(ttfPath)
    }
}