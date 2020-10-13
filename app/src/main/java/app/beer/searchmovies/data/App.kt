package app.beer.searchmovies.data

import android.app.Application
import app.beer.searchmovies.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://api.themoviedb.org/3/"
const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w342"
const val API_KEY = "f499b371270f3597d3343079c3b37e58"
const val LANGUAGE = "ru-RU"
const val DATE_FORMAT = ""

class App : Application() {

    private lateinit var retrofit: Retrofit
    private lateinit var movieService: MovieService

    override fun onCreate() {
        super.onCreate()
    }

    fun getRetrofit(): Retrofit {
        return retrofit
    }

    fun getMovieService(): MovieService {
        val requestInterceptor = Interceptor { chain ->
            val url = chain.request()
                .url()
                .newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .addQueryParameter("language", LANGUAGE)
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor chain.proceed(request)
        }

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.HEADERS

        val okHttpClient = OkHttpClient.Builder()
            .callTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(requestInterceptor)
            .addInterceptor(interceptor)
            .build()

        val gson = GsonBuilder()
            .setDateFormat(DATE_FORMAT)
            .serializeNulls()
            .create()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return MovieService.create(retrofit)
    }

}