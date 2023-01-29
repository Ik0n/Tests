package com.geekbrains.tests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.geekbrains.tests.presenter.RepositoryContract

class ViewModelFactory constructor(
    private val repositoryContract: RepositoryContract,
    private val appSchedulerProvider: SchedulerProvider
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        SearchViewModel(repositoryContract, appSchedulerProvider) as T

}