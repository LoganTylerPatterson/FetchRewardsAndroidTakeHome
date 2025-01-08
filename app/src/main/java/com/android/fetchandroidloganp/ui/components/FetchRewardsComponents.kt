package com.android.fetchandroidloganp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.fetchandroidloganp.model.CollapsableItem
import com.android.fetchandroidloganp.model.Item
import com.android.fetchandroidloganp.ui.theme.FetchGray
import com.android.fetchandroidloganp.ui.theme.FetchOrange
import com.android.fetchandroidloganp.ui.theme.FetchPurple

@Composable
fun SubItem(
    item: Item,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = FetchGray
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = "Id: ${item.id}",
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = FetchOrange
            )
            Text(
                text = "Name: ${item.name ?: "Unknown"}",
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = FetchOrange
            )
        }
    }
}

@Composable
fun CollapsableItemHeader(
    text: String,
    isExpanded: Boolean = false,
    onCollapseIconClicked: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable { onCollapseIconClicked() },
        colors = CardDefaults.cardColors(
            containerColor = FetchPurple,
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = text,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                color = Color.White
            )

            Icon(
                imageVector = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = "Collapse Icon",
                tint = FetchOrange
            )
        }
    }
}

@Composable
fun FetchRewardsItemList(
    collapsableItems: List<CollapsableItem>,
    modifier: Modifier = Modifier
) {
    val isExpandedList = remember {
        mutableStateListOf<Boolean>().apply {
            addAll(List(collapsableItems.size) { false })
        }
    }

    LazyColumn(modifier = modifier.fillMaxWidth()) {
        items(collapsableItems.size) { index -> // Use items() with index
            val collapsableItem = collapsableItems[index]
            val isExpanded = isExpandedList[index]

            CollapsableItemHeader(
                text = "List ${collapsableItem.listId}",
                isExpanded = isExpanded,
                onCollapseIconClicked = {
                    isExpandedList[index] = !isExpanded
                }
            )

            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically(
                    animationSpec = tween(durationMillis = 1000)
                ),
                exit = shrinkVertically(
                    animationSpec = tween(durationMillis = 1000)
                )
            ) {
                Column {
                    collapsableItem.itemsList.forEach { item ->
                        SubItem(item = item)
                    }
                }
            }
        }
    }
}
