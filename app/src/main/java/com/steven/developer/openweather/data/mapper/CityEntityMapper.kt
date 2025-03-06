package com.steven.developer.openweather.data.mapper

import com.steven.developer.openweather.data.local.weather.entity.CityEntity
import com.steven.developer.openweather.domain.mapper.IEntityMapper
import com.steven.developer.openweather.model.City
import com.steven.developer.openweather.model.CoordinateModel
import javax.inject.Inject

class CityEntityMapper @Inject constructor() : IEntityMapper<CityEntity, City> {
    override fun mapFromEntity(entity: CityEntity): City {
        return City(
            entity.country,
            entity.timezone,
            entity.sunrise,
            entity.sunset,
            entity.cityName,
            CoordinateModel(
                entity.latitude,
                entity.longitude
            )
        )
    }

    override fun entityFromModel(model: City): CityEntity {
        return CityEntity(
            id = 1,
            country = model.country,
            timezone = model.timezone,
            sunrise = model.sunrise,
            sunset = model.sunset,
            cityName = model.cityName,
            latitude = model.coordinate.latitude,
            longitude = model.coordinate.longitude
        )
    }
}