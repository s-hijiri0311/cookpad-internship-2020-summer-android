package com.cookpad.android.minicookpad.recipesend

import com.cookpad.android.minicookpad.recipelist.RecipeListContract


class RecipeSendPresenter(
    private val view: RecipeSendContract.View,
    private val interactor: RecipeSendContract.Interactor
) : RecipeSendContract.Presenter {
    override fun onRecipeSendRequested(title: String, imagePath: String, steps: String) {
        interactor.sendRecipe(
            onSuccess = { view.renderRecipeList(it) },
            onFailed = { view.renderError(it) }
        )
    }
}