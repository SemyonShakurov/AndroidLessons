package com.mscorp.lab11

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            val context = this as Context
            val notification = Notification.Builder(context)
                .setContentTitle("Hi from Alex")
                .setContentText("Hey, listen")
                .setTicker("new notification")
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setSound(Uri.parse("android.resource://" + packageName + "/" + R.raw.audio))
                .build()

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.notify(0, notification)
        }
//        button.setOnClickListener {
//            val context = this
//            var newnotchan: NotificationChannel? = null
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                newnotchan = NotificationChannel(
//                    "mychannel1",
//                    "mychannel",
//                    NotificationManager.IMPORTANCE_HIGH
//                )
//                val audioAttributes = AudioAttributes.Builder()
//                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
//                    .setUsage(AudioAttributes.USAGE_MEDIA)
//                    .build()
//                newnotchan.setSound(
//                    Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + packageName + "/" + R.raw.audio),
//                    audioAttributes
//                )
//            }
//            val notificationManager =
//                applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
//                notificationManager?.createNotificationChannel(newnotchan!!)
//
//            val notification: Notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                Notification.Builder(context, "mychannel1")
//                    .setContentTitle("Hi from Alex")
//                    .setContentText("Hey, listen!")
//                    .setTicker("new notification!").setChannelId("mychannel1")
//                    .setSmallIcon(android.R.drawable.ic_dialog_alert).setOngoing(true)
//                    .build()
//            } else {
//                Notification.Builder(context)
//                    .setContentTitle("Hi from Alex").setContentText("Hey, listen!")
//                    .setTicker("new notification!").setSmallIcon(android.R.drawable.ic_dialog_alert)
//                    .setSound(Uri.parse("android.resource://" + packageName + "/" + R.raw.audio))
//                    .build()
//
//            }
//            notificationManager?.notify(0, notification)
//        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.about_program) {
            val dialog = AlertDialog.Builder(this)
            try {
                dialog.setMessage(
                    "$title версия " + packageManager.getPackageInfo(
                        packageName,
                        0
                    ).versionName
                            + "\r\n\nРабота с уведомлениями" +
                            "\r\n\nАвтор - Шакуров Семен Сергеевич, гр. БПИ194"
                )
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
            dialog.setTitle("О программе")
            dialog.setNeutralButton("OK") { _dialog, _ ->
                _dialog.dismiss()
            }
            dialog.setIcon(R.mipmap.ic_launcher_round)
            val alertDialog = dialog.create()
            alertDialog.show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}