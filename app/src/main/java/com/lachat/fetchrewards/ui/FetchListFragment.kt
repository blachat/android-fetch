package com.lachat.fetchrewards

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lachat.fetchrewards.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FetchListFragment : Fragment() {

    private val fetchListViewModel: FetchListViewModel by viewModels()
    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchListViewModel.fetchList.observe(viewLifecycleOwner) {
            println(it)
            it.forEach { fetchItem -> println(fetchItem) }
        }
        binding.buttonFirst.setOnClickListener {
            val action = FetchListFragmentDirections.actionFirstFragmentToSecondFragment()
       //     val action = HomeFragmentDirections.toAddNoteFragment()
         //   binding.floatingActionButton.setOnClickListener {
                findNavController().navigate(action)
         //   }
        }

        fetchListViewModel.getFetchListItems()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}