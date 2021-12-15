package com.darktornado.instagramimagedownloader

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.os.StrictMode
import android.widget.*
import java.lang.Exception

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = LinearLayout(this)
        layout.orientation = 1
        val txt1 = TextView(this)
        txt1.text = "URL : "
        txt1.textSize = 17f
        txt1.setTextColor(Color.BLACK)
        layout.addView(txt1)
        val txt2 = EditText(this)
        txt2.hint = "Input URL..."
        layout.addView(txt2)
        val down = Button(this)
        down.text = "Download Image"
        down.setOnClickListener{
            try{
                StrictMode.enableDefaults()
                val url = txt2.text.toString()
                val id = ImageDownloader(this, url)
                id.execute()
                toast("Image is downloaded.")
            }catch (e: Exception){
                toast(e.toString())
            }
        }
        layout.addView(down)

        val pad = dip2px(16)
        layout.setPadding(pad, pad, pad, pad)
        val scroll = ScrollView(this)
        scroll.addView(layout)
        setContentView(scroll)
    }

    fun toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

    fun dip2px(dips: Int) = Math.ceil(dips * this.resources.displayMetrics.density.toDouble()).toInt()

}