package br.com.apkdoandroid.contadordecaracteres.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.apkdoandroid.contadordecaracteres.databinding.ActivityMainBinding
import br.com.apkdoandroid.contadordecaracteres.ententies.Texto
import br.com.apkdoandroid.contadordecaracteres.helpers.ContadorTexto
import br.com.apkdoandroid.contadordecaracteres.view.model.NewChatViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel : NewChatViewModel
    private var totalCaracteres: Int = 0
    private var totalCaracteresSemEspaco: Int = 0
    private var totalPalavras: Int = 0
    private var totalPalavrasUnicas: Int = 0
    private var totalParagrafos: Int = 0
    private var totalLinhas: Int = 0
    private var totalNumeros: Int = 0
    private var totalVogais: Int = 0
    private var totalConsoantes: Int = 0
    private var totalPontuacao: Int = 0
    private var totalFrases: Int = 0
    private var totalEspacos: Int = 0
    private var totalLetrasMaiusculas: Int = 0
    private var totalLetrasMinusculas: Int = 0
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>
    private var text : Texto? = null
    private var id : Long = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonVoltar.setOnClickListener { finish() }

        val bundle = intent.extras
        if(bundle != null){
           if(Build.VERSION.SDK_INT >= 33){
               text =   bundle.getParcelable("texto",Texto::class.java)
            }else{
               text =    bundle.getParcelable("texto")
            }

            binding.editTextCampoTexto.setText(text?.texto)
            contador(text?.texto)
        }else{

            text = Texto()
        }

        viewModel = ViewModelProvider(this).get(NewChatViewModel::class.java)

        bottomSheetBehavior = BottomSheetBehavior.from(binding.frame)

        bottomSheetBehavior.setBottomSheetCallback( object : BottomSheetBehavior.BottomSheetCallback(){
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                // React to state change
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {

                    binding.editTextCampoTexto.isEnabled = true
                    //   Toast.makeText(binding.getRoot().getContext(), "STATE_COLLAPSED", Toast.LENGTH_SHORT).show();;
                } else if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                  //  binding.editTextCampoTexto.isEnabled = true
                    //Toast.makeText(binding.getRoot().getContext(), "STATE_EXPANDED", Toast.LENGTH_SHORT).show();;
                } else if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                 //   binding.editTextCampoTexto.isEnabled = true
                    // Toast.makeText(binding.getRoot().getContext(), "STATE_DRAGGING", Toast.LENGTH_SHORT).show();;
                } else if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                   // binding.editTextCampoTexto.isEnabled = true
                    //Toast.makeText(binding.getRoot().getContext(), "STATE_HIDDEN", Toast.LENGTH_SHORT).show();;
                } else if (newState == BottomSheetBehavior.STATE_HALF_EXPANDED) {
                   // binding.editTextCampoTexto.isEnabled = true
                    // Toast.makeText(binding.getRoot().getContext(), "STATE_HALF_EXPANDED", Toast.LENGTH_SHORT).show();;
                } else if (newState == BottomSheetBehavior.STATE_SETTLING) {
                   // binding.editTextCampoTexto.isEnabled = true
                    //Toast.makeText(binding.getRoot().getContext(), "STATE_SETTLING", Toast.LENGTH_SHORT).show();;
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }
        })




        binding.editTextCampoTexto.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(texto: CharSequence?, start: Int, before: Int, count: Int) {
                text?.texto = texto.toString()
                viewModel.update(text)
                contador(texto.toString())

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {print("beforeTextChanged $s")}
            override fun afterTextChanged(s: Editable?) {print("afterTextChanged $s")}
        })

        observe()
}

    private fun observe() {
        viewModel.texto.observe(this){
            binding.editTextCampoTexto.setText(it.texto)
        }
        viewModel.resposta.observe(this){
            Toast.makeText(this@MainActivity,"${it.status} - ${it.mensagem}", Toast.LENGTH_SHORT).show()
        }

        viewModel.id.observe(this){
            text?.id = it
        }
    }

    override fun onBackPressed() {

    }

    public fun vermais(v : View){
        binding.layoutInf.textViewContadorCaracteres.setText("$totalCaracteres")
        binding.layoutInf.textView2ContadorCaracteresSemEspacos.setText("$totalCaracteresSemEspaco")
        binding.layoutInf.textViewContadorDePalavras.setText("$totalPalavras")
        binding.layoutInf.textView2ContadorPalavrasUnicas.setText("$totalPalavrasUnicas")
        binding.layoutInf.textView2ContadorFrases.setText("$totalFrases")
        binding.layoutInf.textView2ContadorLinhas.setText("$totalLinhas")
        binding.layoutInf.textView2Contadornumeros.setText("$totalNumeros")
        binding.layoutInf.textView2ContadorVogais.setText("$totalVogais")
        binding.layoutInf.textView2ContadorConsoantes.setText("$totalConsoantes")
        binding.layoutInf.textView2ContadorPontuacao.setText("$totalPontuacao")
        binding.layoutInf.textView2ContadorMauscula.setText("$totalLetrasMaiusculas")
        binding.layoutInf.textViewContadorMinuscula.setText("$totalLetrasMinusculas")
        binding.layoutInf.textViewContadorPAragrafo.setText("$totalParagrafos")
        binding.layoutInf.textView2ContadorCarEspacos.setText("$totalEspacos")
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        binding.editTextCampoTexto.isEnabled = false
    }
    private fun contador(texto : String?){
        texto?.let {totalCaracteres  = ContadorTexto.contarCaracteres(texto.toString()) }
        texto?.let {totalCaracteresSemEspaco = ContadorTexto.contarCaracteresSemEspaco(texto.toString())}
        texto?.let {totalPalavras  = ContadorTexto.contarPalavras(texto.toString())}
        texto?.let {totalPalavrasUnicas  = ContadorTexto.contarPalavrasUnicas(texto.toString())}
        texto?.let {totalParagrafos = ContadorTexto.contarParagrafos(texto.toString())}
        texto?.let {totalLinhas  = ContadorTexto.contarLinhas(texto.toString())}
        texto?.let {totalNumeros = ContadorTexto.contarNumeros(texto.toString())}
        texto?.let {totalVogais  = ContadorTexto.contarVogais(texto.toString())}
        texto?.let {totalConsoantes  = ContadorTexto.contarConsoantes(texto.toString())}
        texto?.let {totalPontuacao  = ContadorTexto.contarPontuacao(texto.toString())}
        texto?.let {totalFrases  = ContadorTexto.contarFrases(texto.toString())}
        texto?.let {totalLetrasMaiusculas  = ContadorTexto.contarLetrasMaiusculas(texto.toString())}
        texto?.let {totalLetrasMinusculas  = ContadorTexto.contarLetrasMinusculas(texto.toString())}
        texto?.let {totalEspacos  = ContadorTexto.contarEspacos(texto.toString())}

        binding.textViewContadorPalavras.setText("$totalPalavras")
        binding.textViewContadorCaracteres.setText("$totalCaracteres")
        binding.textViewContadorParagrado.setText("$totalParagrafos")
        binding.textViewContadorFrases.setText("$totalFrases")

        if(texto.isNullOrEmpty()){
            totalCaracteres = 0
            totalCaracteresSemEspaco = 0
            totalPalavras = 0
            totalPalavrasUnicas = 0
            totalParagrafos = 0
            totalLinhas = 0
            totalNumeros = 0
            totalVogais = 0
            totalConsoantes = 0
            totalPontuacao = 0
            totalFrases = 0
            totalEspacos = 0
            totalLetrasMaiusculas = 0
            totalLetrasMinusculas = 0

            binding.textViewContadorPalavras.setText("$totalPalavras")
            binding.textViewContadorCaracteres.setText("$totalCaracteres")
            binding.textViewContadorParagrado.setText("$totalParagrafos")
            binding.textViewContadorFrases.setText("$totalFrases")

        }
    }
}