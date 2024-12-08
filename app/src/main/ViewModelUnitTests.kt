package com.example.myapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myapp.models.Medicine
import com.example.myapp.repositories.LoginRepository
import com.example.myapp.repositories.MedicineRepository
import com.example.myapp.viewmodels.GreetingViewModel
import com.example.myapp.viewmodels.LoginViewModel
import com.example.myapp.viewmodels.MedicineViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class ViewModelUnitTests {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule() // For LiveData

    // Test 1: Valid Login Credentials
    @Test
    fun `valid login updates loginStatus to SUCCESS`() = runBlocking {
        // Arrange
        val mockRepository = mock(LoginRepository::class.java)
        val viewModel = LoginViewModel(mockRepository)
        val validUsername = "testUser"
        val validPassword = "password123"

        `when`(mockRepository.login(validUsername, validPassword))
            .thenReturn(Result.Success(true))

        // Act
        viewModel.login(validUsername, validPassword)

        // Assert
        assertEquals(LoginStatus.SUCCESS, viewModel.loginStatus.getOrAwaitValue())
        verify(mockRepository).login(validUsername, validPassword)
    }

    // Test 2: Fetch Medicines from Repository
    @Test
    fun `fetch medicines updates live data with medicine list`() = runBlocking {
        // Arrange
        val mockRepository = mock(MedicineRepository::class.java)
        val viewModel = MedicineViewModel(mockRepository)
        val mockMedicineList = listOf(Medicine("Paracetamol"), Medicine("Ibuprofen"))

        `when`(mockRepository.getMedicines())
            .thenReturn(Result.Success(mockMedicineList))

        // Act
        viewModel.fetchMedicines()

        // Assert
        assertEquals(mockMedicineList, viewModel.medicines.getOrAwaitValue())
        verify(mockRepository).getMedicines()
    }

    // Test 3: Generate Greeting Based on User's Name
    @Test
    fun `greeting view model returns personalized greeting`() {
        // Arrange
        val viewModel = GreetingViewModel()
        val username = "Alice"

        // Act
        val greeting = viewModel.getGreeting(username)

        // Assert
        assertEquals("Welcome, Alice!", greeting)
    }
}
