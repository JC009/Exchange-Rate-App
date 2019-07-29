package com.example.kursy.di.annotations

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class SchedulerType(val type: Type)

enum class Type{
    MAIN_THREAD, IO
}
