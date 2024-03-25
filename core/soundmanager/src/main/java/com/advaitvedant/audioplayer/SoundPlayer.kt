package com.advaitvedant.audioplayer

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaMetadataRetriever
import android.media.SoundPool
import android.net.Uri
import android.os.Handler
import android.os.Looper
import androidx.annotation.RawRes
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class SoundPlayer(@ApplicationContext val context: Context){
    private val soundPool: SoundPool
    private val soundMap: MutableMap<String, Int> = mutableMapOf()
    private val soundDuration: MutableMap<String, Long> = mutableMapOf()
    private val soundQueue: MutableList<String> = mutableListOf()
    private var soundsLoaded = 0
    private var playing = false
    private val _loadedAllSounds = MutableStateFlow(true)
    val loadedAllSounds = _loadedAllSounds.asStateFlow()
    private fun loadDuration(file: String) {
        val retriever = MediaMetadataRetriever()
        try {
            retriever.setDataSource(file)
            val durationStr = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
            soundDuration[file] = durationStr?.toLong() ?: 0L
        } finally {
            retriever.release()
        }
    }
    private fun loadDuration(@RawRes soundResId: Int){
        val retriever = MediaMetadataRetriever()
        try {
            retriever.setDataSource(context, Uri.parse("android.resource://${context.packageName}/${soundResId}"))
            val durationStr = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
            soundDuration[soundResId.toString()] = durationStr?.toLong() ?: 0L
        } finally {
            retriever.release()
        }
    }
    init {
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
            .build()
        soundPool = SoundPool.Builder()
            .setMaxStreams(MAX_STREAMS)
            .setAudioAttributes(audioAttributes)
            .build()
        soundPool.setOnLoadCompleteListener { _, _, _ ->
            soundsLoaded ++
            if (soundsLoaded == soundMap.size){
                _loadedAllSounds.value = true
            }
        }
    }
    fun preload(@RawRes soundResId: Int){
        if (soundMap.containsKey(soundResId.toString())) {
            return
        }
        _loadedAllSounds.value = false
        val soundId  = soundPool.load(context, soundResId, PRIORITY)
        soundMap[soundResId.toString()] = soundId
        loadDuration(soundResId)
    }
    fun preload(file: String){
        if (soundMap.containsKey(file)){
            return
        }
        _loadedAllSounds.value = false
        val soundId = soundPool.load(file, PRIORITY)
        soundMap[file] = soundId
        loadDuration(file)
    }

    fun enqueue(@RawRes soundResId: Int){
        enqueue(soundResId.toString())
        if (soundQueue.size >= 1 && !playing){
            playNext()
        }
    }
    fun enqueue(file: String){
        soundQueue.add(file)
        if (soundQueue.size >= 1 && !playing){
            playNext()
        }
    }

    private fun playNext(){
        if (soundQueue.isNotEmpty()){
            val soundKey = soundQueue.removeFirst()
            soundMap[soundKey]?.let { sound ->
                playing = true
                soundPool.play(sound, VOLUME, VOLUME, PRIORITY, LOOP, RATE)
            }
            Handler(Looper.getMainLooper()).postDelayed({
                playNext()
            }, soundDuration[soundKey] ?: 0L)
        } else {
            playing = false
        }
    }

    fun stop(){
        soundPool.autoPause()
        soundQueue.clear()
    }
    fun reset(){
        stop()
        soundMap.forEach { (_, soundId) ->
            soundPool.unload(soundId)
        }
        _loadedAllSounds.value = false
        soundMap.clear()
    }
    fun release(){
        soundPool.release()
    }
    companion object {
        private const val MAX_STREAMS = 1
        private const val VOLUME = 1f
        private const val PRIORITY = 1
        private const val LOOP = 0
        private const val RATE = 1f
    }
}