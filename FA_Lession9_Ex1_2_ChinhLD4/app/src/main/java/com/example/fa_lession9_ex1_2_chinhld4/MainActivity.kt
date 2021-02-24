package com.example.fa_lession9_ex1_2_chinhld4

import android.app.Activity
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.fa_lession9_ex1_2_chinhld4.fragment.ReadContacts
import com.example.fa_lession9_ex1_2_chinhld4.fragment.WriteContacts
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity(),View.OnClickListener {
    val MY_PERMISSION_REQUEST: Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnReadContacts.setOnClickListener(this)
        btnWriteContacts.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when(v){
            btnReadContacts -> {
                val readContacts = ReadContacts()
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout, readContacts)
                    .commit()
            }
            btnWriteContacts -> {
                val writeContacts = WriteContacts()
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout, writeContacts)
                    .commit()
            }
        }
    }


}