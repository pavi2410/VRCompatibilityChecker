package me.pavi2410.vrcc.ui

import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.addPathNodes

private fun icon(name: String, fillColor: Color, pathData: String) = materialIcon(name) {
    materialPath {
        addPath(fill = SolidColor(fillColor), pathData = addPathNodes(pathData))
    }
}

object Icons {
    val Check = icon("check", Colors.Green500, "M9,16.17L4.83,12l-1.42,1.41L9,19 21,7l-1.41,-1.41z")

    val Cross = icon(
        "cross",
        Colors.Red500,
        "M19,6.41L17.59,5 12,10.59 6.41,5 5,6.41 10.59,12 5,17.59 6.41,19 12,13.41 17.59,19 19,17.59 13.41,12z"
    )

    val Accelerometer = icon(
        "accelerometer",
        Color.Black,
        "M12,2L16,6H13V13.85L19.53,17.61L21,15.03L22.5,20.5L17,21.96L18.53,19.35L12,15.58L5.47,19.35L7,21.96L1.5,20.5L3,15.03L4.47,17.61L11,13.85V6H8L12,2Z"
    )

    val Android = icon(
        "android",
        Color.Black,
        "M8,11.5A1.25,1.25 0 0,0 6.75,12.75A1.25,1.25 0 0,0 8,14A1.25,1.25 0 0,0 9.25,12.75A1.25,1.25 0 0,0 8,11.5M16,11.5A1.25,1.25 0 0,0 14.75,12.75A1.25,1.25 0 0,0 16,14A1.25,1.25 0 0,0 17.25,12.75A1.25,1.25 0 0,0 16,11.5M12,7C13.5,7 14.9,7.33 16.18,7.91L18.34,5.75C18.73,5.36 19.36,5.36 19.75,5.75C20.14,6.14 20.14,6.77 19.75,7.16L17.95,8.96C20.41,10.79 22,13.71 22,17H2C2,13.71 3.59,10.79 6.05,8.96L4.25,7.16C3.86,6.77 3.86,6.14 4.25,5.75C4.64,5.36 5.27,5.36 5.66,5.75L7.82,7.91C9.1,7.33 10.5,7 12,7Z"
    )

    val Compass = icon(
        "compass",
        Color.Black,
        "M7,17L10.2,10.2L17,7L13.8,13.8L7,17M12,11.1A0.9,0.9 0 0,0 11.1,12A0.9,0.9 0 0,0 12,12.9A0.9,0.9 0 0,0 12.9,12A0.9,0.9 0 0,0 12,11.1M12,2A10,10 0 0,1 22,12A10,10 0 0,1 12,22A10,10 0 0,1 2,12A10,10 0 0,1 12,2M12,4A8,8 0 0,0 4,12A8,8 0 0,0 12,20A8,8 0 0,0 20,12A8,8 0 0,0 12,4Z"
    )

    val Gyroscope = icon(
        "gyroscope",
        Color.Black,
        "M8,14.25L4.75,11H7C7.25,5.39 9.39,1 12,1C14,1 15.77,3.64 16.55,7.45C20.36,8.23 23,10 23,12C23,13.83 20.83,15.43 17.6,16.3L17.89,14.27C19.8,13.72 21,12.91 21,12C21,10.94 19.35,10 16.87,9.5C16.95,10.29 17,11.13 17,12C17,18.08 14.76,23 12,23C10.17,23 8.57,20.83 7.7,17.6L9.73,17.89C10.28,19.8 11.09,21 12,21C13.66,21 15,16.97 15,12C15,11 14.95,10.05 14.85,9.15C13.95,9.05 13,9 12,9L10.14,9.06L10.43,7.05L12,7C12.87,7 13.71,7.05 14.5,7.13C14,4.65 13.06,3 12,3C10.46,3 9.18,6.5 9,11H11.25L8,14.25M14.25,16L11,19.25V17C5.39,16.75 1,14.61 1,12C1,10.17 3.17,8.57 6.4,7.7L6.11,9.73C4.2,10.28 3,11.09 3,12C3,13.54 6.5,14.82 11,15V12.75L14.25,16Z"
    )

    val Ram = icon(
        "ram",
        Color.Black,
        "M17,17H7V7H17M21,11V9H19V7C19,5.89 18.1,5 17,5H15V3H13V5H11V3H9V5H7C5.89,5 5,5.89 5,7V9H3V11H5V13H3V15H5V17A2,2 0 0,0 7,19H9V21H11V19H13V21H15V19H17A2,2 0 0,0 19,17V15H21V13H19V11M13,13H11V11H13M15,9H9V15H15V9Z"
    )

    val Screen = icon(
        "screen",
        Color.Black,
        "M7,1A2,2 0 0,0 5,3V21A2,2 0 0,0 7,23H17A2,2 0 0,0 19,21V3A2,2 0 0,0 17,1H7M7,4H17V20H7V4M9,6V10H10.5V7.5H13V6H9M13.5,14V16.5H11V18H15V14H13.5Z"
    )
}
