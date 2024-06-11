package sa.halalah.hala_now_library.authentication.models.states

sealed class IntentViewState {
    data object Loading : IntentViewState()

    data object Done: IntentViewState()

    data class Error(val message: String) : IntentViewState()

}