package com.iwex.mobilepartsshopstaff.presentation.utils

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

object FileUtil {

    const val FILENAME = "temp_image"

//    private val FILENAME: String
//        get() = "image${System.currentTimeMillis()}"

    @Throws(IOException::class)
    fun from(context: Context, uri: Uri): File {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val file = File(context.cacheDir, FILENAME)
        val outputStream = FileOutputStream(file)
        inputStream?.copyTo(outputStream)
        inputStream?.close()
        outputStream.close()
        return file
    }
}