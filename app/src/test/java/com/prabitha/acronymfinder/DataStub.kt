package com.prabitha.acronymfinder

import com.prabitha.acronymfinder.models.Acronyms
import com.prabitha.acronymfinder.models.LongForms
import com.prabitha.acronymfinder.models.Vars

fun getAcronymResponse(): Acronyms {
    return Acronyms(
        sf = "abc", listOf(
            LongForms(
                lf = "Adsdsd",
                freq = 3,
                since = 2343,
                vars = listOf(
                    Vars(
                        lf = "Adsdsd",
                        freq = 3,
                        since = 2343
                    )
                )
            )
        )
    )
}