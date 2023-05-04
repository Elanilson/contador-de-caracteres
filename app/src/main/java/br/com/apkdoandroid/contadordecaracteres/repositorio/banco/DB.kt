package br.com.apkdoandroid.contadordecaracteres.repositorio.banco

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DB(context : Context) : SQLiteOpenHelper(context,NOME,null, VERSAO){

    companion object{
        private const val NOME = "contador"
        private const val VERSAO = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE conversa (id integer PRIMARY KEY AUTOINCREMENT, texto text,dateCreate NOT NULL DEFAULT CURRENT_TIMESTAMP);"
        )


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}
