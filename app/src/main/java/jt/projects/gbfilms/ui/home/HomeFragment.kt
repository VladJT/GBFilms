package jt.projects.gbfilms.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import jt.projects.gbfilms.databinding.FragmentHomeBinding
import jt.projects.gbfilms.model.Film
import jt.projects.gbfilms.model.FilmData
import jt.projects.gbfilms.ui.MainActivity
import jt.projects.gbfilms.utils.ViewModelNotInitException
import jt.projects.gbfilms.utils.showSnackbar
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = HomeFragment()
    }

//    private val viewModel by lazy {
//        ViewModelProvider(requireActivity())[HomeViewModel::class.java] // переживает создание активити
//    }

    // private val viewModel: HomeViewModel by lazy { getKoin().get() }
    private val viewModel: HomeViewModel by activityViewModel()

    private val filmsAdapter by lazy { FilmsAdapter(::onItemClick) }

    private fun onItemClick(data: Film) {
        (requireActivity() as MainActivity).showFilmDetails(data)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }


    private fun initUi() {
        if (binding.rvFilmList.adapter != null) {
            throw ViewModelNotInitException
        }

        viewModel.liveDataToObserve.observe(viewLifecycleOwner) {
            renderData(it)
        }

        binding.rvFilmList.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = filmsAdapter
        }

        binding.buttonSearch.setOnClickListener {
            viewModel.loadFilmsBySearchText(binding.editTextSearch.text.toString())
        }
    }

    private fun renderData(data: FilmData) {
        when (data) {
            is FilmData.Success -> {
                binding.loadingFrameLayout.isVisible = false
                binding.editTextSearchLayout.error = null
                filmsAdapter.setData(data.filmList)
                if (data.filmList.isEmpty()) {
                    showSnackbar("Не найдено подходящих фильмов")
                }
            }

            is FilmData.Loading -> {
                binding.loadingFrameLayout.isVisible = true
            }

            is FilmData.Error -> {
                binding.loadingFrameLayout.isVisible = false
                binding.editTextSearchLayout.error = data.error.message
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.clear()
        _binding = null
    }
}