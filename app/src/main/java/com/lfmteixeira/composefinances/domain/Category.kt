package com.lfmteixeira.composefinances.domain

import com.lfmteixeira.composefinances.util.randomUUID

class Category(
    val uuid: String = randomUUID(),
    val name: String
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Category

        return other.uuid == uuid && other.name == name
    }
}