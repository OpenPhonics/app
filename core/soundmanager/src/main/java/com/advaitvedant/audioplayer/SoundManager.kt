package com.advaitvedant.audioplayer
import android.content.Context
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.net.URL

class SoundManager(@ApplicationContext val context: Context){
    private val filesDir = context.filesDir

    fun exists(batch: Int, fileName: String): Boolean {
        val batchDir = File(filesDir, batch.toString())
        return File(batchDir, fileName).exists()
    }
    suspend fun downloadBatch(batch: Int, files: List<Pair<String, String>>){
        val batchDir = File(filesDir, batch.toString())
        coroutineScope {
            files.forEach { (fileName, fileUrl) ->
                launch{
                    try {
                        val url = URL(fileUrl)
                        val connection = url.openConnection()
                        connection.connect()
                        val inputStream = BufferedInputStream(url.openStream())
                        val file = File(batchDir, "${batch}/")
                        FileOutputStream(file).use { outputStream ->
                            inputStream.copyTo(outputStream)
                        }
                        inputStream.close()
                    } catch (e: Exception){
                        Log.d("FILE DOWNLOAD", e.message ?: "")
                    }
                }
            }
        }
    }
    fun deleteBatch(batch: Int): Boolean {
        return File(filesDir, batch.toString()).deleteRecursively()
    }
}