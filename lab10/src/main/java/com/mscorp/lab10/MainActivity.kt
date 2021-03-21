package com.mscorp.lab10

import android.content.pm.PackageManager
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
            val dialog = AlertDialog.Builder(this)
            dialog.setMessage("Вы действительно хотите выйти?")
            dialog.setCancelable(false)
            dialog.setPositiveButton("Да") { _, _ ->
                this.finish()
            }
            dialog.setNegativeButton("Нет") { _dialog, _ ->
                _dialog.cancel()
            }
            val alertDialog = dialog.create()
            alertDialog.show()
        }
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
                            + "\r\n\nПрограмма с примером выполнения диалогового окна" +
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