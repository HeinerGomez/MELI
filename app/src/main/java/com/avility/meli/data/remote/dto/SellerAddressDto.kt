package com.avility.meli.data.remote.dto

import com.avility.meli.domain.model.SellerBasicLocation

data class SellerAddressDto(
    val city: CityDto,
    val country: CountryDto,
    val id: Int
)

fun SellerAddressDto.toSellerBasicLocation(): SellerBasicLocation {
    return SellerBasicLocation(
        country.name,
        city.name
    )
}