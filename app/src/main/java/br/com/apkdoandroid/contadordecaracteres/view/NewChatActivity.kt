package br.com.apkdoandroid.contadordecaracteres.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.ContentInfoCompat.Flags
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.apkdoandroid.contadordecaracteres.R
import br.com.apkdoandroid.contadordecaracteres.adapter.TextoAdapter
import br.com.apkdoandroid.contadordecaracteres.databinding.ActivityNewChatBinding
import br.com.apkdoandroid.contadordecaracteres.ententies.Texto
import br.com.apkdoandroid.contadordecaracteres.interfaces.OnTextoListener
import br.com.apkdoandroid.contadordecaracteres.view.model.NewChatViewModel

class NewChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewChatBinding
    private lateinit var viewModel : NewChatViewModel
    private var adapter = TextoAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewChatBinding.inflate(layoutInflater)
        setContentView(binding.root)



        viewModel = ViewModelProvider(this).get(NewChatViewModel::class.java)


        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = adapter

        binding.textViewNewChat.setOnClickListener {
            var intent = Intent(this@NewChatActivity,MainActivity::class.java)
            startActivity(intent)
           // Toast.makeText(this@NewChatActivity,"click",Toast.LENGTH_SHORT).show()
        }

        val listener = object : OnTextoListener{

            override fun onClick(texto: Texto) {
                var intent = Intent(this@NewChatActivity,MainActivity::class.java)
                intent.putExtra("texto",texto)
                startActivity(intent)
            }

            override fun onDelete(texto: Texto) {
                viewModel.delete(texto.id)
            }
        }
        adapter.attackListener(listener)

       /* binding.textViewNewChat.setOnTouchListener { v, event ->
            when(event.action){
                MotionEvent.ACTION_DOWN -> {
                    var intent = Intent(this@NewChatActivity,MainActivity::class.java)
                    startActivity(intent)
                    binding.textViewNewChat.setBackgroundResource(R.drawable.background_button_new_selected)
                    binding.textViewNewChat.setTextColor(resources.getColor(R.color.white))
                    var icon = ContextCompat.getDrawable(this@NewChatActivity,
                        R.drawable.baseline_add_24_white
                    )
                    binding.textViewNewChat.setCompoundDrawablesWithIntrinsicBounds(icon,null,null,null)
                    up()

                }

                MotionEvent.ACTION_UP ->{
                    up()
                }
            }
           true
        }*/

        observe()
    }

    private fun observe() {
        viewModel.lista.observe(this){
            adapter.attackLista(it)
        }
        viewModel.resposta.observe(this){
            if(it.mensagem.equals("deletado")){
                viewModel.getAll()
            }else{
                Toast.makeText(this@NewChatActivity,"${it.status} - ${it.mensagem}",Toast.LENGTH_SHORT).show()

            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAll()
        up()
    }
    private fun up(){
        binding.textViewNewChat.setBackgroundResource(R.drawable.background_button_new)
        binding.textViewNewChat.setTextColor(resources.getColor(R.color.black))
        var icon = ContextCompat.getDrawable(this@NewChatActivity,
            R.drawable.baseline_add_24_black
        )
        binding.textViewNewChat.setCompoundDrawablesWithIntrinsicBounds(icon,null,null,null)
    }
}