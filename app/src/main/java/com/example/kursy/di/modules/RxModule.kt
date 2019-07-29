package com.example.kursy.di.modules

import com.example.kursy.di.annotations.SchedulerType
import com.example.kursy.di.annotations.Type
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class RxModule {

    @Provides
    @SchedulerType(Type.MAIN_THREAD)
    fun providesMainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @SchedulerType(Type.IO)
    fun providesIoScheduler(): Scheduler = Schedulers.io()
}