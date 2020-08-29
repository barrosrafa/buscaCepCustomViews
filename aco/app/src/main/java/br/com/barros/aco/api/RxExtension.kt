package br.com.barros.aco.api

import io.reactivex.Single

inline fun <T> singleCallable(crossinline func: () -> T): Single<T> {
    return Single.fromCallable { func.invoke() }.defaultSchedulers()
}