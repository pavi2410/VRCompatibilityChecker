package me.pavi2410.vrcc

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.pavi2410.vrcc.models.DetailModel
import me.pavi2410.vrcc.models.Results
import me.pavi2410.vrcc.ui.Colors
import me.pavi2410.vrcc.ui.Icons

@Composable
fun MainScreen() {
    val results = computeCompatibilityResults()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Text(
            text = "VR Compatibility Checker",
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.padding(8.dp)
        )

        MainContent(results)
    }
}

@Composable
fun MainContent(results: Results) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item {
            ResultMessage(results.isCompatible)
        }

        items(results.detailModel, key = { it.name }) { model ->
            DetailRow(model)
        }

        item {
            AboutThisApp()
        }
    }
}

@Composable
fun ResultMessage(isCompatible: Boolean) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(16.dp)
    ) {
        Image(
            painterResource(
                id = if (isCompatible)
                    R.drawable.party_face
                else
                    R.drawable.loudly_crying_face
            ),
            null,
            modifier = Modifier.size(96.dp)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = if (isCompatible)
                "Congratulations!!! Your device is VR compatible"
            else
                "Oops... Your device is not VR compatible",
            style = MaterialTheme.typography.h5
        )
    }
}

@Composable
fun DetailRow(model: DetailModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(1f)
        ) {
            Icon(
                model.icon, null,
                tint = Color.White,
                modifier = Modifier
                    .size(32.dp)
                    .background(model.iconColor, shape = RoundedCornerShape(8.dp))
                    .padding(4.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = model.name,
                fontSize = 22.sp
            )
            Spacer(Modifier.width(8.dp))
            model.detail?.let {
                Text(
                    text = model.detail,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.onSecondary,
                    modifier = Modifier
                        .background(
                            MaterialTheme.colors.secondary,
                            RoundedCornerShape(50)
                        )
                        .padding(6.dp, 4.dp)
                )

            }
        }

        if (model.result) {
            Icon(
                Icons.Check, null,
                tint = Colors.Green500,
                modifier = Modifier.size(32.dp)
            )
        } else {
            Icon(
                Icons.Cross, null,
                tint = Colors.Red500,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
fun AboutThisApp() {
    val uriHandler = LocalUriHandler.current

    Card(
        border = BorderStroke(Dp.Hairline, Color.DarkGray),
        elevation = 0.dp,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Created By Pavitra (@pavi2410)",
                style = MaterialTheme.typography.body1
            )

            ClickableText(
                text = AnnotatedString(
                    text = "Rate this app ⭐⭐⭐⭐⭐",
                    spanStyle = SpanStyle(color = MaterialTheme.colors.onSecondary)
                ),
                style = MaterialTheme.typography.body1
            ) {
                uriHandler.openUri("https://play.google.com/store/apps/details?id=appinventor.ai_pavitragolchha.VR")
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                modifier = Modifier.fillMaxWidth()
            ) {
                val annotatedString = buildAnnotatedString {
                    pushStringAnnotation(
                        tag = "about-me",
                        annotation = "https://pavi2410.me"
                    )
                    withStyle(style = SpanStyle(color = MaterialTheme.colors.onSecondary)) {
                        append("About me")
                    }

                    withStyle(style = SpanStyle(color = MaterialTheme.colors.onBackground)) {
                        append(" • ")
                    }

                    pushStringAnnotation(
                        tag = "view-source",
                        annotation = "https://github.com/pavi2410/VRCompatibilityChecker"
                    )
                    withStyle(style = SpanStyle(color = MaterialTheme.colors.onSecondary)) {
                        append("View Source")
                    }

                    withStyle(style = SpanStyle(color = MaterialTheme.colors.onBackground)) {
                        append(" • ")
                    }

                    pushStringAnnotation(
                        tag = "privacy-policy",
                        annotation = "https://pavi2410.me/privacy_policy/VR"
                    )

                    withStyle(style = SpanStyle(color = MaterialTheme.colors.onSecondary)) {
                        append("Privacy Policy")
                    }

                    pop()
                }

                ClickableText(
                    text = annotatedString,
                    style = MaterialTheme.typography.body1
                ) { offset ->
                    annotatedString.getStringAnnotations(
                        tag = "about-me",
                        start = offset,
                        end = offset
                    ).firstOrNull()?.let {
                        uriHandler.openUri(it.item)
                    }

                    annotatedString.getStringAnnotations(
                        tag = "view-source",
                        start = offset,
                        end = offset
                    ).firstOrNull()?.let {
                        uriHandler.openUri(it.item)
                    }

                    annotatedString.getStringAnnotations(
                        tag = "privacy-policy",
                        start = offset,
                        end = offset
                    ).firstOrNull()?.let {
                        uriHandler.openUri(it.item)
                    }
                }
            }
        }
    }
}