package jt.projects.gbfilms.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import jt.projects.gbfilms.databinding.ActivityMainBinding
import jt.projects.gbfilms.model.Film
import jt.projects.gbfilms.ui.details.DetailsFragment
import jt.projects.gbfilms.ui.home.HomeFragment
import jt.projects.gbfilms.utils.FILM_ID_KEY

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val fragmentLifecycleCallback = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
            super.onFragmentResumed(fm, f)
            when (f) {
                is HomeFragment -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                }

                else -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentLifecycleCallback, false)

        if (savedInstanceState == null) {
         //   navigateToFragment(HomeFragment.newInstance(), false)

            navigateToFragment(DetailsFragment.newInstance().apply {
                arguments = Bundle().apply { putString(FILM_ID_KEY, "tt0110413") }
            }, isAddToBackStack = true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                supportFragmentManager.popBackStack()
            }
        }
        return true
    }

    private fun navigateToFragment(fragment: Fragment, isAddToBackStack: Boolean = false) {
        // проверяем,что фрагмент еще не запущен
        if (supportFragmentManager.fragments.find { it.javaClass == fragment::class.java } != null) return

        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(binding.fragmentContainer.id, fragment)

        if (isAddToBackStack) {
            beginTransaction.addToBackStack(null)
        }
        beginTransaction.commit()
    }

    fun showFilmDetails(data: Film) {
        navigateToFragment(DetailsFragment.newInstance().apply {
            arguments = Bundle().apply { putString(FILM_ID_KEY, data.id) }
        }, isAddToBackStack = true)
    }
}