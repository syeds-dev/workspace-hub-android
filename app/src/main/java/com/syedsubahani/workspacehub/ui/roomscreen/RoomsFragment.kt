package com.syedsubahani.workspacehub.ui.roomscreen

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
import com.syedsubahani.workspacehub.databinding.FragmentRoomsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomsFragment : Fragment() {

    lateinit var binding: FragmentRoomsBinding
    private val viewModel: RoomsViewModel by viewModels()
    private lateinit var adapter: RoomsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRoomsBinding.inflate(inflater,container,false)

        val layoutManager = LinearLayoutManager(requireContext().applicationContext, LinearLayoutManager.VERTICAL, false)

        binding.recyclerViewRooms.layoutManager = LinearLayoutManager(requireContext().applicationContext)

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        if(NetworkStatus.checkForInternet(requireContext().applicationContext)){
            viewModel.getRooms()
        }else{
            Toast.makeText(context, "No Internet Connection!", Toast.LENGTH_SHORT).show()
        }

        lifecycleScope.launchWhenStarted {
            viewModel.rooms.collect { event ->
                when (event) {
                    is RoomsViewModel.RoomsEvent.Success -> {
                        binding.progressBar.isVisible = false

                        adapter = RoomsListAdapter(event.resultText, requireContext().applicationContext)

                        binding.recyclerViewRooms.adapter = adapter

                        binding.recyclerViewRooms.addItemDecoration(
                            DividerItemDecoration(
                                requireContext().applicationContext,
                                layoutManager.orientation
                            )
                        )
                    }
                    is RoomsViewModel.RoomsEvent.Failure -> {
                        binding.progressBar.isVisible = false
                        Toast.makeText(requireContext().applicationContext, event.errorText, Toast.LENGTH_LONG).show()

                    }
                    is RoomsViewModel.RoomsEvent.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    else -> Unit
                }
            }
        }
        return binding.root
    }
}