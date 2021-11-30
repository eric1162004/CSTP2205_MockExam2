package com.example.mock1exam.data

import com.example.assignment4.utils.map.Coordinate
import com.example.mock1exam.data.entities.BananaItem
import com.example.mock1exam.data.entities.BananaLocation

data class CatItem(
    var name: String,
    var imageUrl: String,
    var breed: String,
    var age: Int,
    var gender: String,
    var fact: String,
)

val FakeCatItem = CatItem(
    name = "Bubble",
    imageUrl = "https://blog.healthypawspetinsurance.com/wp-content/uploads/2018/06/GettyImages-160524191.jpg",
    breed = "Persian Cat",
    age = 2,
    gender = "girl",
    fact = "The Persian cat is a long-haired breed of cat characterized by its round face and short muzzle. It is also known as the \"Persian Longhair\" in English-speaking countries. The first documented ancestors of Persian cats were imported into Italy from Persia around 1620."
)

val FakeCatLists = listOf(
    FakeCatItem,
    FakeCatItem,
    FakeCatItem,
    FakeCatItem,
)

val FakeCatBreedList = listOf(
        "Persian",
        "Maine Coon",
        "Shorthair",
        "Siamese",
)

val FakeCatCategoryList = listOf(
    "A",
    "B",
    "C",
    "D",
)

val FakeBananaLocation = BananaLocation(
    address = "300 Memorial Blvd, \nWinnipeg, MB R3C 1V1",
    placeName = "Market A",
    coordinate = Coordinate(latitude = 49.302488, longitude = -123.141718),
    locationImageUrl = "https://www.digisystem" +
            ".com/ca/en/case-studies/20190711060317/news_file_global/file/Masoutis%2001.jpg"
)

val FakeBananaItem = BananaItem(
    name = "banana",
    price = 100f,
    imageUrl = "https://images.theconversation" +
            ".com/files/269559/original/file-20190416-147522-9uqn1q.jpg?ixlib=rb-1.1.0&q=45&auto=format&w=1200&h=900.0&fit=crop",
    description = "Cavendish bananas are the fruits of one of a number of banana cultivars " +
            "belonging to the Cavendish subgroup of the AAA banana cultivar group. The same term is also used to describe the plants on which the bananas grow. They include commercially important cultivars like 'Dwarf Cavendish' and 'Grand Nain'.",
    location  = FakeBananaLocation
)

val FakeBananaItems = listOf(
    FakeBananaItem,
    FakeBananaItem,
    FakeBananaItem,
    FakeBananaItem,

    FakeBananaItem,
    FakeBananaItem,
    FakeBananaItem,
    FakeBananaItem,

    FakeBananaItem,
)

