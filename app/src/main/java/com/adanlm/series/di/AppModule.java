package com.adanlm.series.di;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

/**
 * Aqui declaramos las dependencias que no cambiaran durante la ejecucion de la aplicacion.
 * Por ejemplo, el contexto de la aplicaciones, instacias de retrofit, room, glide, etc.
 */
@Module
public abstract class AppModule {

    @Binds
    abstract Context bindContext(Application application);
}
