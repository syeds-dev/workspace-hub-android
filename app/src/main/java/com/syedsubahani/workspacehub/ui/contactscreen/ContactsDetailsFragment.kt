package com.syedsubahani.workspacehub.ui.contactscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.syedsubahani.workspacehub.R
import com.syedsubahani.workspacehub.databinding.FragmentContactsDetailsBinding

class ContactsDetailsFragment : Fragment() {

    private lateinit var binding:FragmentContactsDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactsDetailsBinding.inflate(inflater, container, false)

        var avatarUrl = arguments?.getString("avatarUrl")
        var contactName = arguments?.getString("contactName")
        var emailId = arguments?.getString("emailId")
        var jobTitle = arguments?.getString("jobTitle")
        var favouriteColor = arguments?.getString("favouriteColor")


        Glide.with(this)
            .load(avatarUrl)
            .apply(RequestOptions.circleCropTransform())
            .transform(CircleCrop())
            .error(R.drawable.default_dp_icon)
            .into(binding.avatarImageView)
        binding.contactName.text = contactName
        binding.emailId.text = emailId
        binding.jobTitle.text = jobTitle
        binding.favouriteColor.text = favouriteColor
        return binding.root
    }
}