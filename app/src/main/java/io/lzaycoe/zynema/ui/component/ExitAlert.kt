package io.lzaycoe.zynema.ui.component

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import io.lzaycoe.zynema.R

@Composable
fun ShowExitDialog(activity: Activity?, openDialog: MutableState<Boolean>) {
    ExitAlertDialog(title = stringResource(R.string.close_the_app),
        description = stringResource(R.string.do_you_want_to_exit_the_app),
        onConfirm = { activity?.finish() },
        onDismiss = { openDialog.value = false })
}