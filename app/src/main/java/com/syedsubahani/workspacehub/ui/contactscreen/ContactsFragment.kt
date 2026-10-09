package com.syedsubahani.workspacehub.ui.contactscreen

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.syedsubahani.workspacehub.common.NetworkStatus
import com.syedsubahani.workspacehub.databinding.FragmentContactsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ContactsFragment : Fragment() {
    private lateinit var binding: FragmentContactsBinding
    private val viewModel: ContactsViewModel by viewModels()
    private lateinit var adapter: ContactListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentContactsBinding.inflate(inflater,container,false)

        val layoutManager = LinearLayoutManager(requireContext().applicationContext, LinearLayoutManager.VERTICAL, false)

        binding.recyclerViewContacts.layoutManager = LinearLayoutManager(requireContext().applicationContext)

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        if(NetworkStatus.checkForInternet(requireContext().applicationContext)){

            viewModel.getContacts()
        }else{
            Toast.makeText(context, "No Internet Connection!", Toast.LENGTH_SHORT).show()
        }

        lifecycleScope.launchWhenStarted {
            viewModel.contacts.collect { event ->
                when (event) {
                    is ContactsViewModel.ContactsEvent.Success -> {
                        binding.progressBar.isVisible = false

                        adapter = ContactListAdapter(event.resultText, requireContext().applicationContext)

                        binding.recyclerViewContacts.adapter = adapter

                        binding.recyclerViewContacts.addItemDecoration(
                            DividerItemDecoration(
                                requireContext().applicationContext,
                                layoutManager.orientation
                            )
                        )
                    }
                    is ContactsViewModel.ContactsEvent.Failure -> {event
                        binding.progressBar.isVisible = false
                        Toast.makeText(requireContext().applicationContext, event.errorText, Toast.LENGTH_LONG).show()

                    }
                    is ContactsViewModel.ContactsEvent.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    else -> Unit
                }
            }
        }
        return binding.root
    }
}