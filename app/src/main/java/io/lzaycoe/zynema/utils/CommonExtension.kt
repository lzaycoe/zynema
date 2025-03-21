package io.lzaycoe.zynema.utils

import androidx.navigation.NavController
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.time.Duration.Companion.minutes

fun Int.hourMinutes(): String {
  return "${this.minutes.inWholeHours}h ${this % 60}m"
}

fun Int.genderInString(): String {
  return when (this) {
    1 -> "Nữ"
    2 -> "Name"
    else -> "N/A"
  }
}

fun Double.roundTo(numFractionDigits: Int): Double {
  val factor = 10.0.pow(numFractionDigits.toDouble())
  return (this * factor).roundToInt() / factor
}

fun NavController.singleTopNavigator(route: String) {
  this.navigate(route) {
    graph.startDestinationRoute?.let { route -> popUpTo(route) { inclusive = true } }
    launchSingleTop = true
    restoreState = true
  }
}
