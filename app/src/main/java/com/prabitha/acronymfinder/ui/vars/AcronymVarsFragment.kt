package com.prabitha.acronymfinder.ui.vars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.prabitha.acronymfinder.databinding.FragmentVarsBinding
import com.prabitha.acronymfinder.models.Vars
import com.prabitha.acronymfinder.ui.vars.adapters.VarsListAdapter
import com.prabitha.acronymfinder.utils.Constants


class AcronymVarsFragment : Fragment() {

    private val viewModel by viewModels<AcronymVarsViewModel>()
    lateinit var binding: FragmentVarsBinding
    private val adapter = VarsListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpObservers()
        setFragmentResultListener(Constants.REQUEST_KEY) { key, bundle ->
            val result = bundle.get(Constants.DATA) as List<Vars>?
            result?.let {
                viewModel.setResultFromBundle(it)
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVarsBinding.inflate(layoutInflater)
        binding.varsList.adapter = adapter
        return binding.root
    }

    private fun setUpObservers() {
        viewModel.varsList.observe(this) {
            if (it.isNotEmpty()) {
                adapter.setData(it)
            }
        }
    }
}