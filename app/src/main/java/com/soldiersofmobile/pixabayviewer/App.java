package com.soldiersofmobile.pixabayviewer;

import android.app.Application;

import com.soldiersofmobile.pixabayviewer.api.PixabayApi;
import com.soldiersofmobile.pixabayviewer.db.DbHelper;
import com.soldiersofmobile.pixabayviewer.db.FavouritesDao;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class App extends Application {

    private PixabayApi pixabayApi;
    private FavouritesDao favouritesDao;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .build();


        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(client);
        builder.baseUrl("https://pixabay.com");
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        pixabayApi = retrofit.create(PixabayApi.class);
        favouritesDao = new FavouritesDao(new DbHelper(this));
    }

    public PixabayApi getPixabayApi() {
        return pixabayApi;
    }

    public FavouritesDao getFavouritesDao() {
        return favouritesDao;
    }
}
