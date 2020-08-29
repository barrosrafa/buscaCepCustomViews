package br.com.barros.aco.base

import android.content.Context
import br.com.barros.aco.data.RemoteDataSource
import br.com.barros.aco.data.Repository

object Injection {

    fun provideRepository(context: Context): Repository {
        return Repository.getInstance(RemoteDataSource)
    }
}