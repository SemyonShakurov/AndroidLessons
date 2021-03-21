package com.mscorp.lab15

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var edit1: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edit1 = findViewById(R.id.editText)
        val save = getSharedPreferences("SAVE",0)
        edit1.setText(save.getString("text",""))

        val arrayList = arrayListOf<String>()

        val db = openOrCreateDatabase("DB", MODE_PRIVATE, null)
        db.execSQL("CREATE TABLE IF NOT EXISTS MyTable (Name VARCHAR);")
        val cursor = db.rawQuery("SELECT * FROM MyTable", null)
        cursor.moveToFirst()
        while (cursor.moveToNext())
            arrayList.add(cursor.getString(cursor.getColumnIndex("Name")))

        cursor.close()
        db.close()


        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList)

        findViewById<ListView>(R.id.list_view).apply {
            this.adapter = adapter
            onItemClickListener = (AdapterView.OnItemClickListener { parent, view, position, id ->
                val content = adapter.getItem(position)
                with(openOrCreateDatabase("DB", MODE_PRIVATE, null)) {
                    this.execSQL("DELETE FROM MyTable WHERE Name='${content}';")
                    this.close()
                }
                adapter.remove(content)
            })
        }

        findViewById<Button>(R.id.button).apply {
            setOnClickListener {
                with(openOrCreateDatabase("DB", MODE_PRIVATE, null)) {
                    this.execSQL("INSERT INTO MyTable VALUES ('${edit1.text}');")
                    this.close()
                }

                adapter.add(edit1.text.toString())
                edit1.text = SpannableStringBuilder("")
            }
        }
    }

    override fun onStop() {
        super.onStop()
        val save = getSharedPreferences("SAVE", 0)
        val editor = save.edit()
        editor.putString("text", edit1.text.toString())
        editor.commit()
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
        if (id == R.id.settings) {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}