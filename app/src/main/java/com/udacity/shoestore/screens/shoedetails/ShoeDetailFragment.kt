package com.udacity.shoestore.screens.shoedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe

class ShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding
    private lateinit var viewModel: ShoeDetailViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_detail,
            container,
            false
        )

        viewModel = ViewModelProvider(this)[ShoeDetailViewModel::class.java]
        navController = findNavController()
        binding.lifecycleOwner = this
        binding.shoeDetailViewModel = viewModel

        configureSaveButton()
        configureCancelButton()

        return binding.root
    }

    private fun configureSaveButton() {
        binding.buttonSave.setOnClickListener {
            val shoe = shoeFromViewModel()
            navController.navigate(
                ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment(shoe)
            )
        }
    }

    private fun configureCancelButton() {
        binding.buttonCancel.setOnClickListener {
            navController.navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        }
    }

    private fun shoeFromViewModel() = Shoe(
        name = viewModel.name.value ?: "",
        company = viewModel.company.value ?: "",
        size = (viewModel.size.value ?: "").toDouble(),
        description = viewModel.description.value ?: ""
    )
}