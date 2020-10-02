package com.thanh.mvp_architecture.model

class ResponseModel<T> {
    var exitcode: Int? = null
    var data: T? = null
        private set

    fun setData(data: T) {
        this.data = data
    }
}