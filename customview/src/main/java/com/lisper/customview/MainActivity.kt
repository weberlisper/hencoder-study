package com.lisper.customview

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.lisper.customview.draw.graph.GraphActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun gotoGraphActivity(view: View?) {
        startActivity(Intent(this, GraphActivity::class.java))
    }
}