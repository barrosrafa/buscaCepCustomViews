package br.com.barros.aco.activity.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.barros.aco.R
import br.com.barros.aco.model.Note


class DashboardAdapter(var notes: List<Note>, val itemClickListener: DashboardViewHolder.OnItemClickListener) : RecyclerView.Adapter<DashboardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): DashboardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dashboard, parent, false)
        return DashboardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(myHolder: DashboardViewHolder, position: Int) {
        val note = notes[position]
        myHolder.bind(note, itemClickListener)
    }
}