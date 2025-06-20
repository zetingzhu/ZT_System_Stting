package com.example.zt_navigation2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Act2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_act2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initView()
    }

    private fun initView() {
        findViewById<Button>(R.id.button6).setOnClickListener {
            val intent = Intent(this@Act2, Act1::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT) // 尝试将ActA带到栈顶
            startActivity(intent)
        }

        findViewById<Button>(R.id.button7).setOnClickListener {
            Log.i("ZZZ", "打印栈中信息：" + MyApplication.getInstance().sActivityList.toString())


            Log.w("ZZZ", "打印栈中信息 22 ：" + UUUUU.getActivitiesByReflection())
        }

    }
}