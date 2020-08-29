package br.com.barros.aco.activity.dashboard

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.barros.aco.model.Note
import kotlinx.android.synthetic.main.item_dashboard.view.*

class DashboardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    val image = itemView.imagedash
    val description = itemView.textdash

    fun bind(note: Note, clickListener: OnItemClickListener)
    {

        itemView.setOnClickListener {
            clickListener.onItemClicked(note)
        }
    }

    interface OnItemClickListener{
        fun onItemClicked(note: Note)
    }
}