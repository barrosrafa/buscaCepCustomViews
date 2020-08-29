package br.com.barros.aco.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.barros.aco.business.CepBusiness
import br.com.barros.aco.business.CepListener
import br.com.barros.aco.data.Repository
import br.com.barros.aco.model.PostalCode
import kotlin.coroutines.coroutineContext

class CepViewModel(private val repository: Repository,
                   application: Application): AndroidViewModel(application), CepListener {

    private var business: CepBusiness = CepBusiness(repository, this)

    private val _postalCode = MutableLiveData<PostalCode>()
    val getPostalCode: LiveData<PostalCode>
    get() = _postalCode
    private fun setPostalCode(postalCode: PostalCode) {
        _postalCode.postValue(postalCode)
    }

    private val _error = MutableLiveData<String>()
    val getError: LiveData<String>
        get() = _error
    private fun setError(error: String) {
        _error.postValue(error)
    }

    override fun searchPostalCode(postalCode: String) {
        business.callApiPostalCode(postalCode)
    }

    override fun responsePostalCode(postalCode: PostalCode) {
        if(postalCode.cep != null && !postalCode.cep.isEmpty()) {
            setPostalCode(postalCode)
        } else {
            setError("CEP não encontrado!")
        }
    }

    override fun errorPostalCode() {
        setError("Ocorreu um problema ao consultar o CEP!")
    }
}