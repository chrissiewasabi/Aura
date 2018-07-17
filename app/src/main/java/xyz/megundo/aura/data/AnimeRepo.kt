package xyz.megundo.aura.data

import io.reactivex.Single
import xyz.megundo.aura.network.GistApiService

class AnimeRepo(val apiService: GistApiService){
    fun getAnimeList():Single<ApiResults>{
        return apiService.getanimeData();
    }
}
