package com.sg.mobile_remote

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sg.mobile_remote.commands.Start
import com.sg.mobile_remote.controls.ButtonStart


class MainActivity : AppCompatActivity() {
    private var _buttonStart : ButtonStart? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        _buttonStart = ButtonStart(this)
    }

    fun onStartService(view: View?) {
        _buttonStart?.click()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        onStartService(null)
    }
}