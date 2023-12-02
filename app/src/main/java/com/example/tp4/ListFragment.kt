package com.example.tp4

import BusClassAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp4.busClassFeature.BusScheduleViewModel
import com.example.tp4.busClassFeature.BusScheduleViewModelFactory
import com.example.tp4.busschedule.BusScheduleApplication

class ListFragment : Fragment() {

    private val busScheduleViewModel: BusScheduleViewModel by viewModels {
        BusScheduleViewModelFactory((requireActivity().application as BusScheduleApplication).database.getScheduleDao())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        val busClassAdapter = BusClassAdapter { schedule ->
            val bundle = bundleOf("stopName" to schedule.stopName)
            findNavController().navigate(R.id.action_listFragment_to_details, bundle)
        }

        setupRecyclerView(busClassAdapter)
        initList(busClassAdapter)
    }

    private fun initList(busClassAdapter: BusClassAdapter) {
        busScheduleViewModel
            .fullSchedule()
            .observe(viewLifecycleOwner) {
                busClassAdapter.updateList(it)
            }
    }

    private fun setupRecyclerView(busClassAdapter: BusClassAdapter) {
        val recyclerView: RecyclerView = requireView().findViewById(R.id.recycleView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = busClassAdapter
    }
}

