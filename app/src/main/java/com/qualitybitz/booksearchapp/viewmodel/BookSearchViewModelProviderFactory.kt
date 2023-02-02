package com.qualitybitz.booksearchapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.qualitybitz.booksearchapp.data.repository.BookSearchRepository

// BookSearchRepository를 초기값으로 전달받아 ViewModel을 반환하는 ViewModelProviderFactory
@Suppress("UNCHECKED_CAST")
class BookSearchViewModelProviderFactory(
    private val bookSearchRepository: BookSearchRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookSearchViewModel::class.java)) {
            return BookSearchViewModel(bookSearchRepository) as T
        }
        throw java.lang.IllegalArgumentException("ViewModel class not Found")
    }

}