package com.example.tp4

import BusClassAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp4.busClassFeature.BusScheduleViewModel
import com.example.tp4.busClassFeature.BusScheduleViewModelFactory
import com.example.tp4.busschedule.BusScheduleApplication

class DetailsFragment : Fragment() {
    private val busScheduleViewModel: BusScheduleViewModel by viewModels {
        BusScheduleViewModelFactory((requireActivity().application as BusScheduleApplication).database.getScheduleDao())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val stopName = arguments?.getString("stopName") ?: ""
        initAdapter(stopName)
    }

    private fun initAdapter(stopName: String) {
        val busClassAdapter = BusClassAdapter(null)
        setupRecyclerView(busClassAdapter)
        initList(busClassAdapter, stopName)
    }

    private fun initList(busClassAdapter: BusClassAdapter, stopName: String) {
        busScheduleViewModel
            .scheduleForStopName(stopName)
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
