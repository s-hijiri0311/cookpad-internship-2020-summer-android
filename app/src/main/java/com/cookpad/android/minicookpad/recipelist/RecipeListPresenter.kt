package com.cookpad.android.minicookpad.recipelist

class RecipeListPresenter(
    private val view: RecipeListContract.View,
    private val interactor: RecipeListContract.Interactor,
    private val routing: RecipeListRouting
) : RecipeListContract.Presenter {
    override fun onRecipeListRequested() {
        interactor.fetchRecipeList(
            onSuccess = {view.renderRecipeList(it)},
            onFailed = {view.renderError(it)}
        )
    }

    override fun onRecipeDetailRequested(recipeId: String, recipeName: String) {
        routing.navigateRecipeDetail(recipeId,recipeName)
    }
}