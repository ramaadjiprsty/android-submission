package com.example.valorantagent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.valorantagent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var rvAgent: RecyclerView
    private val list = ArrayList<Agent>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvAgent.setHasFixedSize(true)

        list.addAll(listOfAgent)
        showRecyclerList()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val moveIntent = Intent(this@MainActivity, AboutPage::class.java)
                startActivity(moveIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private val listOfAgent: ArrayList<Agent>
        get() {
        val dataName = resources.getStringArray(R.array.data_agent)
        val dataDescription = resources.getStringArray(R.array.agent_description)
        val dataPhoto = resources.getStringArray(R.array.agent_icon)
        val listAgent = ArrayList<Agent>()
        for (i in dataName.indices) {
            val agent = Agent(dataName[i], dataDescription[i], dataPhoto[i])
            listAgent.add(agent)
        }
        return listAgent
    }
    private fun showRecyclerList() {
        binding.rvAgent.layoutManager = LinearLayoutManager(this)
        val listAgentAdapter = ListAgentAdapter(list)
        binding.rvAgent.adapter = listAgentAdapter

    }
}