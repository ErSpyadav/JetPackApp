package com.example.myjetpackdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import bindingadapter.ProfileAdapter
import bindingadapter.StudentAdapter
import kotlinx.android.synthetic.main.activity_main.*
import model.ProfileItem
import model.StudentItem

class MainActivity : AppCompatActivity(), ProfileAdapter.ProfileListener<ProfileItem> {
lateinit var button :Button
    private lateinit var adapter: StudentAdapter<StudentItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         button =findViewById(R.id.btn_showList)
        adapter = StudentAdapter()
        button.setOnClickListener{
            var student = mutableListOf<StudentItem>(
                StudentItem("Sarayu","CSE","2013"),
                StudentItem("Sunita", "M.com","2017")
            )
            pupulateRecipientList(student)
        }

    }

   fun pupulateRecipientList(data: MutableList<StudentItem>) {
         adapter.setItems(data)
         recycleView.adapter =adapter

    }

    override fun onRightButtonClick(item: ProfileItem) {
        Toast.makeText(this,item.name,Toast.LENGTH_LONG).show()
    }

    override fun onProfileCellListClicked(item: ProfileItem) {
        Toast.makeText(this,item.name,Toast.LENGTH_LONG).show()
    }
}