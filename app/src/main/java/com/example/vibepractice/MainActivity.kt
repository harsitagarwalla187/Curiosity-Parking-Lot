package com.example.vibepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.vibepractice.core.database.AppDatabase
import com.example.vibepractice.core.theme.VIbePracticeTheme
import com.example.vibepractice.curiosity.CuriosityRepositoryImpl
import com.example.vibepractice.curiosity.CuriosityScreen
import com.example.vibepractice.curiosity.CuriosityViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: CuriosityViewModel by viewModels {
        val database = AppDatabase.getDatabase(applicationContext)
        val repository = CuriosityRepositoryImpl(database.curiosityDao())
        CuriosityViewModel.Factory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VIbePracticeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CuriosityScreen(viewModel = viewModel)
                }
            }
        }
    }
}
