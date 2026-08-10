import com.bitpanda.livechallenge.dto.AssetDto
import com.bitpanda.livechallenge.dto.toCoin
import org.junit.Assert.assertEquals
import org.junit.Test

class CoinMapperUnitTests {

    @Test
    fun `toCoin formats percentage to two decimals`() {
        val dto = AssetDto(
            id = "bitcoin",
            name = "Bitcoin",
            symbol = "BTC",
            priceUsd = "50000.5555",
            changePercent24Hr = "2.5678"
        )
        val euroRate = 0.92
        val coin = dto.toCoin(euroRate)

        assertEquals(54348.43, coin.priceInEuro, 0.001)
        assertEquals("2.57", coin.changePercentFormatted)
    }
}