package com.buiguesortola.cryptotracker.domain

import com.buiguesortola.cryptotracker.domain.models.Coin
import com.buiguesortola.cryptotracker.domain.repository.CryptosRepository
import com.buiguesortola.cryptotracker.domain.usecases.GetTopTenBestCoinsUseCase
import com.buiguesortola.cryptotracker.domain.usecases.GetTopTenWorstCoinsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal

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
    fun `GetTopTenBestCoinsUseCase returns top ten best coins`() =
        runTest(StandardTestDispatcher()) {
            val expectedTop10 =
                listOf(
                    totalCoins.find { it.id == "dogecoin" },
                    totalCoins.find { it.id == "pepe" },
                    totalCoins.find { it.id == "solana" },
                    totalCoins.find { it.id == "avalanche" },
                    totalCoins.find { it.id == "chainlink" },
                    totalCoins.find { it.id == "bitcoin" },
                    totalCoins.find { it.id == "litecoin" },
                    totalCoins.find { it.id == "polkadot" },
                    totalCoins.find { it.id == "polygon" },
                    totalCoins.find { it.id == "ethereum" },
                )

            val usecase = GetTopTenBestCoinsUseCase(repository)
            val res = usecase.invoke().getOrThrow()

            assertEquals(expectedTop10, res)
        }

    @Test
    fun `GetTopTenWorstCoinsUseCase returns top ten worst coins`() =
        runTest(StandardTestDispatcher()) {
            val expectedWorst10 =
                listOf(
                    totalCoins.find { it.id == "shiba" },
                    totalCoins.find { it.id == "cardano" },
                    totalCoins.find { it.id == "algorand" },
                    totalCoins.find { it.id == "cosmos" },
                    totalCoins.find { it.id == "ripple" },
                    totalCoins.find { it.id == "ethereum" },
                    totalCoins.find { it.id == "polygon" },
                    totalCoins.find { it.id == "polkadot" },
                    totalCoins.find { it.id == "litecoin" },
                    totalCoins.find { it.id == "bitcoin" },
                )

            val usecase = GetTopTenWorstCoinsUseCase(repository)
            val res = usecase.invoke().getOrThrow()

            assertEquals(expectedWorst10, res)
        }

    fun createFakeCoins(): List<Coin> =
        listOf(
            Coin("bitcoin", "BTC", "Bitcoin", BigDecimal(60000.0), 1.5),
            Coin("ethereum", "ETH", "Ethereum", BigDecimal(3500.0), -0.8),
            Coin("solana", "SOL", "Solana", BigDecimal(150.0), 8.4),
            Coin("cardano", "ADA", "Cardano", BigDecimal(0.5), -3.2),
            Coin("polkadot", "DOT", "Polkadot", BigDecimal(7.0), 0.0),
            Coin("dogecoin", "DOGE", "Dogecoin", BigDecimal(0.16), 15.2),
            Coin("ripple", "XRP", "Ripple", BigDecimal(0.6), -1.2),
            Coin("avalanche", "AVAX", "Avalanche", BigDecimal(40.0), 4.5),
            Coin("chainlink", "LINK", "Chainlink", BigDecimal(18.0), 2.1),
            Coin("pepe", "PEPE", "Pepe", BigDecimal(0.00001), 12.0),
            Coin("shiba", "SHIB", "Shiba Inu", BigDecimal(0.00002), -5.5),
            Coin("polygon", "MATIC", "Polygon", BigDecimal(0.7), -0.2),
            Coin("litecoin", "LTC", "Litecoin", BigDecimal(85.0), 0.5),
            Coin("algorand", "ALGO", "Algorand", BigDecimal(0.2), -2.5),
            Coin("cosmos", "ATOM", "Cosmos", BigDecimal(9.0), -1.8),
        )
}
