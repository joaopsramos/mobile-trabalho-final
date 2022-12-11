package com.example.grocelist

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.grocelist.model.ShoppingItem
import com.example.grocelist.ui.history.ARG_CART_ID
import com.example.grocelist.ui.item.ShoppingItemActivity
import com.example.grocelist.ui.shopping_cart.ShoppingCart
import com.example.grocelist.ui.shopping_cart.ShoppingCartViewModel
import com.example.grocelist.ui.routes.Screen
import com.example.grocelist.ui.shopping_cart.AddCartActivity
import com.example.grocelist.ui.shopping_cart.ImportCartActivity
import com.example.grocelist.ui.theme.GrocelistTheme
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.grocelist.ui.history.History
import com.example.grocelist.ui.history.HistoryDetailsActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel: ShoppingCartViewModel by viewModel()
            val userId = intent.getLongExtra("userId", -1)
            val carts = viewModel.allCarts(userId).collectAsState(initial = listOf())
            val lastCart = viewModel.getCart(userId).collectAsState(initial = null)

            GrocelistTheme {
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        BottomNavigation {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination

                            listOf(
                                Screen.ShoppingCart,
                                Screen.History
                            ).forEach { screen ->
                                BottomNavigationItem(
                                    icon = {
                                        Icon(
                                            screen.icon,
                                            contentDescription = null
                                        )
                                    },
                                    label = { Text(screen.name) },
                                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController,
                        startDestination = Screen.ShoppingCart.route,
                        Modifier.padding(innerPadding)
                    ) {
                        val onItemClick: (ShoppingItem) -> Unit = { item ->
                            val intent =
                                Intent(this@MainActivity, ShoppingItemActivity::class.java).apply {
                                    putExtra("id", item.id)
                                    putExtra("cartId", lastCart.value?.id)
                                }
                            startActivity(intent)
                        }

                        composable(Screen.ShoppingCart.route) {
                            val onAddClick: () -> Unit = {
                                startActivity(
                                    Intent(
                                        this@MainActivity,
                                        ShoppingItemActivity::class.java
                                    ).apply {
                                        putExtra("cartId", lastCart.value?.id)
                                    }
                                )
                            }

                            val onImportClick: () -> Unit = {
                                startActivity(
                                    Intent(
                                        this@MainActivity,
                                        ImportCartActivity::class.java
                                    ).apply {
                                        putExtra("cartId", lastCart.value?.id)
                                    }
                                )
                            }

                            val onSubAddClick: () -> Unit = {
                                startActivity(
                                    Intent(
                                        this@MainActivity,
                                        AddCartActivity::class.java
                                    ).apply {
                                        putExtra("userId", userId)
                                    }
                                )
                            }

                            ShoppingCart(
                                viewModel,
                                userId,
                                onAddClick = onAddClick,
                                onItemClick = onItemClick,
                                onImportClick = onImportClick,
                                onSubAddClick = onSubAddClick
                            )
                        }

                        composable(Screen.History.route) {
                            History(items = carts.value,
                                onDeleteClick =  { id -> viewModel.deleteCart(id) },
                                onItemClick = {
                                val intent =
                                    Intent(this@MainActivity, HistoryDetailsActivity::class.java)
                                intent.putExtra(ARG_CART_ID, it.id)
                                startActivity(intent)
                            })
                        }
                    }
                }
            }
        }
    }
}