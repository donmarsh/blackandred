package com.example.fcmilan.models.fixtures

import com.example.fcmilan.models.fixtures.Extratime
import com.example.fcmilan.models.fixtures.Fulltime
import com.example.fcmilan.models.fixtures.Halftime
import com.example.fcmilan.models.fixtures.Penalty
import com.google.gson.annotations.SerializedName


data class Score (

    @SerializedName("halftime"  ) var halftime  : Halftime?  = Halftime(),
    @SerializedName("fulltime"  ) var fulltime  : Fulltime?  = Fulltime(),
    @SerializedName("extratime" ) var extratime : Extratime? = Extratime(),
    @SerializedName("penalty"   ) var penalty   : Penalty?   = Penalty()

)