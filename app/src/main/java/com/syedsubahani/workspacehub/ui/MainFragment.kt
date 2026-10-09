package com.syedsubahani.workspacehub.ui

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.syedsubahani.workspacehub.R
import com.syedsubahani.workspacehub.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    lateinit var binding:FragmentMainBinding
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(inflater,container,false)

        binding.contactsTextView.setOnClickListener() {
            findNavController().navigate(R.id.action_mainFragment_to_contactsFragment)
        }
        binding.buttonContacts.setOnClickListener() {
            findNavController().navigate(R.id.action_mainFragment_to_contactsFragment)
        }

        binding.roomsTextView.setOnClickListener() {
            findNavController().navigate(R.id.action_mainFragment_to_roomsFragment)
        }
        binding.buttonRooms.setOnClickListener() {
            findNavController().navigate(R.id.action_mainFragment_to_roomsFragment)
        }
        return binding.root
    }
}