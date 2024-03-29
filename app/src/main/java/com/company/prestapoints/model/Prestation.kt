package com.company.prestapoints.model


data class Prestation(
    val id: Int? = null,
    val title: String = "",
    val duration: Long? = null,
    val addPoint: Int? = null,
    val dateStart: String? = null,
    val dateEnd: String? = null,
    val state: String? = null,
    val description: String? = null,
    val maxUser: Int? = null,
    val images: List<Image> = emptyList(),
    // val type: Type? = null,
    val category: Category? = null,
    // val location: Location? = null,
    val placeAvailable: Int? = null,
    val littleDescription: String? = null,
    val videoLink: String? = null,
    val practicalInformation: String? = null,
    val language: String? = null,
    val personalInfos: String? = null,
    // val registrations: List<Registration> = emptyList()
)
