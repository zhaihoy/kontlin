package com.example.learconstruction.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.RotateAnimation
import android.view.animation.TranslateAnimation
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.learconstruction.R
import kotlinx.android.synthetic.main.jump_view.view.*

/**
 * @Author :  hongyuan3
 * @Time : 2021/11/30 3:15 下午
 * @Description : 一个弹跳的加载的自定义View
 */
class JumpView : FrameLayout {
    //图片集合

    private val mResDrawable: IntArray =
        intArrayOf(R.drawable.old, R.drawable.old_, R.drawable.old_m, R.drawable.old_ma)
    private var mSkip = true
    private var mIndex = 0//当前图片的下标
    private val MAX_HEIGHT = 100
    private val DURATION = 600
    private var mViewAnimEndListener: OnViewAnimEndListener? = null
    var rotateAnimation: RotateAnimation? = null
    var translateAnimation: TranslateAnimation? = null
    var backAnimation: TranslateAnimation? = null
    lateinit var animationSet2: AnimationSet
    var animationSet: AnimationSet? = null
    private var mContext: Context? = null

    constructor(context: Context) : super(context) {
        init(context)
    }

    private fun init(context: Context) {
        this.mContext = context
        val inflate = LayoutInflater.from(context).inflate(R.layout.jump_view, this)
        inflate.view_image.setImageResource(mResDrawable[0])
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(0, MAX_HEIGHT, 0, 0)
        curheadloadingview.layoutParams = layoutParams
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyAttr: Int) : super(
        context,
        attributeSet,
        defStyAttr
    ) {
        init(context)
    }

    fun setOnViewAnimEndListener(mViewAnimEndListener: OnViewAnimEndListener) {
        this.mViewAnimEndListener = mViewAnimEndListener
    }

    fun changeIcon() {
        view_image!!.clearAnimation()
        if (mSkip) {
            mIndex = 2
            mSkip = false
        } else mIndex = if (mIndex == mResDrawable.size - 1) 0 else mIndex + 1
        view_image.setImageResource(mResDrawable[mIndex])
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(
            widthMeasureSpec,
            MAX_HEIGHT + view_image!!.measuredHeight + curheadloadingview!!.measuredHeight
        )
        loadAnim()
    }

    fun loadAnim() {
        translateAnimation = TranslateAnimation(0F, 0F, 0F, MAX_HEIGHT.toFloat())
        translateAnimation!!.duration = DURATION.toLong()
        translateAnimation!!.fillAfter = true
        backAnimation = TranslateAnimation(0F, 0F, MAX_HEIGHT.toFloat(), 0F)
        backAnimation!!.duration = DURATION.toLong()
        backAnimation!!.fillAfter = true
        rotateAnimation = RotateAnimation(
            0F, 360F,
            Animation.RELATIVE_TO_SELF, 0.5f,  //0.5 = 1/2的自己父控件的长度
            Animation.RELATIVE_TO_SELF, 0.5f
        ) //0.5 = 1/2的自己的长度
        rotateAnimation!!.repeatCount = 0
        rotateAnimation!!.duration = DURATION.toLong()

//        rotateAnimation.setAnimationListener(animationListener);
        animationSet = AnimationSet(true)
        animationSet!!.addAnimation(rotateAnimation)
        animationSet!!.addAnimation(translateAnimation)
        animationSet2 = AnimationSet(true)
        animationSet2.addAnimation(rotateAnimation)
        animationSet2.addAnimation(backAnimation)
        translateAnimation!!.setAnimationListener(animationListener)
        backAnimation!!.setAnimationListener(animationListener)
        with(view_image) { animation = animationSet }
        animationSet2.setInterpolator(mContext, android.R.anim.decelerate_interpolator)
        animationSet!!.setInterpolator(mContext, android.R.anim.accelerate_interpolator)
        animationSet!!.start()
    }

    private var animationListener: Animation.AnimationListener =
        object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) =
                if (animation === translateAnimation) {
//                if (mViewAnimEndListener == null)
//                    throw new NullPointerException("please use setonViewAnimEndListener method before use fruitView");
//                else mViewAnimEndListener.onDropDown();
                    curheadloadingview!!.startAnim()
                    changeIcon()
                    view_image!!.animation = animationSet2
                    animationSet2.start()
                } else {
                    view_image!!.animation = animationSet
                    animationSet!!.start()
                }

            override fun onAnimationRepeat(animation: Animation) {}
        }


    /**
     * 此View向下的俯冲动画结束的回调
     */
    interface OnViewAnimEndListener {
        fun onDropDown()
    }

}