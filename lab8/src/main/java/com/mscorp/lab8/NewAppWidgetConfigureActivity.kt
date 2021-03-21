package com.mscorp.lab8

import android.app.Activity
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RemoteViews
import com.mscorp.lab8.databinding.NewAppWidgetConfigureBinding

/**
 * The configuration screen for the [NewAppWidget] AppWidget.
 */
class NewAppWidgetConfigureActivity : Activity() {

    private lateinit var context: NewAppWidgetConfigureActivity
    private var widgetID: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_app_widget_configure)

        setResult(RESULT_CANCELED)
        context = this
        val extras = intent.extras

        if (extras != null) {
            widgetID = extras.getInt(
                AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID
            )
            val widgetManager = AppWidgetManager.getInstance(context)
            val views = RemoteViews(context.packageName, R.layout.new_app_widget)
            val editText = findViewById<EditText>(R.id.appwidget_text)
            val button = findViewById<Button>(R.id.add_button)

            button.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(editText.text.toString()))
                val pending = PendingIntent.getActivity(context, 0, intent, 0)
                views.setOnClickPendingIntent(R.id.appwidget_text, pending)
                widgetManager.updateAppWidget(widgetID, views)
                val resultValue = Intent()
                resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID)
                setResult(RESULT_OK, resultValue)
                finish()
            }
        }
    }
}

private const val PREFS_NAME = "com.mscorp.lab8.NewAppWidget"
private const val PREF_PREFIX_KEY = "appwidget_"

// Write the prefix to the SharedPreferences object for this widget
internal fun saveTitlePref(context: Context, appWidgetId: Int, text: String) {
    val prefs = context.getSharedPreferences(PREFS_NAME, 0).edit()
    prefs.putString(PREF_PREFIX_KEY + appWidgetId, text)
    prefs.apply()
}

// Read the prefix from the SharedPreferences object for this widget.
// If there is no preference saved, get the default from a resource
internal fun loadTitlePref(context: Context, appWidgetId: Int): String {
    val prefs = context.getSharedPreferences(PREFS_NAME, 0)
    val titleValue = prefs.getString(PREF_PREFIX_KEY + appWidgetId, null)
    return titleValue ?: context.getString(R.string.appwidget_text)
}

internal fun deleteTitlePref(context: Context, appWidgetId: Int) {
    val prefs = context.getSharedPreferences(PREFS_NAME, 0).edit()
    prefs.remove(PREF_PREFIX_KEY + appWidgetId)
    prefs.apply()
}