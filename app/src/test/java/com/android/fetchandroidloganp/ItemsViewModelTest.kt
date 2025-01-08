import com.android.fetchandroidloganp.api.APIResult
import com.android.fetchandroidloganp.fake.FakeItemRepository
import com.android.fetchandroidloganp.model.CollapsableItem
import com.android.fetchandroidloganp.model.Item
import com.android.fetchandroidloganp.rules.TestDispatcherRule
import com.android.fetchandroidloganp.ui.screens.FetchRewardsItemScreen.ItemsViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ItemsViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private lateinit var viewModel: ItemsViewModel
    private lateinit var repository: FakeItemRepository

    @Before
    fun setup() {
        repository = FakeItemRepository()
        viewModel = ItemsViewModel(repository)
    }

    @Test
    fun itemsViewModel_getAllItems_verifySuccess(): Unit = runTest {
        val repository = FakeItemRepository()
        viewModel = ItemsViewModel(repository)

        val expectedItems = listOf(
            CollapsableItem(
                listId = 1,
                itemsList = listOf(
                    Item(id = 1, listId = 1, name = "114")
                )
            ),
            CollapsableItem(
                listId = 2,
                itemsList = listOf(
                    Item(id = 2, listId = 2, name = "115")
                )
            )
        )
        assertEquals(expectedItems, viewModel.collapsableItems.value.data)
    }
}