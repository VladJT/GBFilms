package jt.projects.gbfilms.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import jt.projects.gbfilms.databinding.ActivityMainBinding
import jt.projects.gbfilms.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            navigateToFragment(HomeFragment.newInstance(), false)
        }
    }


    fun navigateToFragment(fragment: Fragment, isAddToBackStack: Boolean = false) {
        // проверяем,что фрагмент еще не запущен
        if (supportFragmentManager.fragments.find { it.javaClass == fragment::class.java } != null) return

        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(binding.fragmentContainer.id, fragment)

        if (isAddToBackStack) {
            beginTransaction.addToBackStack(null)
        }
        beginTransaction.commit()
    }
}