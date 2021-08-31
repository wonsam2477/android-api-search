package com.eddiej.apisearch.global

enum class LoadingState {
    LOADING {
        override fun toString(): String = "loading"
    },
    COMPLETED {
        override fun toString(): String = "completed"
    },
    ERROR {
        override fun toString(): String = "error"
    }
}