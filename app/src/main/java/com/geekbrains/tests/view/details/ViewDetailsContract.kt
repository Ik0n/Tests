package com.geekbrains.tests.view.details

import com.geekbrains.tests.ViewContract

internal interface ViewDetailsContract : ViewContract {
    fun setCount(count: Int)
}