package com.cookpad.android.minicookpad.recipesend

import com.cookpad.android.minicookpad.recipelist.RecipeListContract

interface RecipeSendContract {
    interface View{
        fun renderRecipeList(recipeList:List<RecipeSendContract.Recipe>)
        fun renderError(exception: Throwable)
    }

    interface Interactor{
        fun sendRecipe(onSuccess: (List<Recipe>) -> Unit, onFailed: (Throwable) -> Unit)
    }

    interface Presenter{
        fun onRecipeSendRequested(title: String,imagePath: String,steps: String)
    }

    interface Routing

    data class Recipe(
        val title: String,     // レシピのタイトル
        val imagePath: String, // Firebase Storage 上のパス: "images/hogehoge.png"
        val steps: String     // 作り方のテキストをまとめたもの
    )
}