package com.example.lunchtray.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.lunchtray.R
import com.example.lunchtray.databinding.FragmentSideMenuBinding
import com.example.lunchtray.model.OrderViewModel

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class SideMenuFragment : Fragment() {

    // Binding object instance corresponding to fragment_side_menu.xml layout
    private var _binding: FragmentSideMenuBinding? = null
    private val binding get() = _binding!!
    // View Model instance
    private val orderViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSideMenuBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            // Specify the lifecycle Owner of the view model as the lifecycle of the fragment
            lifecycleOwner = viewLifecycleOwner
            // Assign the view model
            viewModel = orderViewModel
            // Assign the fragment
            sideMenuFragment = this@SideMenuFragment

        }
    }

    /**
     * Navigate to the [StartOrderFragment] and reset order
     * */
    fun cancelOrder() {
        // Reset the order
        orderViewModel.resetOrder()
        // Navigate to the start fragment
        findNavController().navigate(R.id.action_sideMenuFragment_to_startOrderFragment)
    }

    /**
     * Navigate to the next screen [AccompanimentMenuFragment] to accomplish the order.
     * */
    fun goNextScreen() {
        findNavController().navigate(R.id.action_sideMenuFragment_to_accompanimentMenuFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}