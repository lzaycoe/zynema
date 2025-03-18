package io.lzaycoe.zynema.ui.component.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import io.lzaycoe.zynema.ui.theme.subTitlePrimary

@Composable
fun SubtitlePrimary(text: String) {
  Text(text = text, style = MaterialTheme.typography.subTitlePrimary)
}
