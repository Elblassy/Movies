package app.elblasy.cinema.utils

import android.content.Context
import android.view.View
import androidx.core.view.ViewCompat
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs


class CarouselEffectTransformer(
    context: Context
) : ViewPager.PageTransformer {

    private var maxTranslateOffsetX = 0
    private var viewPager: ViewPager? = null

    init {
        maxTranslateOffsetX = dp2px(context, 100f)
    }

    override fun transformPage(page: View, position: Float) {
        if (viewPager == null) {
            viewPager = page.parent as ViewPager
        }

        val leftInScreen: Int = page.left - viewPager!!.scrollX
        val centerXInViewPager: Int = leftInScreen + page.measuredWidth / 2
        val offsetX = centerXInViewPager - viewPager!!.measuredWidth / 2
        val offsetRate =
            offsetX.toFloat() * 0.18f / viewPager!!.measuredWidth
        val scaleFactor = 1 - abs(offsetRate)

        if (scaleFactor > 0) {
            page.pivotY = page.height.toFloat()
            page.scaleY = scaleFactor
            page.translationX = -maxTranslateOffsetX * offsetRate
        }
        ViewCompat.setElevation(page, scaleFactor)

    }

    private fun dp2px(context: Context, dipValue: Float): Int {
        val m = context.resources.displayMetrics.density
        return (dipValue * m + 0.5f).toInt()
    }
}