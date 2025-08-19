package me.pavi2410.vrcc

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.pavi2410.vrcc.models.DetailModel
import me.pavi2410.vrcc.ui.Colors
import me.pavi2410.vrcc.ui.Icons

@Composable
fun MainScreen() {
    val context = LocalContext.current
    val (isCompatible, details) = remember { computeCompatibilityResults(context) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        Text(
            text = "VR Compatibility Checker",
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(8.dp)
        )

        ResultMessage(isCompatible)

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .border(
                    DividerDefaults.Thickness,
                    DividerDefaults.color,
                    CardDefaults.outlinedShape
                )
                .clip(CardDefaults.outlinedShape)
        ) {
            itemsIndexed(details) { index, model ->
                if (index != 0) {
                    HorizontalDivider()
                }
                DetailRow(model)
            }
        }

        AboutThisApp()
    }
}

@Composable
fun ResultMessage(isCompatible: Boolean) {
    val emoji = if (isCompatible)
        R.drawable.party_face
    else
        R.drawable.loudly_crying_face

    val message = if (isCompatible)
        "Congratulations! Your device is VR compatible"
    else
        "Oops... Your device is not VR compatible"

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Image(
            painterResource(
                id = emoji
            ),
            null,
            modifier = Modifier.size(120.dp)
        )
        Text(
            text = message,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun DetailRow(model: DetailModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
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
            Spacer(Modifier.width(16.dp))
            Text(
                text = model.name,
                fontSize = 22.sp
            )
            Spacer(Modifier.width(8.dp))
            model.detail?.let {
                Text(
                    text = model.detail,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.secondary,
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
    val aboutMeUrl = "https://pavi2410.com"
    val viewSourceUrl = "https://github.com/pavi2410/VRCompatibilityChecker"
    val privacyPolicyUrl = "https://pavi2410.github.io/privacy_policy/VR"
    val rateAppUrl =
        "https://play.google.com/store/apps/details?id=appinventor.ai_pavitragolchha.VR"

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val uriHandler = LocalUriHandler.current
        FilledTonalButton(
            onClick = { uriHandler.openUri(rateAppUrl) }
        ) {
            Text("Rate this app")
        }

        Text(
            "Created By @pavi2410",
            style = MaterialTheme.typography.bodyLarge
                .copy(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f))
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            modifier = Modifier.fillMaxWidth()
        ) {
            BasicText(
                text = buildAnnotatedString {
                    withLink(
                        link = LinkAnnotation.Url(
                            aboutMeUrl,
                            styles = TextLinkStyles(style = SpanStyle(color = MaterialTheme.colorScheme.onSecondary))
                        )
                    ) {
                        append("About me")
                    }

                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f))) {
                        append(" • ")
                    }

                    withLink(
                        link = LinkAnnotation.Url(
                            viewSourceUrl,
                            styles = TextLinkStyles(style = SpanStyle(color = MaterialTheme.colorScheme.onSecondary))
                        )
                    ) {
                        append("View Source")
                    }

                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f))) {
                        append(" • ")
                    }

                    withLink(
                        link = LinkAnnotation.Url(
                            privacyPolicyUrl,
                            styles = TextLinkStyles(style = SpanStyle(color = MaterialTheme.colorScheme.onSecondary))
                        )
                    ) {
                        append("Privacy Policy")
                    }
                },
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
