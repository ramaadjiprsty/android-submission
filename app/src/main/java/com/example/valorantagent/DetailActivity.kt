package com.example.valorantagent

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val tvDetailName: TextView = findViewById(R.id.tv_agent_name)
        val tvDetailDescription: TextView = findViewById(R.id.tv_description)
        val ivDetailPhoto: ImageView = findViewById(R.id.img_agent_big_icon)


        val dataAgent = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Agent>(EXTRA_DETAIL, Agent::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Agent>(EXTRA_DETAIL)
        }

        if (dataAgent != null) {
            tvDetailName.text = dataAgent.name
        }
        if (dataAgent != null) {
            tvDetailDescription.text = dataAgent.description
        }
        if (dataAgent != null) {
            Glide.with(this)
                .load(dataAgent.icon)
                .into(ivDetailPhoto)
        };
    }
}