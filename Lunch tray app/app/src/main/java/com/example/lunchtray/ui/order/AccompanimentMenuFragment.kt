package com.example.lunchtray.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.lunchtray.R
import com.example.lunchtray.databinding.FragmentAccompanimentMenuBinding
import com.example.lunchtray.model.OrderViewModel

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class AccompanimentMenuFragment : Fragment() {

    private var _binding: FragmentAccompanimentMenuBinding? = null
    private val binding get() = _binding!!
    private val orderViewModel: OrderViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAccompanimentMenuBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            // Specify the lifecycle owner
            lifecycleOwner = viewLifecycleOwner
            // Assign the view model
            viewModel = orderViewModel
            // Assign the fragment
            accompanimentMenuFragment = this@AccompanimentMenuFragment
        }
    }

    /**
     * Cancel the order by navigating the [StartOrderFragment] fragment to start over
     * */
    fun cancelOrder() {
        orderViewModel.resetOrder()
        findNavController().navigate(R.id.action_accompanimentMenuFragment_to_startOrderFragment)
    }

    /**
     * Navigate to next fragment [CheckoutFragment]
     * */
    fun goNextScreen() {
        findNavController().navigate(R.id.action_accompanimentMenuFragment_to_checkoutFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}