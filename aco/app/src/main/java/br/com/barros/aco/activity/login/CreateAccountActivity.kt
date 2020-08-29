package br.com.barros.aco.activity.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import br.com.barros.aco.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var etFirstName: EditText
    private lateinit var etLastName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnCreate: Button

    private lateinit var mDatabaseReference: DatabaseReference
    private lateinit var mDatabase: FirebaseDatabase
    private lateinit var mAuth: FirebaseAuth

    private val TAG = "CreateAccountActivity"

    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var email: String
    private lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        initComponents()
        setVariables()
        initDatabase()
        setOnClick()
    }

    private fun initComponents() {
        etFirstName = findViewById(R.id.edit_text_firstName)
        etLastName = findViewById(R.id.edit_text_lastName)
        etEmail = findViewById(R.id.edit_text_email)
        etPassword = findViewById(R.id.edit_text_password)
        btnCreate = findViewById(R.id.btn_create)
    }

    private fun initDatabase() {
        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase.reference.child("Users")
        mAuth = FirebaseAuth.getInstance()
    }

    private fun setVariables() {
        firstName = etFirstName.text.toString()
        lastName = etLastName.text.toString()
        email = etEmail.text.toString()
        password = etPassword.text.toString()
    }

    private fun setOnClick() {
        btnCreate.setOnClickListener { createAccount() }
    }

    private fun createAccount() {
        mAuth.createUserWithEmailAndPassword(etEmail.text.toString(), etPassword.text.toString()).addOnCompleteListener(this) { task ->
            if(task.isSuccessful) {
                val userID = mAuth.currentUser?.uid
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
