package com.example.tarea_notasapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tarea_notasapp.databinding.ActivityAgregarNotaBinding
import com.redsystemstudio.notasapp_sqlite.NotasDataBaseHelper

class AgregarNotaActivity : AppCompatActivity() {

    private lateinit var binding:   ActivityAgregarNotaBinding
    private lateinit var db : NotasDataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAgregarNotaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotasDataBaseHelper(this)

        binding.ivGuardarNota.setOnClickListener {
            val titulo = binding.etTitulo.text.toString()
            val descripcion = binding.etDescripcion.text.toString()
            val nota = Nota(0, titulo, descripcion)
            db.insertNota(nota)
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finishAffinity()
            Toast.makeText(applicationContext, "Se ha agregado la nota", Toast.LENGTH_SHORT).show()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}