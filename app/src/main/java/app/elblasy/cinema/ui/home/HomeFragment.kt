package app.elblasy.cinema.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import app.elblasy.cinema.R
import app.elblasy.cinema.models.Movie
import app.elblasy.cinema.utils.CarouselEffectTransformer
import app.elblasy.cinema.utils.PageAdapterHelper
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    private val TAG = "HomeFragment"

    private lateinit var homeViewModel: HomeViewModel
    val ADAPTER_TYPE_TOP = 1
    val ADAPTER_TYPE_BOTTOM = 2

    private val movieList = listOf(
        Movie("DeadPool", R.drawable.deadpool, 5),
        Movie("Joker", R.drawable.jocker, 4),
        Movie("Hustle", R.drawable.hustle, 3),
        Movie("DeadPool", R.drawable.deadpool, 5),
        Movie("Joker", R.drawable.jocker, 4),
        Movie("Hustle", R.drawable.hustle, 3)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movie_pager.apply {
            adapter = PagerAdapter(requireContext(),movieList,PageAdapterHelper.ADAPTER_TYPE_TOP.value)
            offscreenPageLimit = 3
            pageMargin = 24
            clipChildren = false
            setPageTransformer(false,CarouselEffectTransformer(requireContext()))
        }

        viewPagerBackground.apply {
            adapter = PagerAdapter(requireContext(),movieList,PageAdapterHelper.ADAPTER_TYPE_BOTTOM.value)
        }

        movie_pager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{

            private var index = 0

            override fun onPageScrollStateChanged(state: Int) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    viewPagerBackground.currentItem = index
                }
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                val width = viewPagerBackground.width
                viewPagerBackground.scrollTo((width * position + width * positionOffset).toInt(), 0)
            }

            override fun onPageSelected(position: Int) {
                index = position
            }

        })


    }

}