package com.example.vibepractice.curiosity

data class CuriosityUiState(
    val items: List<CuriosityItem> = emptyList(),
    val quickCaptureTitle: String = "",
    val quickCaptureSnippet: String = "",
    val quickCaptureSource: String = "",
    val isExpandedInput: Boolean = false,
    val selectedFilter: CuriosityStatus? = null // null means show ALL items
)
