package br.com.barros.aco.business

import br.com.barros.aco.data.Repository
import br.com.barros.aco.model.PostalCode

class CepBusiness(
    private val repository: Repository,
    private val businessListener: CepListener
) {

    fun callApiPostalCode(cep: String) {
        repository.getPostalCode(cep).subscribe(
            {
                it?.let { it1 -> onResponse(it1) }
        },
            {onError()})
    }

    private fun onResponse(response: PostalCode) {
        businessListener.responsePostalCode(response)
    }

    private fun onError() {
        businessListener.errorPostalCode()
    }
}
