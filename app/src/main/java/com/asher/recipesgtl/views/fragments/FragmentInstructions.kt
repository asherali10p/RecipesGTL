package com.asher.recipesgtl.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.asher.recipesgtl.R
import com.asher.recipesgtl.databinding.FragmentInstructionsBinding
import com.asher.recipesgtl.viewmodels.RecipeDetailsViewModel

class FragmentInstructions : Fragment(){
    private val model: RecipeDetailsViewModel by activityViewModels()


    private var _binding: FragmentInstructionsBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInstructionsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        model.recipeDetails.observe(viewLifecycleOwner, { item ->
            // Update the UI

            binding.recipeDetails =item
        })
    }
}