import com.buiguesortola.cryptotracker.dto.AssetDto
import com.buiguesortola.cryptotracker.dto.toCoin
import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

class CoinMapperUnitTests {

    @Test
    fun `toCoin converts price correctly and keeps raw change percent`() {
        val dto = AssetDto(
            id = "bitcoin",
            name = "Bitcoin",
            symbol = "BTC",
            priceUsd = "50000.5555",
            changePercent24Hr = "2.5678"
        )
        val euroRate = 0.92
        val coin = dto.toCoin(euroRate)

        assertEquals(BigDecimal("54348.43"), coin.priceInEuro.setScale(2, BigDecimal.ROUND_HALF_UP))
        assertEquals(2.5678, coin.changePercent, 0.0001)
    }
}