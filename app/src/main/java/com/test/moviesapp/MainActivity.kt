package com.test.moviesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.test.moviesapp.presentation.navigation.RootNavigationGraph
import com.test.moviesapp.ui.theme.MoviesappTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesappTheme {
                // A surface container using the 'background' color from the theme
                RootNavigationGraph()
            }
        }
    }
}
