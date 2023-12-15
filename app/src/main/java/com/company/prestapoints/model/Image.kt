package com.company.prestapoints.model

import java.sql.Blob

data class Image(
    var id: Int = 0,
    var data: Blob? = null,
    var prestation: Prestation? = null,
    var url: String? = null ,
    private var imageUrl: String? = null
)
