package com.android.fetchandroidloganp.ui.screens.FetchRewardsItemScreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.android.fetchandroidloganp.model.CollapsableItem
import com.android.fetchandroidloganp.ui.components.FetchRewardsItemList
import com.android.fetchandroidloganp.ui.theme.FetchBlack
import com.android.fetchandroidloganp.ui.theme.FetchGray
import com.android.fetchandroidloganp.ui.theme.FetchPurple
import com.android.fetchandroidloganp.api.APIResult

@Composable
fun FetchRewardsItemScreen(
    itemsViewModel: ItemsViewModel
) {

    val context = LocalContext.current

    var isLoading by remember { mutableStateOf(false) }

    var collapsableItemList by remember {
        mutableStateOf(emptyList<CollapsableItem>())
    }

    val listItems by itemsViewModel.collapsableItems.collectAsState()

    when (listItems) {
        is APIResult.Loading -> {
            isLoading = true
        }

        is APIResult.Success -> {
            isLoading = false
            collapsableItemList = listItems.data!!
        }

        is APIResult.Error -> {
            isLoading = false
            Toast.makeText(context, listItems.message.toString(), Toast.LENGTH_LONG)
                .show()
        }

        else -> {}
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = FetchPurple,
                    trackColor = FetchGray,
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(FetchBlack)
            ) {
                if (collapsableItemList.isNotEmpty()) {
                    FetchRewardsItemList(
                        collapsableItems = collapsableItemList,
                    )
                }
            }
        }
    }
}
