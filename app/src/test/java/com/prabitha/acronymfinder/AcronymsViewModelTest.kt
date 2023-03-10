package com.prabitha.acronymfinder

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.prabitha.acronymfinder.data.AcronymRepository
import com.prabitha.acronymfinder.ui.acronyms.AcronymViewModel
import com.prabitha.acronymfinder.utils.Constants
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import retrofit2.Response

@ExperimentalCoroutinesApi
class AcronymsViewModelTest {


    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @RelaxedMockK
    lateinit var acronymRepository: AcronymRepository

    @InjectMockKs
    lateinit var acronymViewModel: AcronymViewModel


    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Given when the acronymservice result is successful then the result`() {
        runTest {
            coEvery { acronymRepository.getAcronyms("abc") } returns Response.success(
                listOf(
                    getAcronymResponse()
                )
            )


            acronymViewModel.getAcronyms("abc")
            val value = acronymViewModel.acronyms.getOrAwaitValue()
            assert(value.isNotEmpty())
        }
    }


        @OptIn(ExperimentalCoroutinesApi::class)
        @Test
        fun `Given when there is empty result, show error message should be true`() {
            runTest {
                coEvery { acronymRepository.getAcronyms("abc") } returns Response.success(
                    listOf(
                    )
                )
                acronymViewModel.getAcronyms("abc")
                val value = acronymViewModel.acronyms.getOrAwaitValue() ?: emptyList()
                assert(value.isEmpty())

                val showError = acronymViewModel.showErrorMessage.getOrAwaitValue()
                assert(showError)

                val errorMessage = acronymViewModel.errorMessage.getOrAwaitValue()
                assert(errorMessage == Constants.NO_DATA_FOUND)
            }

        }

    }


    @ExperimentalCoroutinesApi
    class MainCoroutineRule(private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()) :
        TestWatcher() {

        override fun starting(description: Description) {
            super.starting(description)
            Dispatchers.setMain(dispatcher)
        }

        override fun finished(description: Description) {
            super.finished(description)
            Dispatchers.resetMain()
        }
    }
