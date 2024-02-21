package com.example.valorantagent

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.valorantagent.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FD4556")))

        val tvDetailName: TextView = findViewById(R.id.tv_agent_name)
        val tvDetailDescription: TextView = findViewById(R.id.tv_description)
        val ivDetailPhoto: ImageView = findViewById(R.id.img_agent_big_icon)
        val tvAbilities: TextView =  findViewById(R.id.tv_abilities)

        val dataAgent = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Agent>(EXTRA_DETAIL, Agent::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Agent>(EXTRA_DETAIL)
        }

        if (dataAgent != null) {
            tvDetailName.text = dataAgent.name
            tvDetailDescription.text = dataAgent.description
            Glide.with(this)
                .load(dataAgent.icon)
                .into(ivDetailPhoto)
            tvAbilities.text = dataAgent.abilities
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_share, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> {
                val shareIntent =  Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Bagikan informasi ini!")
                startActivity(shareIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

