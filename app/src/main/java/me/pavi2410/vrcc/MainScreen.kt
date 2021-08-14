package me.pavi2410.vrcc

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.pavi2410.vrcc.models.DetailModel

@Composable
fun MainScreen(mvm: MainViewModel) {
    val result by mvm.result.collectAsState(initial = null)

    if (result == null) {
        LinearProgressIndicator()
        return
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)) {
            Text(
                text = "🥳",
                fontSize = 64.sp
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = "Congratulations! Your device is VR compatible",
                fontSize = 24.sp
            )
        }

        result?.let {
            it.detailModel.forEach { model ->
                Detail(model)
            }
        }
    }
}

@Composable
fun Detail(model: DetailModel) {
    Card(
        shape = CutCornerShape(topStart = 16.dp, bottomEnd = 16.dp),
        border = BorderStroke(1.dp, Color(0xFF1976D2)),
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 8.dp)
                .fillMaxWidth()
        ) {
            Icon(
                painterResource(model.icon), null,
                tint = Color(0xFF1976D2),
                modifier = Modifier.size(48.dp)
            )
            Text(
                text = stringResource(model.name),
                fontSize = 20.sp,
                color = Color(0xFF1976D2),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = model.result.toString(),
                fontSize = 20.sp,
                color = Color(0xFF1976D2),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
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
    }
}
