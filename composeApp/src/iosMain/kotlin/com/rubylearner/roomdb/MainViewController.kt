package com.rubylearner.roomdb

import androidx.compose.ui.window.ComposeUIViewController
import com.rubylearner.roomdb.di.initKoin

fun MainViewController() =  ComposeUIViewController( configure = { initKoin() }) { App() }