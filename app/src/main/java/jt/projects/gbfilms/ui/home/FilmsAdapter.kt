package jt.projects.gbfilms.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import jt.projects.gbfilms.databinding.ItemFilmBinding
import jt.projects.gbfilms.model.Film

class FilmsAdapter(
    private var onItemClicked: ((Film) -> Unit),
) :
    RecyclerView.Adapter<FilmsAdapter.FilmViewHolder>() {

    private var data: List<Film> = arrayListOf()

    // Метод передачи данных в адаптер
    fun setData(data: List<Film>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val binding = ItemFilmBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
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
                    tvTitle.text = data.title
                    ivImage.load(data.imageUrl) {
                        error(android.R.drawable.ic_delete)
                    }


                    this.root.setOnClickListener {
                        onItemClicked(data)
                    }
                }

            }
        }
    }
}