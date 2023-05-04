package br.com.apkdoandroid.contadordecaracteres.view.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.apkdoandroid.contadordecaracteres.ententies.Resposta
import br.com.apkdoandroid.contadordecaracteres.ententies.Texto
import br.com.apkdoandroid.contadordecaracteres.repositorio.TextoRepositorio

class NewChatViewModel(application: Application) : AndroidViewModel(application) {
    private val repositorio = TextoRepositorio.getInstace(application)

    private val _Resposta : MutableLiveData<Resposta> = MutableLiveData()
    var resposta : LiveData<Resposta> = _Resposta

    private val _Texto : MutableLiveData<Texto> = MutableLiveData()
    var texto : LiveData<Texto> = _Texto

    private val _Lista : MutableLiveData<List<Texto>> = MutableLiveData()
    var lista : LiveData<List<Texto>> = _Lista

    private val _Id : MutableLiveData<Long> = MutableLiveData()
    var id : LiveData<Long> = _Id


    fun update(texto: Texto?){
        if(texto != null && !texto.texto.isNullOrEmpty()){
            println("ID = ${texto.id}")
            if(texto.id == 0L){
                var  id = repositorio.insert(texto)
                if(id != 0L){
                    _Id.value = id
                    _Resposta.value = Resposta(true,"salvo com sucesso")
                }else{
                    _Resposta.value = Resposta(false,"Não foi possivel salvar")
                }
            }else{
                if(repositorio.update(texto) ){
                    _Resposta.value = Resposta(true,"salvo com sucesso")
                }else{
                    _Resposta.value = Resposta(false,"Não foi possivel atualizar ${texto.id}")
                }
            }
        }else{
            _Resposta.value = Resposta(false,"Nenhum dado para armazenar")
        }
    }
    fun getAll(){
        _Lista.value = repositorio.getAll()
    }
    fun get(id : Long){
        _Texto.value = repositorio.get(id)
    }
    fun delete(id: Long){
        if(repositorio.delete(id)){
            _Resposta.value = Resposta(true,"deletado")
        }
    }
}