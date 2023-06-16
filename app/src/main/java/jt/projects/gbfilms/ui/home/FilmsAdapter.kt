package jt.projects.gbfilms.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import jt.projects.gbfilms.R
import jt.projects.gbfilms.databinding.ItemFilmBinding
import jt.projects.gbfilms.model.Film

class FilmsAdapter(
    private var onItemClicked: ((String) -> Unit),
) : RecyclerView.Adapter<FilmsAdapter.FilmViewHolder>() {

    private var data: List<Film> = arrayListOf()

    // Метод передачи данных в адаптер
    fun setData(data: List<Film>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val binding = ItemFilmBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size


    inner class FilmViewHolder(private val binding: ItemFilmBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Film) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                with(binding) {
                    tvRank.text = data.rank
                    tvTitle.text = data.title
                    tvDescription.text = data.description
                    if (data.imDbRating.isNotBlank()) {
                        tvImdbRating.text = "⭐ ${data.imDbRating} IMDB rating"
                    }else {
                        tvImdbRating.text = "click for details"
                    }

                    ivImage.load(data.imageUrl) {
                        error(R.drawable.baseline_image_not_supported_24)
                    }

                    this.root.setOnClickListener {
                        onItemClicked(data.id)
                    }
                }

            }
        }
    }
}