package chronosirius.opendrop

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class File(
    val name: String,
    val size: Long
)

data class FileScreenState(
    val isLoading: Boolean = false,
    val files: List<File> = emptyList()
)

class MainViewModel: ViewModel() {
    private val _state = mutableStateOf(FileScreenState())
    val state: State<FileScreenState> = _state

    init {
        loadStuff()
    }

    fun loadStuff() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            delay(3000L)
            _state.value = _state.value.copy(
                isLoading = false,
                files = _state.value.files.toMutableList().also { it.add(File("test", 1024)) }
            )
        }
    }
}