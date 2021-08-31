package com.eddiej.apisearch.feature.book

import com.eddiej.apisearch.model.data.Book

sealed class BookUiModel {
    class BookModel(val book: Book) : BookUiModel()

    class BookHeaderModel : BookUiModel()

    class BookFooterModel : BookUiModel()
}
