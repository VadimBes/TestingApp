package com.example.android.testingapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.util.MalformedJsonException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.android.testingapp.R
import com.example.android.testingapp.data.repository.CompanyRepository
import com.example.android.testingapp.databinding.DetailFragmentBinding
import com.example.android.testingapp.other.Constants.BASE_URL
import com.example.android.testingapp.ui.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment:Fragment(R.layout.detail_fragment) {

    lateinit var viewModel: DetailViewModel
    @Inject
    lateinit var repository:CompanyRepository
    lateinit var binding:DetailFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailFragmentBinding.inflate(inflater,null,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val safeArgs = arguments?.let{
            DetailFragmentArgs.fromBundle(it)
        }
        val id = safeArgs?.id
        val viewModelFactory = ViewModelFactory(id!!,repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(DetailViewModel::class.java)
        binding.viewModel = viewModel
            bindUI()


    }

    fun bindUI() = CoroutineScope(Dispatchers.Main).launch {
        viewModel.detailCompany.await().observe(viewLifecycleOwner,{
            Glide.with(this@DetailFragment).load(BASE_URL+ "/" +it.img).into(binding.imageViewDetail)
            binding.title.text = "Название:${it.name}"
            binding.description.text = "Описание:${it.description}"
            binding.phone.text = "Телефон:${it.phone}"
        })
        }
    }