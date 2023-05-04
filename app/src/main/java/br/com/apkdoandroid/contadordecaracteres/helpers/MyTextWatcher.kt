package br.com.apkdoandroid.contadordecaracteres.helpers

import android.widget.EditText
import android.widget.Toast
import android.text.Editable
import android.text.TextWatcher

class MyTextWatcher(val editText: EditText) : TextWatcher {
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // Exibe o Toast informando que houve uma alteração no EditText
        Toast.makeText(editText.context, "Texto alterado!", Toast.LENGTH_SHORT).show()
        println("onTextChanged $s")
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun afterTextChanged(s: Editable?) {}
}
