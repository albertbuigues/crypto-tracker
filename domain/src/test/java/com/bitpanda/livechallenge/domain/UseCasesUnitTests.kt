package com.bitpanda.livechallenge.domain

import com.bitpanda.livechallenge.domain.models.Coin
import com.bitpanda.livechallenge.domain.repository.CryptosRepository
import com.bitpanda.livechallenge.domain.usecases.GetTopTenBestCoinsUseCase
import com.bitpanda.livechallenge.domain.usecases.GetTopTenWorstCoinsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UseCasesUnitTests {

    private val repository: CryptosRepository = mockk()
    lateinit var totalCoins: List<Coin>

    @Before
    fun setup() {
        totalCoins = createFakeCoins()
        coEvery { repository.getCoins() } returns Result.success(totalCoins)
    }

    @Test
    fun `GetTopTenBestCoinsUseCase returns top ten best coins`() = runTest(StandardTestDispatcher()) {
        val expectedTop10 = listOf(
            totalCoins.find { it.id == "dogecoin" },
            totalCoins.find { it.id == "pepe" },
            totalCoins.find { it.id == "solana" },
            totalCoins.find { it.id == "avalanche" },
            totalCoins.find { it.id == "chainlink" },
            totalCoins.find { it.id == "bitcoin" },
            totalCoins.find { it.id == "litecoin" },
            totalCoins.find { it.id == "polkadot" },
            totalCoins.find { it.id == "polygon" },
            totalCoins.find { it.id == "ethereum" }
        )

        val usecase = GetTopTenBestCoinsUseCase(repository)
        val res = usecase.invoke().getOrThrow()

        assertEquals(expectedTop10, res)
    }

    @Test
    fun `GetTopTenWorstCoinsUseCase returns top ten worst coins`() = runTest(StandardTestDispatcher()) {
        val expectedWorst10 = listOf(
            totalCoins.find { it.id == "shiba" },
            totalCoins.find { it.id == "cardano" },
            totalCoins.find { it.id == "algorand" },
            totalCoins.find { it.id == "cosmos" },
            totalCoins.find { it.id == "ripple" },
            totalCoins.find { it.id == "ethereum" },
            totalCoins.find { it.id == "polygon" },
            totalCoins.find { it.id == "polkadot" },
            totalCoins.find { it.id == "litecoin" },
            totalCoins.find { it.id == "bitcoin" }
        )

        val usecase = GetTopTenWorstCoinsUseCase(repository)
        val res = usecase.invoke().getOrThrow()

        assertEquals(expectedWorst10, res)
    }

    fun createFakeCoins(): List<Coin> {
        return listOf(
            Coin("bitcoin", "BTC", "Bitcoin", 60000.0, 1.5, "1.50%"),       // 6a
            Coin("ethereum", "ETH", "Ethereum", 3500.0, -0.8, "-0.80%"),    // 10a
            Coin("solana", "SOL", "Solana", 150.0, 8.4, "8.40%"),           // 2a (Top)
            Coin("cardano", "ADA", "Cardano", 0.5, -3.2, "-3.20%"),         // 14a (Worst)
            Coin("polkadot", "DOT", "Polkadot", 7.0, 0.0, "0.00%"),         // 8a
            Coin("dogecoin", "DOGE", "Dogecoin", 0.16, 15.2, "15.20%"),     // 1a (Top)
            Coin("ripple", "XRP", "Ripple", 0.6, -1.2, "-1.20%"),           // 11a
            Coin("avalanche", "AVAX", "Avalanche", 40.0, 4.5, "4.50%"),     // 3a (Top)
            Coin("chainlink", "LINK", "Chainlink", 18.0, 2.1, "2.10%"),     // 5a
            Coin("pepe", "PEPE", "Pepe", 0.00001, 12.0, "12.00%"),          // 4a (Top)
            Coin("shiba", "SHIB", "Shiba Inu", 0.00002, -5.5, "-5.50%"),    // 15a (Worst)
            Coin("polygon", "MATIC", "Polygon", 0.7, -0.2, "-0.20%"),       // 9a
            Coin("litecoin", "LTC", "Litecoin", 85.0, 0.5, "0.50%"),        // 7a
            Coin("algorand", "ALGO", "Algorand", 0.2, -2.5, "-2.50%"),      // 13a (Worst)
            Coin("cosmos", "ATOM", "Cosmos", 9.0, -1.8, "-1.80%")           // 12a (Worst)
        )
    }
}