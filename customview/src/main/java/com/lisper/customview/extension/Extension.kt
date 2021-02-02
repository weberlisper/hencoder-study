package com.lisper.customview.extension

import android.content.res.Resources
import android.util.TypedValue
import kotlin.math.cos
import kotlin.math.sin

fun Int.dp() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this.toFloat(),
    Resources.getSystem().displayMetrics
)


fun Float.toRadians() = Math.toRadians(this.toDouble()).toFloat()

fun Float.cosByAngle() = cos(Math.toRadians(this.toDouble()))

fun Float.sinByAngle() = sin(Math.toRadians(this.toDouble()))
