package com.example.fcmilan.models

import com.google.gson.annotations.SerializedName

data class Players(
    @SerializedName("id"          ) var id          : Int?     = null,
    @SerializedName("name"        ) var name        : String?  = null,
    @SerializedName("firstname"   ) var firstname   : String?  = null,
    @SerializedName("lastname"    ) var lastname    : String?  = null,
    @SerializedName("age"         ) var age         : Int?     = null,
    @SerializedName("birth"       ) var birth       : Birth?   = Birth(),
    @SerializedName("nationality" ) var nationality : String?  = null,
    @SerializedName("height"      ) var height      : String?  = null,
    @SerializedName("weight"      ) var weight      : String?  = null,
    @SerializedName("injured"     ) var injured     : Boolean? = null,
    @SerializedName("photo"       ) var photo       : String?  = null
)
