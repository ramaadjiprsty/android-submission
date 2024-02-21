package com.example.valorantagent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvAgent: RecyclerView
    private val list = ArrayList<Agent>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvAgent = findViewById(R.id.rv_agent)
        rvAgent.setHasFixedSize(true)

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
        rvAgent.layoutManager = LinearLayoutManager(this)
        val listAgentAdapter = ListAgentAdapter(list)
        rvAgent.adapter = listAgentAdapter

        listAgentAdapter.setOnItemClickCallback(object : ListAgentAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Agent) {
                showSelectedAgent(data)
            }
        })
    }

    private fun showSelectedAgent(agent: Agent) {
        Toast.makeText(this, "Kamu memilih " + agent.name, Toast.LENGTH_SHORT).show()
    }
}