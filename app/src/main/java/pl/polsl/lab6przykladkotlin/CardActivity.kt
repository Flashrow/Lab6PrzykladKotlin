package pl.polsl.lab6przykladkotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class CardActivity() : AppCompatActivity(){

    lateinit var presents: ArrayList<String>
    private lateinit var page: WebView

    @SuppressLint("JavascriptInterface", "SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        actionBar?.hide()
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        presents = intent.getStringArrayListExtra("presents") as ArrayList<String>
        Log.d("prezenty",presents.toString())

        page = WebView(this)
        page.settings.javaScriptEnabled=true
        page.addJavascriptInterface(this, "activity")
        page.loadUrl("file:///android_asset/card.html")

        setContentView(page)

        presents.forEach {
            page.post({page.loadUrl("javascript: addPresent(\"$it\")")})
        }
    }
}