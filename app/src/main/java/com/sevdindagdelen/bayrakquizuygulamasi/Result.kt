package com.sevdindagdelen.bayrakquizuygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sevdindagdelen.bayrakquizuygulamasi.databinding.ActivityResultBinding

class Result : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dogru=intent.getIntExtra("dogruSayac",0)

        binding.textResult.text="$dogru DOGRU ${5-dogru} YANLIŞ"
        binding.textViewYuzdelik.text="Başarı: %${dogru * 100/5}"

        binding.buttonTekrar.setOnClickListener {
            startActivity(Intent(this@Result,QuizSayfasi::class.java))
            finish()
        }
    }
}