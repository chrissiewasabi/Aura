package xyz.megundo.aura.network

import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import xyz.megundo.aura.data.ApiResults


interface GistApiService {

    @GET("anime.json")
    fun getanimeData(): Single<ApiResults>

    companion object RetrofitProvider {
        val baseUrl="https://gist.githubusercontent.com/aws1994/f583d54e5af8e56173492d3f60dd5ebf/raw/c7796ba51d5a0d37fc756cf0fd14e54434c547bc/"

        fun provideRetrofit(): GistApiService {

        /* using this Okhttp logging service for purposes of debugging , not intended for production */
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient= OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()

            val retrofit = Retrofit.Builder()
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .build()

            return retrofit.create(GistApiService::class.java);
        }
}