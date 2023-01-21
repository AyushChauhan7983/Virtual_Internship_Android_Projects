package com.example.lunchtray.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.lunchtray.R
import com.example.lunchtray.databinding.FragmentCheckoutBinding
import com.example.lunchtray.model.OrderViewModel
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class CheckoutFragment : Fragment() {


    private var _binding: FragmentCheckoutBinding? = null
    private val binding get() = _binding!!
    private val orderViewModel: OrderViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCheckoutBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = orderViewModel
            checkoutFragment = this@CheckoutFragment
        }
    }


    fun submitOrder() {
        Snackbar.make(binding.root, R.string.submit_order, Snackbar.LENGTH_SHORT).show()
        // TODO: Reset order in view model
        // TODO: Navigate back to the [StartFragment] to start over
        orderViewModel.resetOrder()
        findNavController().navigate(R.id.action_checkoutFragment_to_startOrderFragment)
    }

    fun cancelOrder() {
        orderViewModel.resetOrder()
        findNavController().navigate(R.id.action_checkoutFragment_to_startOrderFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}