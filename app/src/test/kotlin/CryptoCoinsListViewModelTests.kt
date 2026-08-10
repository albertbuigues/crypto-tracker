import app.cash.turbine.test
import com.bitpanda.livechallenge.domain.CryptoError
import com.bitpanda.livechallenge.domain.TOP_TEN_FILTER
import com.bitpanda.livechallenge.domain.UiEvent
import com.bitpanda.livechallenge.domain.WORST_TEN_FILTER
import com.bitpanda.livechallenge.domain.models.Coin
import com.bitpanda.livechallenge.domain.usecases.GetTopTenBestCoinsUseCase
import com.bitpanda.livechallenge.domain.usecases.GetTopTenWorstCoinsUseCase
import com.bitpanda.livechallenge.ui.states.UiState
import com.bitpanda.livechallenge.ui.viewmodels.CryptoCoinsListViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class CryptoCoinsListViewModelTests {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: CryptoCoinsListViewModel
    private lateinit var getTopTen: GetTopTenBestCoinsUseCase
    private lateinit var getWorstTen: GetTopTenWorstCoinsUseCase

    @Before
    fun setup() {
        getTopTen = mockk()
        getWorstTen = mockk()
    }

    @Test
    fun `when fetchCryptoCoins called then first state is Loading`() = runTest {
        coEvery { getTopTen() } returns Result.success(emptyList())

        initViewModel()

        viewModel.uiState.test {
            assertEquals(
                UiState.Loading, awaitItem()
            )
        }
    }

    @Test
    fun `fetchCryptoCoins should emit ShowNetworkError event when NetworkError is thrown`() = runTest {
        coEvery { getTopTen() } returns Result.failure(CryptoError.NetworkError(""))

        initViewModel()

        viewModel.uiState.test {
            assertEquals(UiState.Loading, awaitItem())
            assertEquals(UiState.Error, awaitItem())
        }
        viewModel.uiEvents.test {
            assertEquals(UiEvent.ShowNetworkError, awaitItem())
        }
    }

    @Test
    fun `fetchCryptoCoins should emit ShowEuroNotFoundError event when EuroNotFoundError is thrown`() = runTest {
        coEvery { getTopTen() } returns Result.failure(CryptoError.EuroRateNotFoundError(""))

        initViewModel()

        viewModel.uiState.test {
            assertEquals(UiState.Loading, awaitItem())
            assertEquals(UiState.Error, awaitItem())
        }
        viewModel.uiEvents.test {
            assertEquals(UiEvent.ShowEuroNotFoundError, awaitItem())
        }
    }

    @Test
    fun `fetchCryptoCoins should emit GenericError event when other error is thrown`() = runTest {
        coEvery { getTopTen() } returns Result.failure(IOException(""))

        initViewModel()

        viewModel.uiState.test {
            assertEquals(UiState.Loading, awaitItem())
            assertEquals(UiState.Error, awaitItem())
        }
        viewModel.uiEvents.test {
            assertEquals(UiEvent.ShowGenericError, awaitItem())
        }
    }

    @Test
    fun `fetchCryptoCoins should emit Success state with CryptoCoinsListUIState when successful`() = runTest {
        val mockCoin = mockk<Coin>(relaxed = true) {
            every { id } returns "1"
            every { name } returns "Bitcoin"
            every { symbol } returns "BTC"
            every { priceInEuro } returns 50000.0
            every { changePercent } returns 0.5
            every { changePercentFormatted } returns "0.5"
        }
        coEvery { getTopTen() } returns Result.success(listOf(mockCoin))

        initViewModel()

        viewModel.uiState.test {
            assertEquals(UiState.Loading, awaitItem())

            advanceTimeBy(550)
            val success = awaitItem() as UiState.Success

            assertEquals(TOP_TEN_FILTER, success.content.selectedChip)
            assertEquals(1, success.content.coinsList.size)
            assertEquals("Bitcoin", success.content.coinsList[0].name)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `manageFilterState does not call fetchCrypto if state is not Success`() = runTest {
        coEvery { getTopTen() } returns Result.failure(IOException())

        initViewModel()
        advanceUntilIdle()

        assertTrue(viewModel.uiState.value is UiState.Error)

        viewModel.manageFilterState(WORST_TEN_FILTER)
        runCurrent()

        viewModel.uiState.test {
            val currentState = awaitItem()
            assert(currentState is UiState.Error)
            coVerify(exactly = 0) { getWorstTen() }
            expectNoEvents()
        }
    }

    @Test
    fun `manageFilterState does not call fetchCrypto if newSelectedFilter is the current selected`() = runTest {
        coEvery { getTopTen() } returns Result.success(emptyList())

        initViewModel()
        advanceUntilIdle()

        viewModel.manageFilterState(TOP_TEN_FILTER)
        runCurrent()

        coVerify(exactly = 1) { getTopTen() }
        coVerify(exactly = 0) { getWorstTen() }
    }

    @Test
    fun `when changing selected filter then fetchCoins is called with newFilter`() = runTest {
        val mockCoin = mockk<Coin>(relaxed = true) {
            every { id } returns "1"
            every { name } returns "Bitcoin"
            every { symbol } returns "BTC"
            every { priceInEuro } returns 50000.0
            every { changePercent } returns 0.5
            every { changePercentFormatted } returns "0.5"
        }
        coEvery { getTopTen() } returns Result.success(listOf(mockCoin))
        coEvery { getWorstTen() } returns Result.success(listOf(mockCoin))

        initViewModel()
        advanceUntilIdle()

        viewModel.manageFilterState(WORST_TEN_FILTER)
        advanceUntilIdle()

        coVerify(exactly = 1) { getTopTen() }
        coVerify(exactly = 1) { getWorstTen() }

        viewModel.manageFilterState(TOP_TEN_FILTER)
        advanceUntilIdle()

        coVerify(exactly = 2) { getTopTen() }
        coVerify(exactly = 1) { getWorstTen() }
    }

    @Test
    fun `when refreshData called then fetchCryptoCoins is called with currentState`() = runTest {
        val mockCoin = mockk<Coin>(relaxed = true) {
            every { id } returns "1"
            every { name } returns "Bitcoin"
            every { symbol } returns "BTC"
            every { priceInEuro } returns 50000.0
            every { changePercent } returns 0.5
            every { changePercentFormatted } returns "0.5"
        }
        coEvery { getTopTen() } returns Result.success(listOf(mockCoin))
        coEvery { getWorstTen() } returns Result.success(listOf(mockCoin))

        initViewModel()
        advanceUntilIdle()

        viewModel.manageFilterState(WORST_TEN_FILTER)
        advanceUntilIdle()

        viewModel.refreshData()
        advanceUntilIdle()

        coVerify(exactly = 1) { getTopTen() }
        coVerify(exactly = 2) { getWorstTen() }
    }

    private fun initViewModel() {
        viewModel = CryptoCoinsListViewModel(getTopTen, getWorstTen)
    }
}