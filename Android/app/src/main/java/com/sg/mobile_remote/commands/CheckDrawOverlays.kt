package com.sg.mobile_remote.commands

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity

class CheckDrawOverlays(private val context: AppCompatActivity) : Command, CommandsChain() {
    override fun run() {
        if (Settings.canDrawOverlays(context)) {
            next()
        }
        else {
            val uri = Uri.parse("package:" + context.packageName)
            val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, uri)
            context.startActivityForResult(intent, 1411)
        }
    }
}