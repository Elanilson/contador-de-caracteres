package br.com.apkdoandroid.contadordecaracteres.ententies

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Texto (
    var id : Long = 0L,
    var texto : String = "",
    var dateCreate : String = "") : Parcelable {

   /* constructor(id: Long, texto: String, dateCreate: String) : this() {
        this.id = id
        this.texto = texto
        this.dateCreate = dateCreate
    }*/

    override fun toString(): String {
        return "Texto(id=$id, texto='$texto', dateCreate='$dateCreate')"
    }


}