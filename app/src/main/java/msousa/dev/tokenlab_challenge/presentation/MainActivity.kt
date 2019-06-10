package msousa.dev.tokenlab_challenge.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import msousa.dev.tokenlab_challenge.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {

    }
}
