package com.adanlm.series.di;

import com.adanlm.series.ui.detailshow.DetailShowActivity;
import com.adanlm.series.ui.detailshow.DetailShowModule;
import com.adanlm.series.ui.main.MainActivity;
import com.adanlm.series.ui.main.MainModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Aqui hacemos que Dagger.Android nos cree un subcomponente de los modulos escritos aqui {AcitivtyBindingModule},
 * estos tendran un componente padre {AppComponent}
 * para no hacerlos manualmente nosotros
 */
@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity mainActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = DetailShowModule.class)
    abstract DetailShowActivity detailShowActivity();
}
