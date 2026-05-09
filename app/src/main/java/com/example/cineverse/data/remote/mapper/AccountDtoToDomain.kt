package com.example.cineverse.data.remote.mapper

import com.example.cineverse.data.remote.dto.account.AccountDetailsDto
import com.example.cineverse.data.remote.dto.account.AvatarDto
import com.example.cineverse.data.remote.dto.account.GravatarDto
import com.example.cineverse.data.remote.dto.account.TmdbDto
import com.example.cineverse.domain.model.AccountDetails
import com.example.cineverse.domain.model.Avatar
import com.example.cineverse.domain.model.Gravatar
import com.example.cineverse.domain.model.Tmdb


fun GravatarDto.toDomain() = Gravatar(
    hash = this.hash
)

fun TmdbDto.toDomain(): Tmdb {
    return Tmdb(
        avatarPath = this.avatarPath
    )
}

fun AvatarDto.toDomain(): Avatar {
    val gravatar = this.gravatar.toDomain()
    val tmdb = this.tmdb.toDomain()
    return Avatar(
        gravatar = gravatar,
        tmdb = tmdb
    )
}

fun AccountDetailsDto.toDomain(): AccountDetails {
    val avatar = this.avatar.toDomain()

    return AccountDetails(
        avatar = avatar,
        id = this.id,
        includeAdult = this.includeAdult,
        iso31661 = this.iso31661,
        iso6391 = this.iso6391,
        name = this.name,
        username = this.username
    )
}