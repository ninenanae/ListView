package com.example.listview

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var playlistListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playlistListView = findViewById(R.id.playlistListView)

        val playlists = listOf(

            Playlist("LAZZY2WICE - 12 gauge slug", listOf("LAZZY2WICE - Christ", "LAZZY2WICE - Brdm", "LAZZY2WICE - Nuke")),
            Playlist("CUPSIZE - Как испортить вечеринку?", listOf("CUPSIZE - Юра, Юра", "CUPSIZE - Я схожу с ума", "CUPSIZE - Травматика")),
            Playlist("Kai Angel - Heavy Metal 2", listOf("Kai Angel - Ice + Alabaster", "Kai Angel - Ринопластика", "Kai Angel - 2017"))
        )

        val adapter = ArrayAdapter(this, R.layout.playlist_item, playlists.map { it.name })
        playlistListView.adapter = adapter

        playlistListView.setOnItemClickListener { _, _, position, _ ->
            val selectedPlaylist = playlists[position]
            val intent = Intent(this, SongsActivity::class.java).apply {
                putExtra("songs", ArrayList(selectedPlaylist.songs))
            }
            startActivity(intent)
        }
    }
}