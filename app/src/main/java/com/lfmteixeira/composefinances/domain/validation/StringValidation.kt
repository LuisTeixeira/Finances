package com.lfmteixeira.composefinances.domain.validation

fun String.isPresent(fieldName: String): String {
    if (this.isNullOrBlank()) throw IllegalArgumentException("Field $fieldName must not be empty")
    return this
}