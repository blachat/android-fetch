package com.lachat.fetchrewards.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.lachat.fetchrewards.R
import com.lachat.fetchrewards.databinding.FragmentDetailsBinding
import com.lachat.fetchrewards.utils.FetchUtils

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DetailsFragment : Fragment() {

    private val args: DetailsFragmentArgs by navArgs()
    private var _binding: FragmentDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fetchItem = args.fetchItem

        with (binding.includedLyt) {
            val cardBackgroundColor = FetchUtils.getCardColor(requireActivity(), fetchItem.listId)
            cardView.setCardBackgroundColor(cardBackgroundColor)
            tvGroup.text = getString(R.string.group, fetchItem.listId)
            tvItem.text = fetchItem.name?.replace(
                getString(R.string.item_field_name),
                getString(R.string.formatted_item_field_name)
            )
            tvId.text = getString(R.string.item_id, fetchItem.id)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}