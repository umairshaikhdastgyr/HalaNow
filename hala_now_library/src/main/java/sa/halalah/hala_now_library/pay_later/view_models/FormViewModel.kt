package sa.halalah.hala_now_library.pay_later.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import sa.halalah.hala_now_library.pay_later.repository.PayLaterRepository

class FormViewModel : ViewModel() {


    private val _tweets = MutableStateFlow<List<String>>(emptyList())
    val tweets: StateFlow<List<String>>
        get() = _tweets

    private val repository = PayLaterRepository()
    init {
        viewModelScope.launch {
            repository.getCategories()
        }
    }
}