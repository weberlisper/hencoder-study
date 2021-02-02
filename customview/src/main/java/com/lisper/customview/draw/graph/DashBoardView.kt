package com.lisper.customview.draw.graph

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.lisper.customview.extension.cosByAngle
import com.lisper.customview.extension.dp
import com.lisper.customview.extension.sinByAngle

val RADIUS = 100.dp()
const val DASH_COUNT = 20
val DASH_WIDTH = 2.dp()
val DASH_HEIGHT = 10.dp()
const val DEFAULT_VALUE = 4
const val START_ANGLE = 150f
const val ARC_ANGLE = 240f
val POINTER_LEN = 80.dp()

class DashBoardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr) {

    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 2.dp()
    }
    private lateinit var pathEffect: PathEffect
    private val dashPath = Path().apply {
        addRect(0f, 0f, DASH_WIDTH, DASH_HEIGHT, Path.Direction.CW)
    }
    private val arcPath = Path()

    private val value = DEFAULT_VALUE       // 刻度值

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        val arcRectF = RectF(
            w.toFloat() / 2 - RADIUS,
            h.toFloat() / 2 - RADIUS,
            w.toFloat() / 2 + RADIUS,
            h.toFloat() / 2 + RADIUS
        )
        arcPath.addArc(arcRectF, START_ANGLE, ARC_ANGLE)

        val pathMeasure = PathMeasure(arcPath, false)
        pathEffect = PathDashPathEffect(
            dashPath,
            (pathMeasure.length - 2.dp()) / (DASH_COUNT - 1),
            0f,
            PathDashPathEffect.Style.ROTATE
        )
    }

    override fun onDraw(canvas: Canvas) {
        // 画圆弧
        canvas.drawPath(arcPath, paint)

        // 画刻度
        paint.pathEffect = pathEffect
        canvas.drawPath(arcPath, paint)
        paint.pathEffect = null

        // 画指针
        val angleForArc = ARC_ANGLE * value / (DASH_COUNT - 1)
        val angle = angleForArc + START_ANGLE
        canvas.drawLine(
            width.toFloat() / 2,
            height.toFloat() / 2,
            (width.toFloat() / 2 + angle.cosByAngle() * POINTER_LEN).toFloat(),
            (height.toFloat() / 2 + angle.sinByAngle() * POINTER_LEN).toFloat(),
            paint
        )
    }
}