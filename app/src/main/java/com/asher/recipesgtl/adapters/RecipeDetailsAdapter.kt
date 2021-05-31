package com.asher.recipesgtl.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.asher.recipesgtl.views.fragments.FragmentIngredients
import com.asher.recipesgtl.views.fragments.FragmentInstructions
import com.asher.recipesgtl.views.fragments.FragmentSummary

class RecipeDetailsAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int  = 3


    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FragmentSummary()
            1 -> FragmentIngredients()
            else -> FragmentInstructions()
        }
    }
}
