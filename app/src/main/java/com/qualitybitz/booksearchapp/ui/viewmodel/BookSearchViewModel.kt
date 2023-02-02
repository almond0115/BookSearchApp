package com.qualitybitz.booksearchapp.ui.viewmodel

import androidx.lifecycle.*
import com.qualitybitz.booksearchapp.data.model.SearchResponse
import com.qualitybitz.booksearchapp.data.repository.BookSearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookSearchViewModel(
    // 초기값으로 BookSearchRepository을 전달받아야 하지만
    // viewModel은 그 자체로는 생성 시 초기값을 전달받을 수 없기 때문에 Factory를 만들어주어야 함
    private val bookSearchRepository: BookSearchRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    // Api
    private val _searchResult = MutableLiveData<SearchResponse>()
    val searchResult: LiveData<SearchResponse> get() = _searchResult

    // Repository에 searchBooks를 실행하되 Parameter는 query 이외에는 모두 고정값을 전달
    // Retrofit 서비스의 반환값은 MutableLiveData인 _searchResult에 저장
    // 외부에는 수정할 수 없는 LiveData로 변환한 searchResult를 노출하도록 함
    fun searchBooks(query: String) = viewModelScope.launch(Dispatchers.IO) {
        val response = bookSearchRepository.searchBooks(query, "accuracy", 1, 15)
        if (response.isSuccessful) {
            response.body()?.let { body ->
                _searchResult.postValue(body)
            }
        }
    }

    // SavedState
    // 쿼리 보존에 사용할 쿼리 변수를 정의
    var query = String()
        set(value) { // 쿼리의 값이 변화하면 그값을 바로 반영 -> savedState 저장
            field = value
            savedStateHandle.set(SAVE_STATE_KEY, value)
        }

    // view Model 초기화할 때 쿼리 초기값을 saveState에서 가져오도록 함
    init {
        query = savedStateHandle.get<String>(SAVE_STATE_KEY) ?: ""
    }

    // 저장 및 로드에 사용할 SAVE_STATE_KEY 정의
    companion object {
        private const val SAVE_STATE_KEY = "query"
    }
}