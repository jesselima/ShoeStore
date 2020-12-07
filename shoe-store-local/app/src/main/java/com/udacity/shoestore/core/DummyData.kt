package com.udacity.shoestore.core

import tech.jesselima.local.sqlite.data.shoes.models.Shoe

/**
 * Created by jesselima on 7/12/20.
 * This is a part of the project ShoeStore.
 */
fun getDummyShoes(): List<Shoe> {
    // Sample Data for testes only
    return listOf(
        Shoe(
            name = "Boot Extreme",
            brand = "Adidas",
            category = "adventure",
            isHotSelling = true,
            price = 40.0f,
            quantitySold = 50,
            season = "Winter",
            stockQuantity = 15,
            year = 2019,
            image = "model_shoe_05"
        ),
        Shoe(
            name = "Climber X",
            brand = "X&Z",
            category = "social",
            isHotSelling = true,
            price = 120.0f,
            quantitySold = 48,
            season = "Summer",
            stockQuantity = 15,
            year = 2020,
            image = "model_shoe_01"
        ),
        Shoe(
            name = "Urban Low",
            brand = "Nike",
            category = "casual",
            isHotSelling = false,
            price = 55.0f,
            quantitySold = 48,
            season = "Winter",
            stockQuantity = 51,
            year = 2019,
            image = "model_shoe_08"
        ),
        Shoe(
            name = "Adventura Mantis",
            brand = "Victor Hugo",
            category = "social",
            isHotSelling = true,
            price = 56.0f,
            quantitySold = 150,
            season = "Summer",
            stockQuantity = 15,
            year = 2019,
            image = "model_shoe_04"
        ),
        Shoe(
            name = "Boot Master X 1",
            brand = "Nike",
            category = "casual",
            isHotSelling = false,
            price = 35.0f,
            quantitySold = 48,
            season = "Winter",
            stockQuantity = 77,
            year = 2020,
            image = "model_shoe_03"
        ),
        Shoe(
            name = "Altitude 5",
            brand = "Pulma",
            category = "casual",
            isHotSelling = false,
            price = 150.0f,
            quantitySold = 123,
            season = "Winter",
            stockQuantity = 88,
            year = 2020,
            image = "none"
        ),
        Shoe(
            name = "Sport Clutch",
            brand = "Pulma",
            category = "social",
            isHotSelling = true,
            price = 45.0f,
            quantitySold = 50,
            season = "Summer",
            stockQuantity = 15,
            year = 2020,
            image = "model_shoe_02"
        ),
        Shoe(
            name = "Boot Master X 1",
            brand = "Nike",
            category = "casual",
            isHotSelling = false,
            price = 93.0f,
            quantitySold = 48,
            season = "Winter",
            stockQuantity = 90,
            year = 2019,
            image = "model_shoe_01"
        ),
        Shoe(
            name = "Runner Rouge",
            brand = "Nike",
            category = "casual",
            isHotSelling = true,
            price = 120.0f,
            quantitySold = 56,
            season = "Spring",
            stockQuantity = 21,
            year = 2020,
            image = "none"
        ),
        Shoe(
            name = "Adistar Pop",
            brand = "Adidas",
            category = "sport",
            isHotSelling = false,
            price = 70.0f,
            quantitySold = 48,
            season = "Summer",
            stockQuantity = 60,
            year = 2020,
            image = "model_shoe_09"
        ),
        Shoe(
            name = "Adventura Mantis",
            brand = "Victor Hugo",
            category = "social",
            isHotSelling = true,
            price = 56.0f,
            quantitySold = 150,
            season = "Summer",
            stockQuantity = 15,
            year = 2019,
            image = "model_shoe_04"
        ),
        Shoe(
            name = "Boot Master X 1",
            brand = "Nike",
            category = "casual",
            isHotSelling = false,
            price = 35.0f,
            quantitySold = 48,
            season = "Winter",
            stockQuantity = 77,
            year = 2020,
            image = "model_shoe_03"
        ),
        Shoe(
            name = "Sport Clutch",
            brand = "Pulma",
            category = "social",
            isHotSelling = true,
            price = 45.0f,
            quantitySold = 50,
            season = "Summer",
            stockQuantity = 15,
            year = 2020,
            image = "model_shoe_02"
        ),
        Shoe(
            name = "Boot Master X 1",
            brand = "Nike",
            category = "casual",
            isHotSelling = false,
            price = 93.0f,
            quantitySold = 48,
            season = "Winter",
            stockQuantity = 90,
            year = 2019,
            image = "model_shoe_01"
        ),
        Shoe(
            name = "Adistar Pop",
            brand = "Adidas",
            category = "sport",
            isHotSelling = false,
            price = 70.0f,
            quantitySold = 48,
            season = "Summer",
            stockQuantity = 60,
            year = 2020,
            image = "model_shoe_09"
        )
    )
}