package com.example.learconstruction.view

import android.content.Context
import android.view.View
import android.graphics.Paint
import android.util.AttributeSet
import android.graphics.Color
import androidx.annotation.StringRes
import android.graphics.Canvas
import android.graphics.Path
import android.os.Handler
import android.os.Message
import android.util.Log

class CurveHeadLoadingView : View {
    private var mContext: Context
    private var mTextColor //画出文字的具体颜色
            = 0
    private var mTextPaint //文字画笔
            : Paint? = null
    private val MIN_HEIGHT = 200 //最小的高度
    private val PAINT_TEXTSIZE = 40
    private val PAINT_TEXT_BASEIINE = PAINT_TEXTSIZE //BASELINE的高度
    private var mResText //画出来的文字
            : String? = null
    private var mTextWidth //文字的宽度
            = 0
    private var mDefaultText: String? = null
    private val DEFAULT_RECF_SPACE = 6 //默认的画弧形的时候的间距,值越大速度越快，不能超过最大值
    private val MAX_RECF_SPACE = 36 //最大的画弧形的时候的间距
    private val MIN_RECF_SPACE = -12 //最大的画弧形的时候的间距
    private var mRecfSpace = 0 //矩形RECF间距
    private val STATUS_DOWN_CURVE = 0 //向下弯曲的状态
    private val STATUS_UP_CURVE = 1 //向上恢复的状态
    private val STATUS_FLAT_CURVE = 2 //平的状态
    private var mCurveStatus = STATUS_FLAT_CURVE
    private val MAX_SPRING_COUNT = 18 //来回弹动的时间
    private var mSringCount = MAX_SPRING_COUNT //当前弹动的次数
    private var mPath: Path? = null

    constructor(context: Context) : super(context) {
        mContext = context
        initPaint()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        mContext = context
        initPaint()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        mContext = context
        initPaint()
    }

    fun initPaint() {
        mDefaultText = "欢迎来到新世界"
        mResText = mDefaultText
        mTextPaint = Paint()
        mTextPaint!!.color = Color.parseColor("#666666")
        mTextPaint!!.textSize = PAINT_TEXTSIZE.toFloat()
        mTextPaint!!.style = Paint.Style.STROKE
        mTextPaint!!.textAlign = Paint.Align.LEFT
        mTextWidth = mTextPaint!!.measureText(mResText).toInt()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val baseLineSpace = 20
        setMeasuredDimension(mTextWidth, PAINT_TEXTSIZE + baseLineSpace)
    }

    fun setTextColor(color: Int) {
        mTextColor = color
        mTextPaint!!.color = color
    }

    fun startAnim() {
        mSringCount = 0
        mCurveStatus = STATUS_DOWN_CURVE
        invalidate()
    }

    fun setText(@StringRes res: Int) {
        mResText = mContext.getString(res)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawLinePathAndText(canvas)
    }

    /**
     * 画出直线的文字
     *
     * @param canvas
     */
    private fun drawLinePathAndText(canvas: Canvas) {
        if (mPath == null) {
            mPath = Path()
            drawLinePath()
        } else {
            drawArcPath()
            mRecfSpace = recfSpace
            if (mRecfSpace >= MAX_RECF_SPACE) {
                mCurveStatus = STATUS_UP_CURVE
            } else if (mRecfSpace <= MIN_RECF_SPACE) {
                mCurveStatus = STATUS_DOWN_CURVE
            }
        }
        if (mSringCount < MAX_SPRING_COUNT) {
            mSringCount++
            invalidate()
        } else reset(canvas)
        canvas.drawTextOnPath(mResText!!, mPath!!, 0f, 0f, mTextPaint!!)
    }

    fun reset(canvas: Canvas?) {
        mRecfSpace = 0
        drawArcPath()
        mCurveStatus = STATUS_FLAT_CURVE
    }

    /**
     * 当矩形间距这块如果到了最大弯曲值就
     * 每次递减，反之则递增，平行状态不变
     *
     * @return
     */
    private val recfSpace: Int
        get() = if (mCurveStatus == STATUS_DOWN_CURVE) {
            mRecfSpace + DEFAULT_RECF_SPACE
        } else if (mCurveStatus == STATUS_UP_CURVE) {
            mRecfSpace - DEFAULT_RECF_SPACE
        } else {
            Log.v("h", "return 0")
            0
        }

    /**
     * 初始化直线路径
     */
    private fun drawLinePath() {
        mPath!!.moveTo(0f, PAINT_TEXT_BASEIINE.toFloat()) //设定起始点
        mPath!!.lineTo(mTextWidth.toFloat(), PAINT_TEXT_BASEIINE.toFloat()) //第一条直线的终点，也是第二条直线的起点
        mPath!!.close()
    }

    /**
     * 画出弧线路径
     */
    private fun drawArcPath() {
        mPath!!.reset()
        //        RectF rectF = new RectF(0, PAINT_TEXT_BASEIINE, mTextWidth, PAINT_TEXT_BASEIINE + mRecfSpace);
//        mPath.addOval(rectF, Path.Direction.CCW);
        mPath!!.moveTo(0f, PAINT_TEXT_BASEIINE.toFloat()) //设定起始点
        //        mPath.lineTo(mTextWidth/5,PAINT_TEXT_BASEIINE);
        mPath!!.quadTo(0f, PAINT_TEXT_BASEIINE.toFloat(), 5f, PAINT_TEXT_BASEIINE.toFloat())
        mPath!!.quadTo(
            (mTextWidth / 2).toFloat(),
            (PAINT_TEXT_BASEIINE + mRecfSpace).toFloat(),
            (mTextWidth - 5).toFloat(),
            PAINT_TEXT_BASEIINE.toFloat()
        )
        mPath!!.quadTo(
            (mTextWidth * 5 / 6).toFloat(),
            PAINT_TEXT_BASEIINE.toFloat(),
            mTextWidth.toFloat(),
            PAINT_TEXT_BASEIINE.toFloat()
        )
        mPath!!.close()
    }
}