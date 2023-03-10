package com.prabitha.acronymfinder.ui.acronyms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.prabitha.acronymfinder.R
import com.prabitha.acronymfinder.databinding.FragmentAcronymsBinding
import com.prabitha.acronymfinder.ui.acronyms.adapters.AcronymListAdapter
import com.prabitha.acronymfinder.utils.Constants
import com.prabitha.acronymfinder.utils.isNetworkConnectionAvailable
import com.prabitha.acronymfinder.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AcronymFragment : Fragment() {

    private val adapter = AcronymListAdapter { position -> onCLickOfAcronym(position) }


    private lateinit var binding: FragmentAcronymsBinding


    private val viewModel by viewModels<AcronymViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAcronymsBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {

                if (p0?.isNotEmpty() == true) {
                    context?.let {
                        when {
                            isNetworkConnectionAvailable(context = it) ->
                                viewModel.getAcronyms(p0)
                            else -> showToast(it, Constants.NO_INTERNET)
                        }
                    }
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
        binding.rvAcronyms.adapter = adapter
        binding.lifecycleOwner = this
        return binding.root
    }

    private fun setUpObservers() {
        viewModel.acronyms.observe(this) {
            if (it.isNotEmpty()) {
                adapter.setData(it)
            }
        }
    }

    private fun onCLickOfAcronym(position: Int) {
        viewModel.acronyms.value?.let {
            if (it[0].lfs[position].vars.isNotEmpty()) {
                setFragmentResult(
                    Constants.REQUEST_KEY,
                    bundleOf(Constants.DATA to it[0].lfs[position].vars)
                )
                this.findNavController().navigate(R.id.action_mainFragment_to_acronymVarsFragment)
            }
        }


    }
}