package com.rrat.lyricsapp.viewmodel

import org.junit.Assert
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class HitsArtistViewModelTest {

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    private val receivedUiState = mutableListOf<UiState>()

    @Test
    fun `Should return Success when network request is successful`() {
        //Arrange
        val fakeApi = FakeSuccessApi()
        val viewModel = HitsArtistViewModel(fakeApi)

        observeViewModel(viewModel)
        //Act
        viewModel.performingSingleNetworkRequest("data")

        //Assert
        Assert.assertEquals(
            listOf(
                UiState.Loading, UiState.Success("data")
            ), receivedUiState
        )
    }

    private fun observeViewModel(viewModel: HitsArtistViewModel) {
        viewModel.uiState().observeForever { uiState ->
            if (uiState != null) {
                receivedUiState.add(uiState)
            }
        }
    }
}