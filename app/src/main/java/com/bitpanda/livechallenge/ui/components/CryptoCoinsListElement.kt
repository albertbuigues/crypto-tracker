package com.bitpanda.livechallenge.ui.components

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
import com.bitpanda.livechallenge.domain.COINS_LIST_ELEM_TAG
import com.bitpanda.livechallenge.ui.theme.BitpandaLiveChallengeTheme
import com.bitpanda.livechallenge.ui.theme.NegativeRed
import com.bitpanda.livechallenge.ui.theme.PositiveGreen

@Composable
fun StatefulCryptoCoinsListElement(
    coinName: String,
    symbol: String,
    priceInEuro: String,
    changePercentage: String
) {
    StatelessCryptoCoinsListElement(
        coinName, priceInEuro, symbol, changePercentage
    )
}

@Composable
private fun StatelessCryptoCoinsListElement(
    coinName: String = "Bitcoin",
    priceInEuro: String = "52000.20",
    symbol: String = "BTC",
    changePercentage: String = "0.25"
) {
    val percentageColor = if (changePercentage.toDouble() > 0) {
        PositiveGreen
    } else {
        NegativeRed
    }
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
                text = priceInEuro,
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
                text = "$changePercentage%",
                style = MaterialTheme.typography.bodyMedium,
                color = percentageColor
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun PreviewListElement() {
    BitpandaLiveChallengeTheme {
        StatelessCryptoCoinsListElement()
    }
}