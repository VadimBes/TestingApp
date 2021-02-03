package com.example.android.testingapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.testingapp.R
import com.example.android.testingapp.databinding.FragmentMainBinding
import com.example.android.testingapp.ui.adapter.RecyclerClickListener
import com.example.android.testingapp.ui.adapter.RecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment :Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding:FragmentMainBinding
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater,null,false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = RecyclerViewAdapter(RecyclerClickListener {
                companyId->binding.viewModel!!.navigateActionToDetail(companyId)
        })
        binding.recyclerView.adapter= adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        bindUi()

        binding.viewModel!!.navigateToDetail.observe(viewLifecycleOwner,{
            if (it!=null) {
                this.findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToDetailFragment(it)
                )
                binding.viewModel!!.finishNavigate()
            }
        })
    }

    private fun bindUi() = CoroutineScope(Dispatchers.Main).launch {
        val currentCompanies = viewModel.companies.await()
        currentCompanies.observe(viewLifecycleOwner,{
            adapter.submitList(it)
        })
    }
}