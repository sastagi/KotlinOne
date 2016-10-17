package com.example.santoshastagi.kotlinone

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import java.util.jar.Attributes

class CircleView : View{
    private val INTERPOLATOR = AccelerateDecelerateInterpolator()
    private val mRect = RectF()
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mListener:Listener? = null
    private val mStrokeColor = Color.RED
    private val mStrokeWidth = 1

    private var mAnimationStartTime:Float = 0f
    private var mDuration:Float = 0f
    private var mClockwise = false

    enum class State(val state:Int) {
        START(0),
        END(1),
        ANIMATING(2)
    }

    private var mState = State.END

    constructor(context:Context): super(context)
    constructor(context:Context, attrs: AttributeSet): super(context, attrs)

    init {
        mPaint.color = mStrokeColor
        mPaint.style = Paint.Style.FILL_AND_STROKE
        mPaint.strokeWidth = mStrokeWidth.toFloat()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mRect.left = 0f
        mRect.right = w.toFloat()
        mRect.top = 0f
        mRect.bottom = h.toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var percentToDraw:Float = 1f

        if (mState == State.START) {
            //do nothing
            return
        }

        if (mState == State.ANIMATING) {
            val currentAnimationTime = (System.currentTimeMillis() - mAnimationStartTime).toFloat()
            val percentAnimationTime = currentAnimationTime / mDuration
            if (percentAnimationTime >= 1f) {
                percentToDraw = 1f
                mState = State.END
                if (mListener != null) mListener.onAnimationEnd()
            } else {
                percentToDraw = INTERPOLATOR.getInterpolation(percentAnimationTime)
            }
        } else {
            // END state
            percentToDraw = 1f
        }

        val sweepValue = 360f * percentToDraw
        val sweepAngle = if (mClockwise) sweepValue else -sweepValue
        canvas.drawArc(mRect, -90f, sweepAngle, false, mPaint)

        if (mState == State.ANIMATING) {
            postInvalidateOnAnimation()
        }
    }

    interface Listener {
        fun onAnimationEnd()
    }
}