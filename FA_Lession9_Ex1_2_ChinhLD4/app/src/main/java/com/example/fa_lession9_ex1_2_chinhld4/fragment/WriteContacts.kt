package com.example.fa_lession9_ex1_2_chinhld4.fragment


import android.content.Intent

import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fa_lession9_ex1_2_chinhld4.R
import kotlinx.android.synthetic.main.fragment_write_contacts.*


class WriteContacts : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_write_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btnAdd.setOnClickListener(this)
    }

    private fun addContacts() {
        val intent = Intent(Intent.ACTION_INSERT)
        if (edtName.text.toString().isNotEmpty() && edtNumber.text.toString()
                .isNotEmpty() && edtEmail.text.toString()
                .isNotEmpty()
        ) {
            intent.type = ContactsContract.RawContacts.CONTENT_TYPE
            intent.putExtra(ContactsContract.Intents.Insert.NAME, edtName.text.toString())
            intent.putExtra(
                ContactsContract.Intents.Insert.PHONE,
                edtNumber.text.toString()
            )
            intent.putExtra(ContactsContract.Intents.Insert.EMAIL, edtEmail.text.toString())
            if (activity!!.packageManager != null) startActivity(intent)
            else Toast.makeText(context, "no device to start ", Toast.LENGTH_SHORT).show()
        } else Toast.makeText(context, "please fill all the fields", Toast.LENGTH_SHORT)
            .show()
    }

    override fun onClick(v: View?) {
        when (v) {
            btnAdd -> addContacts()
        }
    }

}