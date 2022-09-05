package ru.igor.marvelapp.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import ru.igor.marvelapp.R
import ru.igor.marvelapp.databinding.FragmentHeroListBinding
import ru.igor.marvelapp.ui.MainActivity

class HeroListFragment : Fragment() {
    var menu: Menu? = null
    lateinit var binding: FragmentHeroListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeroListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setupCollapsingToolbar()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupCollapsingToolbar() {
        (activity as MainActivity).apply {
            setSupportActionBar(binding.toolbar)
            setHasOptionsMenu(true)
        }
        binding.appBarLayout.addOnOffsetChangedListener(object :
            AppBarLayout.OnOffsetChangedListener {
            var scrollRange = -1
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    (activity as MainActivity).apply {
                        menu?.findItem(R.id.search)?.isVisible = true
                    }
                } else {
                    (activity as MainActivity).apply {
                        menu?.findItem(R.id.search)?.isVisible = false
                    }
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        this.menu = menu
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}