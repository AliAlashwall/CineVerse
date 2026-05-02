package com.example.cineverse.domain.model

data class Cast(
    val adult: Boolean,
    val castId: Int,
    val character: String,
    val creditId: String,
    val gender: Int,
    val id: Int,
    val knownForDepartment: String,
    val name: String,
    val order: Int,
    val originalName: String,
    val popularity: Double,
    val profilePath: String?
)

val mockCastList =
    listOf(
        Cast(
            adult = false,
            gender = 2,
            id = 819,
            knownForDepartment = "Acting",
            name = "Edward Norton",
            originalName = "Edward Norton",
            popularity = 26.99,
            profilePath = "/8nytsqL59SFJTVYVrN72k6qkGgJ.jpg",
            castId = 4,
            character = "The Narrator",
            creditId = "52fe4250c3a36847f80149f3",
            order = 0
        ),
        Cast(
            adult = false,
            gender = 1,
            id = 1283,
            knownForDepartment = " Acting ",
            name = "Helena Bonham Carter ",
            originalName = "Helena Bonham Carter ",
            popularity = 22.112,
            profilePath = "/DDeITcCpnBd0CkAIRPhggy9bt5.jpg",
            castId = 285,
            character = "Marla Singer",
            creditId = "631f0de8bd32090082733691",
            order = 2
        ),
        Cast(
            adult = false,
            gender = 2,
            id = 7499,
            knownForDepartment = " Acting ",
            name = " Jared Leto",
            originalName = " Jared Leto",
            popularity = 18.969,
            profilePath = " / ca3x0OfIKbJppZh8S1Alx3GfUZO.jpg",
            castId = 286,
            character = " Angel Face",
            creditId = "631f0e29ce9e91007f757d86",
            order = 4
        ),
        Cast(
            adult = false,
            gender = 2,
            id = 7471,
            knownForDepartment = "Acting",
            name = "Zach Grenier",
            originalName = "Zach Grenier",
            popularity = 5.608,
            profilePath = "/fSyQKZO39sUsqY283GXiScOg3Hi.jpg",
            castId = 31,
            character = "Richard Chesler",
            creditId = "52fe4250c3a36847f8014a55",
            order = 5
        ),
        Cast(
            adult = false,
            gender = 2,
            id = 7497,
            knownForDepartment = "Acting",
            name = "Holt McCallany",
            originalName = "Holt McCallany",
            popularity = 14.635,
            profilePath = "/a5ax2ICLrV6l0T74OSFvzssCQyQ.jpg",
            castId = 32,
            character = "The Mechanic",
            creditId = "52fe4250c3a36847f8014a59",
            order = 6
        )
    )