package br.com.apkdoandroid.contadordecaracteres.view.holder

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.apkdoandroid.contadordecaracteres.R
import br.com.apkdoandroid.contadordecaracteres.ententies.Texto
import br.com.apkdoandroid.contadordecaracteres.interfaces.OnTextoListener

class TextoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var text = itemView.findViewById<TextView>(R.id.textViewNome)
    var chat = itemView.findViewById<LinearLayout>(R.id.chat)
    var delete = itemView.findViewById<ImageView>(R.id.imageViewDelete)

    fun bind(texto: Texto,listener : OnTextoListener){
        text.setText(texto.texto)
        chat.setOnClickListener { listener.onClick(texto) }
        delete.setOnClickListener { listener.onDelete(texto) }
    }
}