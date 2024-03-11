package com.advaitvedant.audioplayer
import android.content.Context
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
        if (!batchDir.exists()){
            batchDir.mkdir()
        }
        coroutineScope {
            files.forEach { (fileName, fileUrl) ->
                launch {
                    try {
                        withContext(Dispatchers.IO) { // Perform network operations in IO dispatcher
                            val url = URL(fileUrl)
                            val connection = url.openConnection()
                            connection.connect()
                            val inputStream = BufferedInputStream(url.openStream())
                            val file = File(batchDir, fileName)
                            FileOutputStream(file).use { outputStream ->
                                inputStream.copyTo(outputStream)
                            }
                            inputStream.close()
                        }
                    } catch (e: Exception) {
                        Log.d("FILE DOWNLOAD", e.toString())
                    }
                }
            }
        }

    }
    fun deleteBatch(batch: Int): Boolean {
        return File(filesDir, batch.toString()).deleteRecursively()
    }
}