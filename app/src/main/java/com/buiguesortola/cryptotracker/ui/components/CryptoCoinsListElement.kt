package com.buiguesortola.cryptotracker.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.buiguesortola.cryptotracker.testtags.COINS_LIST_ELEM_TAG
import com.buiguesortola.cryptotracker.ui.theme.CryptoTrackerTheme
import com.buiguesortola.cryptotracker.ui.theme.NegativeRed
import com.buiguesortola.cryptotracker.ui.theme.PositiveGreen
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

@Composable
fun StatefulCryptoCoinsListElement(
    coinName: String,
    symbol: String,
    priceInEuro: BigDecimal,
    changePercent: Double
) {
    StatelessCryptoCoinsListElement(
        coinName, priceInEuro, symbol, changePercent
    )
}

@Composable
private fun StatelessCryptoCoinsListElement(
    coinName: String = "Bitcoin",
    priceInEuro: BigDecimal = BigDecimal("52000.20"),
    symbol: String = "BTC",
    changePercent: Double = 0.25
) {
    val priceFormat = NumberFormat.getCurrencyInstance(Locale.getDefault()).apply {
        currency = Currency.getInstance("EUR")
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }
    val percentFormat = NumberFormat.getPercentInstance(Locale.getDefault()).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }
    val percentageColor = if (changePercent > 0) PositiveGreen else NegativeRed

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(2.dp))
            .background(Color.White)
            .padding(12.dp)
            .testTag(COINS_LIST_ELEM_TAG)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = coinName,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.End,
                text = priceFormat.format(priceInEuro),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = symbol,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.End,
                text = percentFormat.format(changePercent / 100),
                style = MaterialTheme.typography.bodyMedium,
                color = percentageColor
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun PreviewListElement() {
    CryptoTrackerTheme {
        StatelessCryptoCoinsListElement()
    }
}