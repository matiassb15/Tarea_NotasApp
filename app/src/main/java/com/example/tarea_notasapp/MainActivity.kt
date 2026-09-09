package com.example.tarea_notasapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tarea_notasapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var db : NotasDataBaseHelper
    private lateinit var notasAdapator : NotasAadptador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotasDataBaseHelper(this)

        notasAdapator = NotasAadptador(db.getAllNotas(),this)

        binding.notasRv.layoutManager = LinearLayoutManager(this)
        binding.notasRv.adapter = notasAdapator

        binding.FABAgregarNota.setOnClickListener {
            startActivity(Intent(applicationContext, AgregarNotaActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        notasAdapator.refrescarLista(db.getAllNotas())
    }
}