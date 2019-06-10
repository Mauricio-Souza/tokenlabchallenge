package msousa.dev.tokenlab_challenge.data.model

import com.google.gson.annotations.SerializedName

data class ProductionCompany (
    val id: Int,
    @SerializedName("logoUrl")
    val logoUrl: String,
    val name: String,
    @SerializedName("original_country")
    val originalCountry: String
)