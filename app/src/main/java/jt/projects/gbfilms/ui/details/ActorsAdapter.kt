package jt.projects.gbfilms.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import jt.projects.gbfilms.databinding.ItemActorBinding
import jt.projects.gbfilms.repository.dto.details.Actor

class ActorsAdapter() :
    RecyclerView.Adapter<ActorsAdapter.ActorViewHolder>() {

    private var data: List<Actor> = arrayListOf()

    // Метод передачи данных в адаптер
    fun setData(data: List<Actor>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val binding = ItemActorBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ActorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size


    inner class ActorViewHolder(private val binding: ItemActorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Actor) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                with(binding) {
                    tvName.text = data.name
                    tvAsCharacter.text = data.asCharacter

                    ivAvatar.load(data.image) {
                        error(android.R.drawable.ic_delete)
                    }
                }

            }
        }
    }
}