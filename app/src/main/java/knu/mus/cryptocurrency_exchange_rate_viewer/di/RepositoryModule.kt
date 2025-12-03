package knu.mus.cryptocurrency_exchange_rate_viewer.di

import knu.mus.cryptocurrency_exchange_rate_viewer.data.RepositoryDataBase
import knu.mus.cryptocurrency_exchange_rate_viewer.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun provideRepository(repository: RepositoryDataBase): Repository
}

