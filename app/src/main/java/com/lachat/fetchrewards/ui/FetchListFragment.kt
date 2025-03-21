package com.lachat.fetchrewards.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.lachat.fetchrewards.R
import com.lachat.fetchrewards.databinding.FragmentListBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FetchListFragment : Fragment() {

    private val fetchListViewModel: FetchListViewModel by viewModels()
    private var _binding: FragmentListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        binding.rvItems.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun initObservers() {
        fetchListViewModel.fetchList.observe(viewLifecycleOwner) {
            binding.rvItems.adapter = FetchItemsAdapter(it)
        }
        fetchListViewModel.loading.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
        fetchListViewModel.networkError.observe(viewLifecycleOwner) { isNetworkError ->
            if (isNetworkError) {
                displayErrorDialog()
            }
        }
    }

    private fun displayErrorDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder
            .setMessage(R.string.network_error_message)
            .setTitle(R.string.network_error_title)
            .setPositiveButton(R.string.try_again) { _, _ ->
                fetchListViewModel.getFetchListItems()
            }
            .setCancelable(false)

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}