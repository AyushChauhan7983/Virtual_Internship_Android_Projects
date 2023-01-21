package com.example.lunchtray.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.lunchtray.R
import com.example.lunchtray.databinding.FragmentEntreeMenuBinding
import com.example.lunchtray.model.OrderViewModel

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class EntreeMenuFragment : Fragment() {

    // Binding object instance corresponding to the fragment_entree_menu.xml layout
    private var _binding: FragmentEntreeMenuBinding? = null
    private val binding get() = _binding!!

    // Order View Model object instance
    private val orderViewModel: OrderViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEntreeMenuBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            // Specify the view model lifecycleOwner as the fragment lifecycle
            lifecycleOwner = viewLifecycleOwner
            // Assign the view model
            viewModel = orderViewModel
            // Assign the fragment
            entreeMenuFragment = this@EntreeMenuFragment
        }
    }

    /**
     *  Navigate to the side menu fragment
     *  */
    fun goNextScreen() {
        findNavController().navigate(R.id.action_entreeMenuFragment_to_sideMenuFragment)
    }

    /**
     * Cancel order and start over
     * */
    fun cancelOrder() {
        orderViewModel.resetOrder()
        findNavController().navigate(R.id.action_entreeMenuFragment_to_startOrderFragment)
    }

    /**
     * This fragment lifecycle method is called when the view hierarchy associated with the fragment
     * is being removed. As a result, clear out the binding object.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}