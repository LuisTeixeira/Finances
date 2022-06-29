package com.lfmteixeira.composefinances.domain.exception

class ValidationException(
    override val message: String,
    private val detailsMap: MutableMap<String, String> = HashMap()
) : Exception(message) {

    fun addDetail(property: String, error: String) {
        detailsMap[property] = error
    }

    fun get(property: String): String? {
        return detailsMap[property]
    }

}