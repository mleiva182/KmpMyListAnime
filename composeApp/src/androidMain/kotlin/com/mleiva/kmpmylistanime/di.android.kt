package com.mleiva.kmpmylistanime

import com.mleiva.kmpmylistanime.data.database.getDatabaseBuilder
import org.koin.core.module.Module
import org.koin.dsl.module

actual val nativeModuiule = module {
    single { getDatabaseBuilder(get()) }
}