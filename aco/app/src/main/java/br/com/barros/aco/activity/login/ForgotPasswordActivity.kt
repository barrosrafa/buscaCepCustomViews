package br.com.barros.aco.activity.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import br.com.barros.aco.R
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {

    private var TAG = "ForgotPasswordActivity"

    private lateinit var etEmail: EditText
    private lateinit var btnSubmit: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        mAuth = FirebaseAuth.getInstance()

        initComponents()
        setOnClick()
    }

    private fun initComponents() {
        etEmail = findViewById(R.id.edit_text_email)
    }

    private fun setOnClick() {
        btnSubmit.setOnClickListener { sendForgotPassword() }
    }

    private fun sendForgotPassword() {
        mAuth.sendPasswordResetEmail(etEmail.text.toString()).addOnCompleteListener { task ->
            if(task.isSuccessful) {
                Toast.makeText(this, "Email Enviado", Toast.LENGTH_LONG).show()
                redirectToLogin()
            }
        }
    }

    private fun redirectToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.putExtra("keyIdentifier", "teste")
        startActivity(intent)
    }
}
