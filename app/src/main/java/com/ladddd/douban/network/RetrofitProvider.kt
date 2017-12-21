package com.ladddd.douban.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import com.ladddd.douban.model.api.DoubanMovieApi
import com.ladddd.douban.model.api.HttpAddressConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by 陈伟达 on 2017/12/6.
 */
object RetrofitProvider {

    private var gson : Gson
    private lateinit var retrofit : Retrofit

    init {
        gson = GsonBuilder()
                .registerTypeAdapter(String::class.java, NoNullStringAdapter())
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create()
    }

    class NoNullStringAdapter : TypeAdapter<String>() {
        override fun write(out: JsonWriter?, value: String?) {
            out?.value(value?:"")
        }

        override fun read(reader: JsonReader?): String {
            return if (JsonToken.NULL == reader?.peek()) {
                reader.nextNull()
                return ""
            } else reader?.nextString() ?: ""
        }
    }

    private fun getRetrofit(baseUrl : String) : Retrofit {
        if (!RetrofitProvider::retrofit.isInitialized) {
            retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(OkHttpProvider.okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
        }

        return retrofit
    }

    fun getDoubanService() : DoubanMovieApi {
        return getRetrofit(HttpAddressConfig.DOUBAN_MOVIE_BASE_URL).create(DoubanMovieApi::class.java)
    }
}