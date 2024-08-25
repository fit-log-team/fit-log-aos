package com.example.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.example.presentation.databinding.FragmentTMapBinding
import com.example.presentation.ui.theme.FitLogTheme
import com.skt.tmap.TMapView
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class TMapFragment(val tMapView: TMapView): Fragment() {
    private lateinit var binding: FragmentTMapBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.i("TEST_LOG | TMapFragment INIT")
        binding = FragmentTMapBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()
    }


    private fun initLayout() {
        val parent = tMapView.parent as? ViewGroup
        parent?.removeView(tMapView)
        binding.clMap.addView(tMapView)

        binding.composeView.setContent {
            FitLogTheme {
                var value by remember {
                    mutableIntStateOf(0)
                }
                Text(
                    modifier = Modifier,
                    text = value.toString(),
                    fontSize = 15.sp
                )

                Button(
                    modifier = Modifier.width(100.dp).height(100.dp),
                    onClick = {
                    value++
                }) {

                }
            }

        }
    }
}