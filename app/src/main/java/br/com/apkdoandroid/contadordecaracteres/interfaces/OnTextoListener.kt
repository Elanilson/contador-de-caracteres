package br.com.apkdoandroid.contadordecaracteres.interfaces

import br.com.apkdoandroid.contadordecaracteres.ententies.Texto

interface OnTextoListener {
    fun onClick(texto: Texto)
    fun onDelete(texto: Texto)
}