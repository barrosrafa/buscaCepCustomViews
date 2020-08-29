package br.com.barros.aco.data

import br.com.barros.aco.model.PostalCode
import io.reactivex.Single

interface DataSource {
    fun getPostalCode(cep: String): Single<PostalCode?>
}