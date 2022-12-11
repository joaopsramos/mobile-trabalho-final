package com.example.grocelist.ui.history

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.grocelist.ui.GenericItemsList
import com.example.grocelist.ui.theme.GrocelistTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

const val ARG_CART_ID = "cartId"

class HistoryDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val cartId = intent.extras?.getLong(ARG_CART_ID, -1)?.takeIf { it != -1L }
            if (cartId == null) {
                finish()
                return@setContent
            }

            val viewModel: HistoryDetailsViewModel by viewModel()
            val cart = viewModel.cart(cartId).collectAsState(initial = null)
            val items = viewModel.items(cartId).collectAsState(initial = listOf())

            GrocelistTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Detalhes do histórico") },
                            backgroundColor = MaterialTheme.colors.primaryVariant,
                            navigationIcon = {
                                IconButton(onClick = { finish() }) {
                                    Icon(Icons.Filled.ArrowBack, "backIcon")
                                }
                            },
                        )
                    },
                    content = { padding ->
                        Box(modifier = Modifier.padding(padding)) {
                            GenericItemsList(
                                items = items,
                                title = cart.value?.name.orEmpty()
                            )
                        }
                    }
                )
            }
        }
    }

}