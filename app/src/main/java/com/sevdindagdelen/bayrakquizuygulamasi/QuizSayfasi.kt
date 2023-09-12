package com.sevdindagdelen.bayrakquizuygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.sevdindagdelen.bayrakquizuygulamasi.databinding.ActivityMainBinding
import com.sevdindagdelen.bayrakquizuygulamasi.databinding.ActivityQuizSayfasiBinding

class QuizSayfasi : AppCompatActivity() {
    private lateinit var binding: ActivityQuizSayfasiBinding

    private lateinit var sorular:ArrayList<Bayraklar>
    private lateinit var yanlisSecenekler:ArrayList<Bayraklar>
    private lateinit var dogruSoru:Bayraklar
    private lateinit var tumSeceneklerHashSet: HashSet<Bayraklar>  //hasSet eklenilen öğeler karıştırılır kendi içinde
    private lateinit var vt:VeriTabaniYardimcisi

    private var dogruSayac=0
    private var yanlisSayac=0
    private var soruSayac=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityQuizSayfasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vt= VeriTabaniYardimcisi(this)

        sorular=BayraklarDao().rastgele5bayrakGetir(vt)

        sorugetir()



        binding.buttonA.setOnClickListener {
            dogruKontrol(binding.buttonA)
            soruSayacKontrol()
        }
        binding.buttonB.setOnClickListener {
            dogruKontrol(binding.buttonB)
            soruSayacKontrol()
        }
        binding.buttonC.setOnClickListener {
            dogruKontrol(binding.buttonC)
            soruSayacKontrol()
        }
        binding.buttonD.setOnClickListener {
            dogruKontrol(binding.buttonD)
            soruSayacKontrol()
        }
    }

    fun sorugetir(){
        binding.soruSayisi.text="${soruSayac+1}. Soru"
        dogruSoru=sorular[soruSayac]

        binding.imageViewBayrak.setImageResource(resources.getIdentifier(dogruSoru.bayrak_resim,"drawable",packageName))


        yanlisSecenekler=BayraklarDao().rastgele3YanlisbayrakGetir(vt,dogruSoru.bayrak_id)

        tumSeceneklerHashSet= HashSet()

        tumSeceneklerHashSet.add(dogruSoru)
        tumSeceneklerHashSet.add(yanlisSecenekler[0])
        tumSeceneklerHashSet.add(yanlisSecenekler[1])
        tumSeceneklerHashSet.add(yanlisSecenekler[2])

        binding.buttonA.text=tumSeceneklerHashSet.elementAt(0).bayrak_ad
        binding.buttonB.text=tumSeceneklerHashSet.elementAt(1).bayrak_ad
        binding.buttonC.text=tumSeceneklerHashSet.elementAt(2).bayrak_ad
        binding.buttonD.text=tumSeceneklerHashSet.elementAt(3).bayrak_ad


    }

    fun soruSayacKontrol(){
        soruSayac++

        if (soruSayac !=5){
            sorugetir()
        }else{
            val intent=Intent(this@QuizSayfasi,Result::class.java)
            intent.putExtra("dogruSayac",dogruSayac)
            startActivity(intent)
            finish()
        }
    }

    fun dogruKontrol(button:Button){
        val buttonYazi=button.text.toString()
        val dogruCevap=dogruSoru.bayrak_ad.toString()

        if(buttonYazi==dogruCevap){
            dogruSayac++
        }else{
            yanlisSayac++
        }

        binding.Dogru.text="Doğru: $dogruSayac"
        binding.yanlis.text="Yanlış: $yanlisSayac"

    }


}