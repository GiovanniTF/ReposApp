package br.com.giovanni.reposapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.giovanni.reposapp.api.Client
import br.com.giovanni.reposapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        recyclerMain.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerMain.layoutManager = linearLayoutManager

        Client.createEndpoint(this, recyclerMain)
    }
}
