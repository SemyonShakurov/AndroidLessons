package com.mscorp.lab5

import android.app.ListActivity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class MainActivity : ListActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listAdapter = MyAdapter(
            this,
            android.R.layout.simple_list_item_1,
            R.id.textView2,
            resources.getStringArray(R.array.images)
        )
    }

    open class MyAdapter(
        context: Context,
        resource: Int,
        textViewResourceId: Int,
        string: Array<String>
    ) : ArrayAdapter<String>(context, resource, textViewResourceId, string) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val row = inflater.inflate(R.layout.list_item, parent, false)
            val items = context.resources.getStringArray(R.array.images)
            val image = row.findViewById<ImageView>(R.id.imageView)
            val text = row.findViewById<TextView>(R.id.textView2)
            text.text = items[position]

            when (items[position]) {
                "Image 1" -> image.setImageResource(R.drawable.image1)
                "Image 2" -> image.setImageResource(R.drawable.image2)
                "Image 3" -> image.setImageResource(R.drawable.image3)
                "Image 4" -> image.setImageResource(R.drawable.image4)
            }

            return row
        }
    }
}