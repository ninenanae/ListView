@file:Suppress("DEPRECATION")

package com.example.listview

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SongsActivity : AppCompatActivity() {

    private lateinit var songsListView: ListView
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_songs)

        songsListView = findViewById(R.id.songsListView)

        val songs = intent.getSerializableExtra("songs") as List<Pair<String, Int>>
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, songs.map { it.first })
        songsListView.adapter = adapter

        songsListView.setOnItemClickListener { _, _, position, _ ->
            val selectedSong = songs[position]
            playSong(selectedSong.second)
        }
    }

    private fun playSong(resourceId: Int) {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(this, resourceId)
        mediaPlayer?.start()
        Toast.makeText(this, "Playing song", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
    }
}