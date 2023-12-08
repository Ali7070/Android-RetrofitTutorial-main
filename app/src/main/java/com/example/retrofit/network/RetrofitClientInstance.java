package com.example.retrofit.network;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://movies-api14.p.rapidapi.com/";
    private static final String API_KEY = "451edb30b9mshe6d6851ecd8a749p170bdfjsnf3e74bbe66c0"; // Replace with your actual API key

    // Singleton pattern
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(chain -> {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("X-RapidAPI-Key", API_KEY) // Add your API key header
                        // Add any other headers or parameters here
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            });

            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpClient.build()) // Set the OkHttpClient with interceptor
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
