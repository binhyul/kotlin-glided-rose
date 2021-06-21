package com.gildedrose

const val CHEESE_NAME ="Aged Brie"
const val BACKSTAGE_PASS_TICKET_NAME ="Backstage passes to a TAFKAL80ETC concert"
const val LEGEND_NAME = "Sulfuras, Hand of Ragnaros"

class GildedRose(var items: Array<Item>) {

    private val itemHandler = ItemHandler()
    private val agedItemHandler = AgedItemHandler()
    private val backStageTicketItemHandler = BackStageTicketItemHandler()

    fun updateQuality(){
        items.forEach {
            when(it.name){
                CHEESE_NAME -> agedItemHandler.passOneDay(it)
                BACKSTAGE_PASS_TICKET_NAME -> backStageTicketItemHandler.passOneDay(it)
                LEGEND_NAME -> {}
                else -> itemHandler.passOneDay(it)
            }
        }
    }




//
//    fun updateQuality() {
//        for (i in items.indices) {
//            // 치즈, 패스권 아니고
//            if (items[i].name != "Aged Brie" && items[i].name != "Backstage passes to a TAFKAL80ETC concert") {
//                // 퀄리티가 0이상이고
//                if (items[i].quality > 0) {
//                    // 전설템 아니면
//                    if (items[i].name != "Sulfuras, Hand of Ragnaros") {
//                        // 퀄리티 떨어짐
//                        items[i].quality = items[i].quality - 1
//                    }
//                }
//                // 치즈, 패스권임
//            } else {
//                // 퀄리티가 50 아래임
//                if (items[i].quality < 50) {
//                    // 그럼 퀄리티 증가
//                    items[i].quality = items[i].quality + 1
//
//                    // 패스권은 특별히
//                    if (items[i].name == "Backstage passes to a TAFKAL80ETC concert") {
//                        // 판매기한 10일 이하고 퀄리티 50 아래면 퀄리티 증가
//                        if (items[i].sellIn < 11) {
//                            if (items[i].quality < 50) {
//                                items[i].quality = items[i].quality + 1
//                            }
//                        }
//
//                        // 판매기한 5일 이하고 퀄리티 50아래면 퀄리티 더 증가
//                        if (items[i].sellIn < 6) {
//                            if (items[i].quality < 50) {
//                                items[i].quality = items[i].quality + 1
//                            }
//                        }
//                    }
//                }
//            }
//
//            // 전설템 아니면 판매기한이 줄어듬
//            if (items[i].name != "Sulfuras, Hand of Ragnaros") {
//                items[i].sellIn = items[i].sellIn - 1
//            }
//
//            // 판매기한이 0 아래인데
//            if (items[i].sellIn < 0) {
//                // 치즈가 아니고
//                if (items[i].name != "Aged Brie") {
//                    // 패스권도 아닌
//                    if (items[i].name != "Backstage passes to a TAFKAL80ETC concert") {
//                        // 전설템도 아닌 퀄리티 1 이상의 물품은 퀄리티가 떨어짐
//                        if (items[i].quality > 0) {
//                            if (items[i].name != "Sulfuras, Hand of Ragnaros") {
//                                items[i].quality = items[i].quality - 1
//                            }
//                        }
//                    } else {
//                        // 패스권은 퀄리티가 0이됨
//                        items[i].quality = items[i].quality - items[i].quality
//                    }
//                } else {
//                    // 치즈는 퀄리티가 50보다 아래면 올라감
//                    if (items[i].quality < 50) {
//                        items[i].quality = items[i].quality + 1
//                    }
//                }
//            }
//        }
//    }

}

