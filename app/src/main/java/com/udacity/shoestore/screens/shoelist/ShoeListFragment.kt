package com.udacity.shoestore.screens.shoelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe

class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding
    private lateinit var viewModel: ShoeListViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_list,
            container,
            false
        )

        viewModel = ViewModelProvider(requireActivity())[ShoeListViewModel::class.java]
        navController = findNavController()

        binding.shoeListViewModel = viewModel
        binding.lifecycleOwner = this

        val args by navArgs<ShoeListFragmentArgs>()
        args.shoe?.let {
            viewModel.addShoe(it)
        }

        setOnClickListener()
        observeShoesAdded()
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_logout, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.menu_logout) {
            navController.navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
            true
        } else {
            false
        }
    }

    private fun setOnClickListener() {
        binding.buttonAddShoe.setOnClickListener {
            navController.navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }
    }

    private fun observeShoesAdded() {
        viewModel.shoes.observe(this.viewLifecycleOwner) { shoes ->
            shoes?.let {
                addShoeTextViews(shoes)
            }
        }
    }

    private fun addShoeTextViews(shoes: List<Shoe>) {
        shoes.forEach { shoe ->
            binding.shoesListLayout.addView(createShoeTextView(shoe))
        }
    }

    private fun createShoeTextView(shoe: Shoe): TextView {
        val shoeListItemText = getString(
            R.string.shoe_list_item_template,
            shoe.company,
            shoe.name,
            shoe.size,
            shoe.description
        )
        return TextView(this.requireContext()).apply {
            text = shoeListItemText
            textSize = resources.getDimension(R.dimen.text_size_shoe)
            background = ContextCompat.getDrawable(context, R.drawable.ic_box).apply {
                height
            }
        }
    }
}