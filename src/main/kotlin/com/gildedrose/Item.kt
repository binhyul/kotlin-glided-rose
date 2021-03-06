package com.gildedrose

const val CONJURED = "Conjured "
const val CHEESE_NAME = "Aged Brie"
const val BACKSTAGE_PASS_TICKET_NAME = "Backstage passes to a TAFKAL80ETC concert"
const val LEGEND_NAME = "Sulfuras, Hand of Ragnaros"

const val MAX_QUALITY = 50
const val MIN_QUALITY = 0

data class Item(
        var name: String,
        var sellIn: Int,
        var quality: Int
)