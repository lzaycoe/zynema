package io.lzaycoe.zynema.ui.screens.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import io.lzaycoe.zynema.ui.screens.mainscreen.MainScreen
import io.lzaycoe.zynema.ui.theme.HiltMVVMComposeMovieTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val splashViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply { setKeepOnScreenCondition { splashViewModel.isLoading.value } }
        setContent { HiltMVVMComposeMovieTheme { MainScreen() } }
    }
}
