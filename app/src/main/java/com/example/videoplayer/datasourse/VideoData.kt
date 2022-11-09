package com.example.example

import com.google.gson.annotations.SerializedName


data class VideoData (

  @SerializedName("VideoId"         ) var VideoId         : Int?    = null,
  @SerializedName("VideoIdentifier" ) var VideoIdentifier : String? = null,
  @SerializedName("OrderNumber"     ) var OrderNumber     : Int?    = null

)