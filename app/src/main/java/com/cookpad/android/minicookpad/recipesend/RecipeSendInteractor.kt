package com.cookpad.android.minicookpad.recipesend

import com.cookpad.android.minicookpad.datasource.FirebaseImageDataSource
import com.cookpad.android.minicookpad.datasource.ImageDataSource
import com.cookpad.android.minicookpad.datasource.RecipeDataSource
import com.cookpad.android.minicookpad.datasource.RecipeEntity

class RecipeSendInteractor(
    private val imageDataSource: ImageDataSource,
    private val recipeDataSource: RecipeDataSource
) : RecipeSendContract.Interactor {
    override fun sendRecipe(
        onSuccess: (List<RecipeSendContract.Recipe>) -> Unit,
        onFailed: (Throwable) -> Unit
    ) {
        recipeDataSource.send(
            title = "test",
            imagePath = "test",
            steps = listOf("test","test","test"),
            onSuccess = {},
            onFailed = onFailed
        )
    }
}