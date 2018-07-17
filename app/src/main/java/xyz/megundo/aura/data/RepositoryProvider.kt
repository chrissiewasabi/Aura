package xyz.megundo.aura.data

import xyz.megundo.aura.network.GistApiService


object RepositoryProvider {
    fun provideAnimeRepository():AnimeRepo{
        return AnimeRepo(GistApiService.RetrofitProvider.provideRetrofit())
    }
}