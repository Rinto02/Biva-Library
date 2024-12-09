package com.biva.library.app.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.biva.library.app.ServiceLocator
import com.biva.library.app.data.ShoppingAppSessionManager
import com.biva.library.app.data.source.FakeAuthRepository
import com.biva.library.app.data.source.repository.AuthRepoInterface
import com.biva.library.app.data.utils.StoreDataStatus
import com.biva.library.app.getOrAwaitValue
import org.hamcrest.Matchers.`is`
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeViewModelTest {
	private lateinit var homeViewModel: HomeViewModel
	private lateinit var authRepository: AuthRepoInterface

	@get:Rule
	var instantTaskExecutorRule = InstantTaskExecutorRule()

	@Before
	fun setUp() {
		val sessionManager = ShoppingAppSessionManager(ApplicationProvider.getApplicationContext())
		authRepository = FakeAuthRepository(sessionManager)
		ServiceLocator.authRepository = authRepository
		homeViewModel = HomeViewModel(ApplicationProvider.getApplicationContext())
	}

	@After
	fun cleanUp() {
		ServiceLocator.resetRepository()
	}

    @Test
    fun setDataLoaded_setsValue() {
        homeViewModel.setDataLoaded()
        val result = homeViewModel.storeDataStatus.getOrAwaitValue()
        assertThat(result, `is`(StoreDataStatus.DONE))
    }

	@Test
	fun filterProducts_All() {
		homeViewModel.filterProducts("All")
		val result =  homeViewModel.filterCategory.getOrAwaitValue()
		assertThat(result, `is`("All"))
	}

	@Test
	fun filterProducts_Shoes() {
		homeViewModel.filterProducts("Shoes")
		val result =  homeViewModel.filterCategory.getOrAwaitValue()
		assertThat(result, `is`("Shoes"))
	}


}