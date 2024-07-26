package com.test.moviesapp.usecases

import androidx.paging.PagingData
import com.test.moviesapp.domain.MovieDomain
import com.test.moviesapp.usecases.data.FakeRepositoryErrorApi
import com.test.moviesapp.usecases.data.FakeRepositorySuccessApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException

class GetPopularMoviesUseCaseTest {

    private lateinit var sut: GetPopularMoviesUseCase
    private lateinit var sutSuccess: GetPopularMoviesUseCase

    private lateinit var fakeRepositoryFailureApi: FakeRepositoryErrorApi
    private lateinit var fakeRepositorySuccessApi: FakeRepositorySuccessApi

    private var listMovies = mutableListOf<PagingData<MovieDomain>>()

    @Before
    fun setUp() {
        fakeRepositoryFailureApi = FakeRepositoryErrorApi()
        sut = GetPopularMoviesUseCase(fakeRepositoryFailureApi)

        fakeRepositorySuccessApi = FakeRepositorySuccessApi()
        sutSuccess = GetPopularMoviesUseCase(fakeRepositorySuccessApi)
    }

    @Test(expected = HttpException::class)
    fun `should return exception when network request is failed`() = runBlocking {
        val result = sut.invoke()

        result.collect {
            listMovies += it
        }
        assert(listMovies.isEmpty())
    }

    @Test
    fun `should return success with list converted to domain when network request is success`() =
        runBlocking {

            val result = sutSuccess.invoke()

            result.collect {
                listMovies += it
            }

            assert(listMovies.isNotEmpty())
        }


    @After
    fun tearDown() {
    }
}