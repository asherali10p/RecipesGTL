package com.asher.recipesgtl.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.asher.recipesgtl.views.fragments.FragmentIngredients
import com.asher.recipesgtl.views.fragments.FragmentInstructions
import com.asher.recipesgtl.views.fragments.FragmentSummary


class RecipeDetailsAdapter(fragmentActivity: FragmentActivity, titles: List<String>) :
    FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return FragmentSummary()
            1 -> return FragmentIngredients()
            2 -> return FragmentInstructions()
        }
        return FragmentSummary()
    }

    override fun getItemCount(): Int {
        return 3
    }
}