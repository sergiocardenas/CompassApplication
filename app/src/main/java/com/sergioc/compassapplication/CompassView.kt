package com.sergioc.compassapplication

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix
import android.util.AttributeSet
import android.view.View


class CompassView : View {

    private var arrow: Bitmap? = null
    private var srcArrow: Bitmap? = null
    private var position: Matrix? = null
    private var updatedDirection: Boolean = false
    private var currentDegree: Int = 0


    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(context)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        updateArrow()
        if (arrow != null || position != null)
            canvas.drawBitmap(arrow!!, position!!, null)
    }

    private fun init(context: Context) {
        setBackgroundResource(R.drawable.compass)
        srcArrow = BitmapFactory.decodeResource(context.resources, R.drawable.arrow_vector)
        updatedDirection = false
        currentDegree = 0
    }

    private fun updateArrow() {
        if (updatedDirection || position == null || arrow == null) {
            updatedDirection = false
            arrow = Bitmap.createScaledBitmap(srcArrow!!, width / 4, height / 2, true)
            position = Matrix()
            position!!.setRotate(currentDegree.toFloat(),
                (arrow!!.width / 2).toFloat(),
                (arrow!!.height / 2).toFloat())
            position!!.postTranslate((width / 2 - arrow!!.width / 2).toFloat(),
                (height / 2 - arrow!!.height / 2).toFloat()
            )
        }
    }

    fun updateCurrentDirection(newDegree: Int) {
        updatedDirection = true
        currentDegree = newDegree
    }

}
