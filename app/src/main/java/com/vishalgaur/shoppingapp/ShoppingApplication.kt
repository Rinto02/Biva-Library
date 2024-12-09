package com.biva.library.app

import android.app.Application
import com.biva.library.app.data.source.repository.AuthRepoInterface
import com.biva.library.app.data.source.repository.ProductsRepoInterface

class ShoppingApplication : Application() {
	val authRepository: AuthRepoInterface
		get() = ServiceLocator.provideAuthRepository(this)

	val productsRepository: ProductsRepoInterface
		get() = ServiceLocator.provideProductsRepository(this)

	override fun onCreate() {
		super.onCreate()
	}
}