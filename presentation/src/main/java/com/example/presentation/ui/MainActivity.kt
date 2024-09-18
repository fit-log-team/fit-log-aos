package com.example.presentation.ui

import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.presentation.R
import com.example.presentation.config.TMapConfig
import com.example.presentation.databinding.ActivityMainBinding
import com.example.presentation.ui.main.MainScreen2
import com.example.presentation.viewmodel.MainViewModel
import com.skt.tmap.TMapView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initTMap()
    }

    private fun initTMap() {
        TMapView(this).apply {
            setSKTMapApiKey(TMapConfig.API_KEY)
            setOnMapReadyListener {
                initLayout(this)
            }
        }
    }

    private fun initLayout(tMapView: TMapView) {
        binding.composeView.setContent {
            MainScreen2(
                context = this@MainActivity
                ,tMapView = {
                AndroidView(factory = { tMapView })
            })
        }

    }
}