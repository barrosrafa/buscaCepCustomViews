package br.com.barros.aco.data

import br.com.barros.aco.api.Api
import br.com.barros.aco.api.singleCallable
import br.com.barros.aco.model.PostalCode
import com.google.gson.GsonBuilder
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RemoteDataSource: DataSource {

    private lateinit var retrofit: Retrofit

    fun RetrofitConfig() {
        val gson = GsonBuilder().setLenient().create()

        this.retrofit = Retrofit.Builder()
            .baseUrl("https://viacep.com.br/ws/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    override fun getPostalCode(cep: String): Single<PostalCode?> {
        RetrofitConfig()
        return singleCallable {
            this.retrofit.create(Api::class.java).buscarCEP(cep).execute().body()
        }
    }
}