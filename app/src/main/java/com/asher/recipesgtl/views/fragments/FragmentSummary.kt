package com.asher.recipesgtl.views.fragments

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.asher.recipesgtl.R
import com.asher.recipesgtl.databinding.ActivityRecipeDetailsBinding
import com.asher.recipesgtl.databinding.FragmentSummaryBinding
import com.asher.recipesgtl.models.RecipeDetails
import com.asher.recipesgtl.viewmodels.RecipeDetailsViewModel


class FragmentSummary :Fragment(){
    private val model: RecipeDetailsViewModel by activityViewModels()


    private var _binding: FragmentSummaryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSummaryBinding.inflate(inflater, container, false)
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
            binding.recipeDetails =item
        })
    }
}