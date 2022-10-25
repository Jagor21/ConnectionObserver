package com.sgvdev.connectionobserver

import kotlinx.coroutines.flow.Flow

interface ConnectionObserver {

    fun observe() : Flow<Status>

    enum class Status {
        AVAILABLE, UNAVAILABLE, LOSING, LOST
    }
}