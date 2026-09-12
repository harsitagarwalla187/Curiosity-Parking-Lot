package com.example.vibepractice.curiosity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CuriosityViewModel(
    private val repository: CuriosityRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CuriosityUiState())
    val uiState: StateFlow<CuriosityUiState> = _uiState.asStateFlow()

    private val _selectedFilter = MutableStateFlow<CuriosityStatus?>(null)

    init {
        observeItems()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun observeItems() {
        _selectedFilter
            .flatMapLatest { status ->
                if (status == null) {
                    repository.getAllItems()
                } else {
                    repository.getItemsByStatus(status)
                }
            }
            .onEach { items ->
                _uiState.update { it.copy(items = items) }
            }
            .launchIn(viewModelScope)
    }

    fun onTitleChange(newTitle: String) {
        _uiState.update { it.copy(quickCaptureTitle = newTitle) }
    }

    fun onSnippetChange(newSnippet: String) {
        _uiState.update { it.copy(quickCaptureSnippet = newSnippet) }
    }

    fun onSourceChange(newSource: String) {
        _uiState.update { it.copy(quickCaptureSource = newSource) }
    }

    fun toggleExpandedInput() {
        _uiState.update { it.copy(isExpandedInput = !it.isExpandedInput) }
    }

    fun setFilter(status: CuriosityStatus?) {
        _selectedFilter.value = status
        _uiState.update { it.copy(selectedFilter = status) }
    }

    fun captureItem() {
        val title = _uiState.value.quickCaptureTitle.trim()
        if (title.isBlank()) return

        val snippet = _uiState.value.quickCaptureSnippet.trim().ifEmpty { null }
        val source = _uiState.value.quickCaptureSource.trim().ifEmpty { null }

        viewModelScope.launch {
            repository.insertItem(
                CuriosityItem(
                    title = title,
                    contextSnippet = snippet,
                    source = source,
                    status = CuriosityStatus.PARKED
                )
            )
            _uiState.update {
                it.copy(
                    quickCaptureTitle = "",
                    quickCaptureSnippet = "",
                    quickCaptureSource = "",
                    isExpandedInput = false
                )
            }
        }
    }

    fun updateStatus(item: CuriosityItem, newStatus: CuriosityStatus) {
        viewModelScope.launch {
            repository.updateItem(
                item.copy(
                    status = newStatus,
                    updatedAt = System.currentTimeMillis()
                )
            )
        }
    }

    fun deleteItem(item: CuriosityItem) {
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }

    class Factory(private val repository: CuriosityRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CuriosityViewModel(repository) as T
        }
    }
}
