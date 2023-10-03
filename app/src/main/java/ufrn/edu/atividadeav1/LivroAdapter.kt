package ufrn.edu.atividadeav1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import model.Books

class LivroAdapter(private val livros: List<Books>) :
    RecyclerView.Adapter<LivroAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_livro, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val livro = livros[position]
        holder.bind(livro)
    }

    override fun getItemCount(): Int {
        return livros.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tituloTextView: TextView = itemView.findViewById(R.id.titulo)
        private val autorTextView: TextView = itemView.findViewById(R.id.autor)
        private val anoTextView: TextView = itemView.findViewById(R.id.ano)
        private val notaTextView: TextView = itemView.findViewById(R.id.nota)

        fun bind(livro: Books) {
            tituloTextView.text = livro.titulo
            autorTextView.text = livro.autor
            anoTextView.text = "Ano de Publicação: ${livro.ano}"
            notaTextView.text = "Nota: ${livro.nota}"
        }
    }
}
