package com.prabitha.acronymfinder.data

import com.prabitha.acronymfinder.api.AcronymService
import com.prabitha.acronymfinder.models.Acronyms
import retrofit2.Response
import javax.inject.Inject

class AcronymRepository @Inject constructor(
    private val acronymService: AcronymService
) {

    /*
  *  method to fetch the acronyms from the network
  * */
    suspend fun getAcronyms(userQuery: String): Response<List<Acronyms>> {
        return acronymService.getAcronyms(userQuery)
    }
}