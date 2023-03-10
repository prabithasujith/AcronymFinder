package com.prabitha.acronymfinder.models

data class Acronyms(
    val sf: String,
    val lfs: List<LongForms> = listOf()
)

data class LongForms(
    val lf: String,
    val freq: Int,
    val since: Int,
    val vars: List<Vars> = listOf()
)

data class Vars(
    val lf: String,
    val freq: Int,
    val since: Int
)