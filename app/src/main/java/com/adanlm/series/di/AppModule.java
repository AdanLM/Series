package com.adanlm.series.di;

import android.app.Application;
import android.content.Context;

import com.adanlm.series.BuildConfig;
import com.adanlm.series.R;
import com.adanlm.series.data.ShowsRepository;
import com.adanlm.series.data.remote.EndPoints;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Aqui declaramos las dependencias que no cambiaran durante la ejecucion de la aplicacion.
 * Por ejemplo, el contexto de la aplicaciones, instacias de retrofit, room, glide, etc.
 */
@Module
public abstract class AppModule {

    @Binds
    abstract Context bindContext(Application application);

    @Singleton
    @Provides
    static Retrofit provideRetrofitService() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    @Provides
    static EndPoints provideEndpoints(Retrofit retrofit) {
        return retrofit.create(EndPoints.class);
    }

    @Provides
    static ShowsRepository provideRepository(EndPoints endPoints) {
        return new ShowsRepository(endPoints);
    }

    @Singleton
    @Provides
    static RequestOptions provideRequestOptions() {
        return RequestOptions
                .placeholderOf(R.drawable.preview_img)
                .error(R.drawable.preview_img);
    }

    @Singleton
    @Provides
    static RequestManager provideGlideInstance(Application application, RequestOptions requestOptions) {
        return Glide.with(application)
                .setDefaultRequestOptions(requestOptions);
    }
}
