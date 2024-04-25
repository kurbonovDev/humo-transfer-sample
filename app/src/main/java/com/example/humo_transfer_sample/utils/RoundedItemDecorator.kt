package com.example.humo_transfer_sample.utils
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.graphics.RectF
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView




//ChatGpt постарался помочь:)
class RoundedItemDecorator(radiusInDp: Float, context: Context) : RecyclerView.ItemDecoration() {
    private val path = Path()
    private val radii = FloatArray(8)
    private val paint: Paint = Paint()

    init {
        val radiusInPixels = radiusInDp * context.resources.displayMetrics.density
        radii.fill(radiusInPixels)
        paint.style = Paint.Style.FILL
        paint.color = ContextCompat.getColor(context, android.R.color.white)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.setEmpty()
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val rect = RectF(parent.paddingLeft.toFloat(), parent.paddingTop.toFloat(),
            (parent.width - parent.paddingRight).toFloat(), (parent.height - parent.paddingBottom).toFloat())

        path.reset()
        path.addRoundRect(rect, radii, Path.Direction.CW)

        c.drawPath(path, paint)
    }
}
