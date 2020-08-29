package br.com.barros.aco.activity.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.com.barros.aco.R
import br.com.barros.aco.activity.MainActivity
import br.com.barros.aco.model.Note

class DashboardActivity : AppCompatActivity(), DashboardViewHolder.OnItemClickListener {

    private lateinit var rvDashboard: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        rvDashboard = findViewById(R.id.rv)
        rvDashboard.adapter = DashboardAdapter(notes(), this)
        val layoutManager = StaggeredGridLayoutManager(
            2, StaggeredGridLayoutManager.VERTICAL)
        rvDashboard.layoutManager = layoutManager
    }

    private fun notes(): List<Note> {
        return listOf(Note("Buscar CEP", "Buscar CEP"), Note("Financiamento", "Financiamento"))
    }

    override fun onItemClicked(user: Note) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("keyIdentifier", "teste")
        startActivity(intent)
    }
}
