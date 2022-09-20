package net.sherafatpour.internetconnectivitystatus.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.sherafatpour.internetconnectivitystatus.di.qualifiers.QualifierAvailable
import net.sherafatpour.internetconnectivitystatus.di.qualifiers.QualifierLosing
import net.sherafatpour.internetconnectivitystatus.di.qualifiers.QualifierLost
import net.sherafatpour.internetconnectivitystatus.di.qualifiers.QualifierUnAvailable
import net.sherafatpour.internetconnectivitystatus.util.connection.ConnectivityObserver
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleConnect {
    //Statuses
    @Provides
    @Singleton
    //@Named("Available")
    @QualifierAvailable
    fun provideStatusAvailable() : ConnectivityObserver.Status =
        ConnectivityObserver.Status.Available

    @Provides
    @Singleton
    //@Named("Losing")
    @QualifierLosing
    fun provideStatusLosing() : ConnectivityObserver.Status = ConnectivityObserver.Status.Losing

    @Provides
    @Singleton
    //@Named("Lost")
    @QualifierLost
    fun provideStatusLost() : ConnectivityObserver.Status = ConnectivityObserver.Status.Lost

    @Provides
    @Singleton
    //@Named("Unavailable")
    @QualifierUnAvailable
    fun provideStatusUnavailable() : ConnectivityObserver.Status =
        ConnectivityObserver.Status.Unavailable
}