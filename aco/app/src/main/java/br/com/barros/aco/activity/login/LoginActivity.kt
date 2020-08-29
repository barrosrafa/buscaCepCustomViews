package br.com.barros.aco.activity.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.barros.aco.activity.dashboard.DashboardActivity
import br.com.barros.aco.R
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var btnCreateAccount: TextView
    private lateinit var btnForgotPassword: TextView
    private lateinit var btnLogin: Button

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        initComponents()
        setOnClick()
    }

    private fun initComponents() {
        btnCreateAccount = findViewById(R.id.txt_new_account)
        btnForgotPassword = findViewById(R.id.txt_forgot_password)
        etEmail = findViewById(R.id.edit_text_user)
        etPassword = findViewById(R.id.edit_text_password)
        btnLogin = findViewById(R.id.btn_sign_in)
    }

    private fun setOnClick() {
        btnCreateAccount.setOnClickListener { createAccount() }
        btnForgotPassword.setOnClickListener { forgotPassword() }
        btnLogin.setOnClickListener { loginUser() }
    }

    private fun forgotPassword() {
        val intent = Intent(this, ForgotPasswordActivity::class.java)
        intent.putExtra("keyIdentifier", "teste")
        startActivity(intent)
    }

    private fun createAccount() {
        val intent = Intent(this, CreateAccountActivity::class.java)
        intent.putExtra("keyIdentifier", "teste")
        startActivity(intent)
    }

    private fun redirectToDashboard() {
        val intent = Intent(this, DashboardActivity::class.java)
        intent.putExtra("keyIdentifier", "teste")
        startActivity(intent)
    }

    private fun loginUser() {
        mAuth.signInWithEmailAndPassword(etEmail.text.toString(), etPassword.text.toString()).addOnCompleteListener(this) { task ->
            if(task.isSuccessful) {
                redirectToDashboard()
            } else {
                Toast.makeText(this, task.exception?.message.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }
}
