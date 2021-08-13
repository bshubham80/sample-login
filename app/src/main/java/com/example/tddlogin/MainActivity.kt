package com.example.tddlogin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var login: Button;

    private var _username = MutableStateFlow("")
    private var _password = MutableStateFlow("")

    private var formIsValid = combine(_username, _password) { u, password ->
        return@combine u.isNotEmpty() and password.isNotEmpty()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<EditText>(R.id.edittext_username).apply {
            addTextChangedListener { setUsername(it.toString()) }
        }

        findViewById<EditText>(R.id.edittext_password).apply {
            addTextChangedListener { setPassword(it.toString()) }
        }

        val login: Button = findViewById<Button>(R.id.button_login).apply {
            setOnClickListener {
                startActivity(
                    Intent(this@MainActivity, HomeActivity::class.java)
                )
            }
        }

        lifecycleScope.launch {
            formIsValid.collect {
                login.isEnabled = it
            }
        }
    }

    public fun setUsername(username: String) {
        _username.value = username
    }

    public fun setPassword(pass: String) {
        _password.value = pass
    }
}