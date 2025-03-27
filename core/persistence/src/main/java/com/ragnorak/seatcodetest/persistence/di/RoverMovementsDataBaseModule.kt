package com.ragnorak.seatcodetest.persistence.di

import android.content.Context
import androidx.room.Room
import com.ragnorak.seatcodetest.persistence.dao.RoverMovementsDao
import com.ragnorak.seatcodetest.persistence.ddbb.RoverMovementsDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoverMovementsDataBaseModule {

    @Provides
    @Singleton
    fun provideRoverMovementsDao(
        @ApplicationContext appContext: Context
    ): RoverMovementsDao {
        return Room.databaseBuilder(
            appContext,
            RoverMovementsDataBase::class.java,
            "rover_movements_db"
        ).build().roverMovementsDao()
    }

}