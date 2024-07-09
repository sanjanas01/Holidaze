package com.exampe.travel
import java.io.Serializable

data class Journey(
    val location: String? = null,
    val startDate: String? = null,
    val endDate: String? = null
) : Serializable

