package me.pavi2410.vrcc

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.statusBarsHeight
import me.pavi2410.vrcc.models.DetailModel
import me.pavi2410.vrcc.models.Results
import me.pavi2410.vrcc.ui.checkIcon
import me.pavi2410.vrcc.ui.crossIcon
import me.pavi2410.vrcc.ui.greenA400
import me.pavi2410.vrcc.ui.red500

@Composable
fun MainScreen(mvm: MainViewModel) {
    val results = mvm.results

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        Spacer(
            Modifier
                .statusBarsHeight()
                .fillMaxWidth()
        )

        TopAppBar(backgroundColor = Color.Transparent, elevation = 0.dp) {
            Text(
                text = "VR Compatibility Checker",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.fillMaxWidth()
            )
        }

        MainContent(results)
    }
}

@Composable
fun MainContent(results: Results) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 32.dp, horizontal = 16.dp)
    ) {
        Text(
            text = if (results.isCompatible) "🥳" else "😢",
            fontSize = 64.sp,
            color = MaterialTheme.colors.onBackground
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = if (results.isCompatible) "Congratulations! Your device is compatible" else "Oops! Your device is not compatible",
            fontSize = 32.sp,
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.ExtraBold
        )
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(results.detailModel, key = { it.name }) { model ->
            DetailRow(model)
        }
    }
}

@Composable
fun DetailRow(model: DetailModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .background(
                    MaterialTheme.colors.secondary,
                    RoundedCornerShape(topEndPercent = 50, bottomEndPercent = 50)
                )
                .padding(16.dp, 8.dp)
                .weight(1f)
        ) {
            Icon(
                painterResource(model.icon), null,
                tint = MaterialTheme.colors.onSecondary,
                modifier = Modifier.size(32.dp)
            )
            Spacer(Modifier.width(16.dp))
            Text(
                text = model.name,
                fontSize = 24.sp,
                color = MaterialTheme.colors.onSecondary,
                textAlign = TextAlign.Center
            )
            model.detail?.let {
                Text(
                    text = model.detail,
                    fontSize = 16.sp,
                    color = Color(0xFF1976D2),
                    textAlign = TextAlign.Center
                )
            }
        }

        if (model.result) {
            Icon(
                imageVector = checkIcon,
                tint = greenA400,
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .size(32.dp)
            )
        } else {
            Icon(
                imageVector = crossIcon,
                tint = red500,
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .size(32.dp)
            )
        }
    }
}
