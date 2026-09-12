package com.example.vibepractice.ui

import com.example.vibepractice.data.model.CuriosityItem
import com.example.vibepractice.data.model.CuriosityStatus

data class CuriosityUiState(
    val items: List<CuriosityItem> = emptyList(),
    val quickCaptureTitle: String = "",
    val quickCaptureSnippet: String = "",
    val quickCaptureSource: String = "",
    val isExpandedInput: Boolean = false,
    val selectedFilter: CuriosityStatus? = null // null means show ALL items
)
