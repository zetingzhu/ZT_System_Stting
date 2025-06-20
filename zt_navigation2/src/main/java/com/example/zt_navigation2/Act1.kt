package com.example.zt_navigation2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.log

class Act1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_act1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initView();
    }

    private fun initView() {
        var button1: Button = findViewById(R.id.button1)
        button1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("navigateToDestinationId", R.id.navigation_dashboard)
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(intent)
            finish()
        }

        findViewById<Button>(R.id.button5).setOnClickListener {
            val intent = Intent(this@Act1, Act2::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.button7).setOnClickListener {
            Log.d("ZZZ", "打印栈中信息：" + MyApplication.getInstance().sActivityList.toString())


            Log.w("ZZZ", "打印栈中信息 11 ：" + UUUUU.getActivitiesByReflection() )

        }
    }
}