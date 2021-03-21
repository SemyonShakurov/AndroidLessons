package com.mscorp.lab9

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.text1)
        registerForContextMenu(textView)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.contextmenu, menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.color_red) {
            val textView = findViewById<TextView>(R.id.text1)
            textView.setTextColor(Color.parseColor("red"))
        }
        if (id == R.id.color_black) {
            val textView = findViewById<TextView>(R.id.text1)
            textView.setTextColor(Color.parseColor("black"))
        }
        return super.onContextItemSelected(item)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        if (id == R.id.show_text) {
            if (item.isChecked) {
                val textView = findViewById<TextView>(R.id.text1)
                textView.visibility = TextView.VISIBLE
                item.isChecked = false
            } else {
                val textView = findViewById<TextView>(R.id.text1)
                textView.visibility = TextView.INVISIBLE
                item.isChecked = true
            }
        } else if(id == R.id.show_name) {
            Toast.makeText(this, R.string.fio, Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}