package com.example.fa_lession9_ex1_2_chinhld4.fragment

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fa_lession9_ex1_2_chinhld4.R
import com.example.fa_lession9_ex1_2_chinhld4.adapter.ContactAdapter
import com.example.fa_lession9_ex1_2_chinhld4.model.Contacts
import kotlinx.android.synthetic.main.fragment_read_contacts.*


class ReadContacts : Fragment() {
    private val MY_REQUEST_PERMISSIONS: Int = 1
    private val contactList: MutableList<Contacts> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_read_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView.layoutManager = LinearLayoutManager(context)
        if (ContextCompat.checkSelfPermission(
                context!!,
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    context as Activity,
                    Manifest.permission.READ_CONTACTS
                )
            ) {
                ActivityCompat.requestPermissions(
                    context as Activity,
                    arrayOf(Manifest.permission.READ_CONTACTS),
                    MY_REQUEST_PERMISSIONS
                )
            } else {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.READ_CONTACTS),
                    MY_REQUEST_PERMISSIONS
                )
            }
        } else {
            getContacts()
        }
    }

    private fun getContacts() {
        val contacts = activity?.contentResolver?.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null,
            null
        )
        while (contacts!!.moveToNext()) {
            val name =
                contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val number =
                contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            val obj = Contacts()
            obj.name = name
            obj.number = number
            val photo =
                contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI))
            if (photo != null) {
                obj.image =
                    MediaStore.Images.Media.getBitmap(activity?.contentResolver, Uri.parse(photo))
            }
            contactList.add(obj)
        }
        recyclerView.adapter = ContactAdapter(contactList, context!!)
        contacts.close()
    }

}