package com.cookpad.android.minicookpad.recipesend

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.bumptech.glide.Glide
import com.cookpad.android.minicookpad.RecipeCreateViewModel
import com.cookpad.android.minicookpad.databinding.ActivityRecipeCreateBinding
import com.cookpad.android.minicookpad.datasource.FirebaseImageDataSource
import com.cookpad.android.minicookpad.datasource.FirebaseRecipeDataSource
import kotlinx.android.synthetic.main.activity_recipe_create.*

class RecipeCreateActivity : AppCompatActivity(), RecipeSendContract.View {
    private lateinit var binding: ActivityRecipeCreateBinding

    private val viewModel: RecipeCreateViewModel by viewModels()

    lateinit var presenter: RecipeSendContract.Presenter

    override fun renderRecipeList(recipeList: List<RecipeSendContract.Recipe>) {

    }

        override fun renderError(exception: Throwable) {

            }

    private val launcher: ActivityResultLauncher<Unit> by lazy {
        registerForActivityResult(ImageSelector()) { imageUri ->
            imageUri?.let {
                Glide.with(this)
                    .load(imageUri)
                    .into(binding.image)
                viewModel.updateImageUri(imageUri.toString())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecipeCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.apply {
            title = "レシピ作成"
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }

        binding.image.setOnClickListener {
            launcher.launch(null)
        }

        save_button.setOnClickListener{
            presenter=RecipeSendPresenter(
                interactor = RecipeSendInteractor(FirebaseImageDataSource(),FirebaseRecipeDataSource()),
                view = this
            )
            presenter.onRecipeSendRequested()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    class ImageSelector : ActivityResultContract<Unit, Uri?>() {
        override fun createIntent(context: Context, input: Unit?): Intent =
            Intent.createChooser(
                Intent().apply {
                    type = "image/*"
                    action = Intent.ACTION_GET_CONTENT
                },
                "レシピ写真"
            )

        override fun parseResult(resultCode: Int, intent: Intent?): Uri? = intent?.data
    }
}