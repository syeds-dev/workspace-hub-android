package com.syedsubahani.workspacehub.ui.contactscreen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.syedsubahani.workspacehub.R
import com.syedsubahani.workspacehub.data.models.ContactsResponseItem
import android.os.Bundle


public class ContactListAdapter(
    var contactList: ArrayList<ContactsResponseItem>,
    var context: Context
) : RecyclerView.Adapter<ContactListAdapter.ContactViewHolder>() {

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var avatarImageView: ImageView = itemView.findViewById(R.id.avatarImageView)
        var fullNameTextView: TextView = itemView.findViewById(R.id.contactName)
        var mainLinearLayout:LinearLayout = itemView.findViewById(R.id.mainContactLinearLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.fullNameTextView.text =
            contactList[position].firstName + " " + contactList[position].lastName

        Glide.with(context)
            .load(contactList[position].avatar)
            .transform(CircleCrop())
            .error(
                Glide.with(context)
                    .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcGJegujCz3neLg3btfiVRfmV4dg52BBd38g&usqp=CAU")
            )
            .into(holder.avatarImageView)

        holder.mainLinearLayout.setOnClickListener() {

            val bundle = Bundle()
            bundle.putString("avatarUrl", contactList[position].avatar)
            bundle.putString(
                "contactName",
                contactList[position].firstName + " " + contactList[position].lastName
            )
            bundle.putString("emailId", contactList[position].email)
            bundle.putString("jobTitle", contactList[position].jobtitle)
            bundle.putString("favouriteColor", contactList[position].favouriteColor)
            Navigation.findNavController(it)
                .navigate(R.id.action_contactsFragment_to_contactsDetailsFragment, bundle)
        }
    }

    override fun getItemCount(): Int {
        return contactList.size
    }
}