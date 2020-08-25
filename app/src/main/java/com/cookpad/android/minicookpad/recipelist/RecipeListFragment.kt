package com.cookpad.android.minicookpad.recipelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cookpad.android.minicookpad.R
import com.cookpad.android.minicookpad.databinding.FragmentRecipeListBinding
import com.cookpad.android.minicookpad.datasource.FirebaseRecipeDataSource
import com.cookpad.android.minicookpad.datasource.RecipeEntity
import com.google.firebase.firestore.FirebaseFirestore

class RecipeListFragment : Fragment(),RecipeListContract.View {
    private lateinit var binding: FragmentRecipeListBinding

    private lateinit var adapter: RecipeListAdapter

    lateinit var presenter: RecipeListContract.Presenter

    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter=RecipeListPresenter(
            view=this,
            interactor = RecipeListInteractor(FirebaseRecipeDataSource()),
            routing = RecipeListRouting(this)
        )
        presenter.onRecipeListRequested()

        adapter = RecipeListAdapter { recipeId, recipeName ->
            presenter.onRecipeDetailRequested(recipeId,recipeName)
        }.also { binding.recipeList.adapter = it }
        binding.recipeList.layoutManager = LinearLayoutManager(requireContext())
        binding.createButton.setOnClickListener { findNavController().navigate(R.id.createRecipe) }

    }

    override fun renderRecipeList(recipeList: List<RecipeListContract.Recipe>) {
        adapter.update(recipeList)
    }

    override fun renderError(exception: Throwable) {
        Toast.makeText(requireContext(), "Failed to fetch recipe list.", Toast.LENGTH_SHORT).show()
    }
}