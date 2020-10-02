package com.thanh.mvp_architecture.ui

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.thanh.mvp_architecture.R
import com.thanh.mvp_architecture.presenter.LoginPresenter
import com.thanh.mvp_architecture.presenter.impl.LoginPresenterImpl

class LoginActivity : AppCompatActivity(), LoginPresenter.View {
    private var dialog: ProgressDialog? = null
    private lateinit var loginPresenter: LoginPresenter
    private lateinit var edt_username: EditText
    private lateinit var edt_password: EditText
    private lateinit var btn_login: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
        btn_login.setOnClickListener { requestLogin(edt_username.text.toString(), edt_password.text.toString()) }
    }

    override fun onLoginPending() {
        onStartProcessBar("Đợi chút...")
    }

    override fun onLoginSuccess() {
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        finish()
    }

    override fun onLoginFail() {
        onStopProcessBar()
        Toast.makeText(this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show()
    }


    override fun onStartProcessBar(message: String?) {
        dialog!!.setMessage(message)
        dialog!!.setCancelable(false)
        dialog!!.show()
    }

    override fun onStopProcessBar() {
        dialog!!.dismiss()
    }

    override fun requestLogin(username: String?, password: String?) {
        loginPresenter.onLogin(username, password)
    }

    private fun init() {
        dialog = ProgressDialog(this)
        loginPresenter = LoginPresenterImpl(this, this)
        edt_username = findViewById(R.id.edt_username)
        edt_password = findViewById(R.id.edt_password)
        btn_login = findViewById(R.id.btn_login)
    }
}