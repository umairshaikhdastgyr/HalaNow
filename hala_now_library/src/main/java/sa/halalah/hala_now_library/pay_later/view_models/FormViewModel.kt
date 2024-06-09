package sa.halalah.hala_now_library.pay_later.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import sa.halalah.hala_now_library.pay_later.repository.PayLaterRepository
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(private val repository: PayLaterRepository) : ViewModel() {

    init {
        viewModelScope.launch {
            repository.getCategories()
        }
    }
}