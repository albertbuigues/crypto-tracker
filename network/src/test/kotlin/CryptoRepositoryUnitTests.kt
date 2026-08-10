import com.bitpanda.livechallenge.api.CryptoApi
import com.bitpanda.livechallenge.domain.CryptoError
import com.bitpanda.livechallenge.domain.repository.CryptosRepository
import com.bitpanda.livechallenge.dto.AssetResponse
import com.bitpanda.livechallenge.dto.RatesResponse
import com.bitpanda.livechallenge.repository.CryptoRepositoryImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class CryptoRepositoryUnitTests {

    private val api: CryptoApi = mockk()
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repository: CryptosRepository

    @Before
    fun setup() {
        repository = CryptoRepositoryImpl(api, testDispatcher)
    }

    @Test
    fun `getCoins returns success when both api calls succeed`() = runTest(testDispatcher) {

        coEvery { api.getAssets() } returns AssetResponse(createFakeAssetsList())
        coEvery { api.getRates() } returns RatesResponse(createFakeRatesList())

        val res = repository.getCoins()
        advanceUntilIdle()

        val coins = res.getOrThrow()

        assertTrue(res.isSuccess)
        assertEquals(6, coins.size)
        coVerify(exactly = 1) { api.getAssets() }
        coVerify(exactly = 1) { api.getRates() }
    }

    @Test
    fun `getCoins calculates price in Euro correctly`() = runTest(testDispatcher) {

        coEvery { api.getAssets() } returns AssetResponse(createFakeAssetsList())
        coEvery { api.getRates() } returns RatesResponse(createFakeRatesList())

        val res = repository.getCoins()
        advanceUntilIdle()

        val coins = res.getOrThrow()
        val bitcoin = coins.find { it.id == "bitcoin" }

        assertNotNull(bitcoin)
        assertEquals(69021.74, bitcoin?.priceInEuro ?: 0.0, 0.001)
    }

    @Test
    fun `getCoins returns EuroRateNotFoundError when EUR symbol is missing in rates`() = runTest(testDispatcher) {

        val ratesWithNoEuro = createFakeRatesListWithoutEur()
        coEvery { api.getAssets() } returns AssetResponse(createFakeAssetsList())
        coEvery { api.getRates() } returns RatesResponse(ratesWithNoEuro)

        val res = repository.getCoins()
        advanceUntilIdle()

        assertTrue(res.isFailure)
        assertTrue(res.exceptionOrNull() is CryptoError.EuroRateNotFoundError)
    }

    @Test
    fun `getCoins returns NetworkError when API throws IOException`() = runTest(testDispatcher) {
        coEvery { api.getAssets() } coAnswers { throw IOException("No internet connection") }
        coEvery { api.getRates() } returns RatesResponse(createFakeRatesList())

        val res = repository.getCoins()
        advanceUntilIdle()

        assertTrue(res.isFailure)
        assertTrue(res.exceptionOrNull() is CryptoError.NetworkError)
    }
}