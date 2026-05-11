package com.example.quizapp_azizy;

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    //Step 1: Declaration
    var etLogin: EditText? = null
    var etPassword: EditText? = null
    var bLogin: Button? = null
    var tvRegister: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Step 2: Recuperation des ids
        etLogin = findViewById<View?>(R.id.etMail) as EditText
        etPassword = findViewById<View?>(R.id.etPassword) as EditText
        bLogin = findViewById<View?>(R.id.bLogin) as Button
        tvRegister = findViewById<View?>(R.id.tvRegister) as TextView
        //Step 3: Association de listeners
        bLogin!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                //Step 4: Traitement
                if (etLogin!!.getText().toString() == "toto" && etPassword!!.getText()
                        .toString() == "123"
                ) {
                    // Navigation vers le premier Quiz
                    val intent = Intent(this@MainActivity, Quiz1::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        getApplicationContext(),
                        "Login or password incorrect !",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
        tvRegister!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                //Step 4: Traitement
                startActivity(Intent(this@MainActivity, Register::class.java))
            }
        })
    }
}