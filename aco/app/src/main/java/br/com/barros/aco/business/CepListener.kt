package br.com.barros.aco.business

import br.com.barros.aco.model.PostalCode

interface CepListener {

    fun searchPostalCode(cep: String)

    fun responsePostalCode(postalCode: PostalCode)

    fun errorPostalCode()
}
