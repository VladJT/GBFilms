package jt.projects.gbfilms.ui.details

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import coil.load
import jt.projects.gbfilms.databinding.FragmentDetailsBinding
import jt.projects.gbfilms.model.FilmDetailsData
import jt.projects.gbfilms.repository.dto.details.DetailsDTO
import jt.projects.gbfilms.utils.FILM_ID_KEY
import jt.projects.gbfilms.utils.LOG_TAG
import jt.projects.gbfilms.utils.showSnackbar
import org.koin.android.ext.android.getKoin


class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = DetailsFragment()
    }

    private val viewModel: DetailsViewModel by lazy { getKoin().get() }

    private val actorsAdapter by lazy { ActorsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }


    private fun initUi() {
        val filmId = arguments?.getString(FILM_ID_KEY)

        viewModel.liveDataToObserve.observe(viewLifecycleOwner) {
            renderData(it)
        }
        if (filmId != null) {
            viewModel.loadFilmDetailsById(filmId)
        }

        binding.rvActors.apply {
            adapter = actorsAdapter
        }
    }

    private fun renderData(data: FilmDetailsData) {
        when (data) {
            is FilmDetailsData.Success -> {
                binding.loadingFrameLayout.isVisible = false
                showData(data.filmData)
                actorsAdapter.setData(data.filmData.actorList)
            }

            is FilmDetailsData.Loading -> {
                binding.loadingFrameLayout.isVisible = true
            }

            is FilmDetailsData.Error -> {
                binding.loadingFrameLayout.isVisible = false
                showSnackbar(data.error.message.toString())
            }
        }
    }

    private fun showData(data: DetailsDTO) {
        with(binding) {
            tvRating.text = "⭐ ${data.imDbRating} IMDB rating"
            tvTitle.text = data.fullTitle
            tvDescription.text = data.plotLocal
            tvDescription.movementMethod = ScrollingMovementMethod()

            tvReleaseDate.text = "Дата выхода: ${data.releaseDate}"
            tvRuntimeMins.text = "Длительность: ${data.runtimeStr}"
            tvAwards.text = "Награды: ${data.awards}"

            try {
                val mRating = data.metacriticRating?.toFloat()
                if (mRating == null) {
                    ratingCircle.progress = 0f
                    tvMetacritic.text = "n/o"
                } else {
                    ratingCircle.progress = mRating
                    tvMetacritic.text = mRating.toInt().toString()
                }

            } catch (e: NumberFormatException) {
                Log.d(LOG_TAG, e.message.toString())
            }

            ivImage.load(data.image) {
                error(android.R.drawable.ic_delete)
            }

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.clear()
        _binding = null
    }
}