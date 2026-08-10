import com.bitpanda.livechallenge.dto.AssetDto
import com.bitpanda.livechallenge.dto.RateDto

private fun createFakeAssetDto(
    id: String = "bitcoin",
    priceUsd: String = "50000.0",
    symbol: String = "BTC",
    changePercent24Hr: String = "2.5"
) = AssetDto(
    id = id,
    symbol = symbol,
    priceUsd = priceUsd,
    changePercent24Hr = changePercent24Hr,
    name = id.replaceFirstChar { it.uppercase() }
)

private fun createFakeRateDto(
    symbol: String = "EUR",
    rateUsd: String = "0.92"
) = RateDto(
    id = "euro",
    symbol = symbol,
    rateUsd = rateUsd
)

fun createFakeAssetsList() = listOf(
    createFakeAssetDto("bitcoin", "63500.0", "BTC", "1.2"),
    createFakeAssetDto("ethereum", "3450.5", "ETH", "-0.5"),
    createFakeAssetDto("tether", "1.0", "USDT", "0.01"),
    createFakeAssetDto("solana", "145.2", "SOL", "5.4"),
    createFakeAssetDto("cardano", "0.45", "ADA", "-2.1"),
    createFakeAssetDto("polkadot", "7.2", "DOT", "1.1")
)

fun createFakeRatesList() = listOf(
    createFakeRateDto("EUR", "0.92"),
    createFakeRateDto("GBP", "0.78"),
    createFakeRateDto("JPY", "150.5"),
    createFakeRateDto("CNY", "7.2"),
    createFakeRateDto("AUD", "1.5"),
    createFakeRateDto("CAD", "1.35")
)

fun createFakeRatesListWithoutEur() = listOf(
    createFakeRateDto("GBP", "0.78"),
    createFakeRateDto("JPY", "150.5"),
    createFakeRateDto("CNY", "7.2"),
    createFakeRateDto("AUD", "1.5"),
    createFakeRateDto("CAD", "1.35")
)