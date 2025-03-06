package com.steven.developer.openweather.data.mapper

import com.steven.developer.openweather.data.remote.entity.CityDto
import com.steven.developer.openweather.data.remote.entity.CoordDto
import com.steven.developer.openweather.domain.mapper.IEntityMapper
import com.steven.developer.openweather.model.City
import com.steven.developer.openweather.model.CoordinateModel
import javax.inject.Inject

class CityDtoMapper @Inject constructor() : IEntityMapper<CityDto, City> {
    override fun mapFromEntity(entity: CityDto): City {
        return City(
            entity.country,
            entity.timezone,
            entity.sunrise,
            entity.sunset,
            entity.cityName,
            CoordinateModel(
                entity.coordinate.latitude,
                entity.coordinate.longitude
            )
        )
    }

    override fun entityFromModel(model: City): CityDto {
        return CityDto(
            model.country,
            model.timezone,
            model.sunrise,
            model.sunset,
            model.cityName,
            CoordDto(
                model.coordinate.latitude,
                model.coordinate.longitude
            )
        )
    }

}