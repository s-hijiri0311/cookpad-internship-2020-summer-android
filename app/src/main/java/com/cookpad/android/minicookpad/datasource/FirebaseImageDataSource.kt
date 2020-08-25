package com.cookpad.android.minicookpad.datasource

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class FirebaseImageDataSource : ImageDataSource {
    private val reference = FirebaseStorage.getInstance().reference

    override fun saveImage(
        uri: String,
        onSuccess: (String) -> Unit,
        onFailed: (Throwable) -> Unit
    ) {
        val imageRef = "$COLLECTION_PATH/${UUID.randomUUID()}" // "images/87360ebc00235b3b9b03e1716844de57" のようなパスにアップロード
        reference.child(imageRef).putFile(Uri.parse(uri))
            .addOnSuccessListener { onSuccess.invoke(imageRef) }
            .addOnFailureListener(onFailed)
    }

    private companion object {
        const val COLLECTION_PATH = "images"
    }
}