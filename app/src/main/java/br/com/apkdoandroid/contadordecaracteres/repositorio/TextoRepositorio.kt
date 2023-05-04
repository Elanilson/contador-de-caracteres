package br.com.apkdoandroid.contadordecaracteres.repositorio

import android.content.ContentValues
import android.content.Context
import android.util.Log
import br.com.apkdoandroid.contadordecaracteres.ententies.Texto
import br.com.apkdoandroid.contadordecaracteres.repositorio.banco.DB
import java.lang.Exception

class TextoRepositorio private constructor(context : Context){
    private  val banco : DB = DB(context)

    companion object {
        private lateinit var repositorio : TextoRepositorio

        fun getInstace(context: Context) : TextoRepositorio{
            if(!::repositorio.isInitialized){
                repositorio = TextoRepositorio(context)
            }
            return repositorio
        }
    }

    fun insert(texto: Texto) : Long {
            var db = banco.writableDatabase

             try{
                val values = ContentValues()
                values.put("texto",texto.texto)
               var id = db.insert("conversa",null,values)
                Log.d("Banco","Salvo com sucesso")
                return id
            }catch (e : Exception){
                println(e.message)
               return 0L
            }finally {
                // db.close()
            }


    }

    fun update(texto: Texto) : Boolean {
        var db = banco.writableDatabase
        if(texto.id != 0L){
            return try{
                val values = ContentValues()
                values.put("texto",texto.texto)

                // Critério de seleção
                val selection = "id = ?"
                val args = arrayOf(texto.id.toString())

                db.update("conversa",values,selection,args)
                Log.d("Banco","Atualizado com sucesso")
                true
            }catch (e : Exception){
                println(e.message)
                false
            }finally {
                //  db.close()
            }

    }else{
        return false
        }
    }

    fun get(id : Long) : Texto?{
        var convidado : Texto? = null
        try{
            val db = banco.readableDatabase

            //projecao
            val projecao = arrayOf("texto","dateCreate")

            //filtro
            val where = "id = ?"
            val whereArgs = arrayOf(id.toString())

            val cursor = db.query("conversa",projecao, where,whereArgs,null,null,null )

            //verficar dados

            if(cursor != null && cursor.count > 0){
                cursor.moveToFirst()

                var texto = cursor.getString(cursor.getColumnIndexOrThrow("texto"))
                var dateCreate = cursor.getString(cursor.getColumnIndexOrThrow("dateCreate"))

                convidado = Texto(id,texto,dateCreate)
            }
            cursor.close()
            return  convidado
        }catch (e : Exception){
            println( e.message)

        }finally {
          //  banco.close()
        }

        return null
    }
    fun getAll() : List<Texto>{
        val lista : MutableList<Texto> = arrayListOf()
        val db = banco.readableDatabase
        return try{
            val cursor = db.rawQuery("select * from conversa",null)

            if(cursor != null && cursor.count > 0){
                while (cursor.moveToNext()){
                    val id  = cursor.getLong(cursor.getColumnIndexOrThrow("id"))
                    val texto = cursor.getString(cursor.getColumnIndexOrThrow("texto"))
                    val dataCreate = cursor.getString(cursor.getColumnIndexOrThrow("dateCreate"))

                    lista.add(Texto(id,texto,dataCreate))
                }

            }
            cursor.close()

            lista
        }catch (e : Exception){
            println(e.message)
            lista
        }finally {
          //  db.close()
        }
    }

    fun delete(id: Long): Boolean {
        return try {
            val db = banco.writableDatabase
            val selection = "id = ?"
            val args = arrayOf(id.toString())

            db.delete("conversa", selection, args)

            true
        } catch (e: Exception) {
            false
        }
    }
}