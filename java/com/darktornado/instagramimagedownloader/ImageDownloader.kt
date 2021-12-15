package com.darktornado.instagramimagedownloader

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import org.jsoup.Jsoup

class ImageDownloader(val ctx: Context, val url: String) {

    fun execute() {
        val url = Jsoup.connect(this.url)
            .userAgent("Googlebot/2.1 (+https://www.google.com/bot.html)")
            .followRedirects(false).get()
            .select("meta[property=og:image]").attr("content")
        val name = url.substringBefore("?").substringAfterLast("/")
        download(url, name)
    }

    fun download(url: String, name: String) {
        val uri = Uri.parse(url)
        val dm = DownloadManager.Request(uri)
        dm.setTitle("Instagram Image Downloader")
        dm.setDescription("Image is downloading...")
        dm.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, name)
        dm.setNotificationVisibility(1)
        val manager = ctx.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        manager.enqueue(dm);
    }

}