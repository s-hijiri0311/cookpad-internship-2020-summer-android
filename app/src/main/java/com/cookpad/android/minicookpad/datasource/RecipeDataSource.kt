package com.cookpad.android.minicookpad.datasource

interface RecipeDataSource {
    fun fetchAll(onSuccess: (List<RecipeEntity>) -> Unit, onFailed: (Throwable) -> Unit)
    fun send(title:String,imagePath:String,steps:List<String>,onSuccess: () -> Unit, onFailed: (Throwable) -> Unit)
}