package com.android.fetchandroidloganp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.android.fetchandroidloganp.repository.ItemRepositoryImpl
import com.android.fetchandroidloganp.ui.screens.FetchRewardsItemScreen.FetchRewardsItemScreen
import com.android.fetchandroidloganp.ui.screens.FetchRewardsItemScreen.ItemsViewModel
import com.android.fetchandroidloganp.ui.theme.FetchRewardsTakeHomeTestTheme

class MainActivity : ComponentActivity() {
    /**
     * I would really prefer to use Hilt DI here, but when I was trying to set it up it seemed
     * to struggle with the newest Kotlin version. Normally I would resolve the gradle issues but I
     * wanted to complete this take home in a timely manner. I haven't done Android professionally
     * for 2 years so my gradle build error resolution skills are a little rusty :)
     */
    val repository = ItemRepositoryImpl.getInstance()

    lateinit var viewModel: ItemsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, ItemsViewModel.provideFactory(repository))
            .get(ItemsViewModel::class.java)

        setContent {
            FetchRewardsTakeHomeTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FetchRewardsItemScreen(viewModel)
                }
            }
        }
    }
}