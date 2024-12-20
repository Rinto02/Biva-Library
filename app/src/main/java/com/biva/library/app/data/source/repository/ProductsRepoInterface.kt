package com.biva.library.app.data.source.repository

import android.net.Uri
import androidx.lifecycle.LiveData
import com.biva.library.app.data.Product
import com.biva.library.app.data.Result
import com.biva.library.app.data.utils.StoreDataStatus

interface ProductsRepoInterface {
	suspend fun refreshProducts(): StoreDataStatus?
	fun observeProducts(): LiveData<Result<List<Product>>?>
	fun observeProductsByOwner(ownerId: String): LiveData<Result<List<Product>>?>
	suspend fun getAllProductsByOwner(ownerId: String): Result<List<Product>>
	suspend fun getProductById(productId: String, forceUpdate: Boolean = false): Result<Product>
	suspend fun insertProduct(newProduct: Product): Result<Boolean>
	suspend fun insertImages(imgList: List<Uri>): List<String>
	suspend fun updateProduct(product: Product): Result<Boolean>
	suspend fun updateImages(newList: List<Uri>, oldList: List<String>): List<String>
	suspend fun deleteProductById(productId: String): Result<Boolean>
}