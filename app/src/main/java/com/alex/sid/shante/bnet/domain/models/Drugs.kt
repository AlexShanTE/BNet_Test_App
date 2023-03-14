package com.alex.sid.shante.bnet.domain.models

data class Drugs(
    val categories: Categories,
    val description: String,
    val documentation: String,
    val fields: List<Field>?,
    val id: Int,
    val image: String?,
    val name: String
)

data class Categories(
    val icon: String,
    val id: Int,
    val image: String,
    val name: String
)

data class Field(
    val flags: Flags,
    val group: Int,
    val image: String?,
    val name: String,
    val show: Int,
    val type: String,
    val types_id: Int,
    val value: String
)

data class Flags(
    val html: Int,
    val no_image: Int,
    val no_name: Int,
    val no_value: Int,
    val no_wrap: Int,
    val no_wrap_name: Int,
    val system: Int
)