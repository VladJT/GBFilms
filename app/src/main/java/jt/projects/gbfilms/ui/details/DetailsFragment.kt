package jt.projects.gbfilms.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import jt.projects.gbfilms.databinding.FragmentDetailsBinding
import jt.projects.gbfilms.utils.FILM_ID_KEY
import jt.projects.gbfilms.utils.showSnackbar


class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = DetailsFragment()
    }

//    private val viewModel by lazy {
//        ViewModelProvider(requireActivity())[HomeViewModel::class.java] // переживает создание активити
//    }

    //   private val viewModel: HomeViewModel by lazy { getKoin().get() }


//    private val filmsAdapter: FilmsAdapter by lazy { FilmsAdapter(::onItemClick) }


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
        val r = arguments?.getString(FILM_ID_KEY)
        showSnackbar(r.toString())
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}