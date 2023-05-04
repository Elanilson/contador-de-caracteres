package br.com.apkdoandroid.contadordecaracteres.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.apkdoandroid.contadordecaracteres.R
import br.com.apkdoandroid.contadordecaracteres.ententies.Texto
import br.com.apkdoandroid.contadordecaracteres.interfaces.OnTextoListener
import br.com.apkdoandroid.contadordecaracteres.view.holder.TextoViewHolder

class TextoAdapter : RecyclerView.Adapter<TextoViewHolder>() {
    private var lista : List<Texto> = listOf()
    private lateinit var listener : OnTextoListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextoViewHolder {
        var layout = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return  TextoViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: TextoViewHolder, position: Int) {
       holder.bind(lista.get(position),listener)
    }

    fun attackLista(lista : List<Texto> = listOf()){
        this.lista = lista
        notifyDataSetChanged()
    }

    fun attackListener(listener : OnTextoListener){
        this.listener = listener
    }
}