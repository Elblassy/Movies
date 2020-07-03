package app.elblasy.cinema.ui.home


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import app.elblasy.cinema.R
import app.elblasy.cinema.models.Movie
import app.elblasy.cinema.utils.PageAdapterHelper


class PagerAdapter(
    val context: Context,
    private val list: List<Movie>,
    val adapterType: Int
) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var view: View? = null

        try {
            when (adapterType) {
                PageAdapterHelper.ADAPTER_TYPE_BOTTOM.value -> {
                    view = LayoutInflater.from(context).inflate(R.layout.bg_item, null)
                    val layout =
                        view.findViewById<View>(R.id.motion) as ConstraintLayout
                    layout.setBackgroundResource(list[position].image)

                    layout.tag = position
                    container.addView(view)
                }
                PageAdapterHelper.ADAPTER_TYPE_TOP.value -> {
                    view = LayoutInflater.from(context).inflate(R.layout.movie_items, null)
                    val imageCover
                            : ImageView =
                        view.findViewById<View>(R.id.movieImage) as ImageView

                    val title
                            : TextView =
                        view.findViewById<View>(R.id.movie_name) as TextView

                    val rating
                            : RatingBar =
                        view.findViewById<View>(R.id.rate) as RatingBar

                    imageCover.setImageResource(list[position].image)
                    title.setText(list[position].title)
                    rating.rating = list[position].rate.toFloat()

                    container.addView(view)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return view!!
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun getCount(): Int = list.size


}