package com.gildedrose

class ItemHandler :GlidedRoseSystemController{
    override fun passOneDay(item: Item) {
        downSellIn(item)
        if(sellInTimeout(item)){
            downQuality(item,2)
        }else{
            downQuality(item,1)
        }
    }

    private fun downSellIn(item: Item){
        item.sellIn -= 1
    }
    private fun sellInTimeout(item: Item) = item.sellIn < 0

    private fun downQuality(item: Item, downPoint : Int){
        if(item.quality > downPoint){
            item.quality -= downPoint
        }else{
            zeroQuality(item)
        }
    }

    private fun zeroQuality(item: Item){
        item.quality =0
    }
}

class AgedItemHandler : GlidedRoseSystemController{
    override fun passOneDay(item: Item) {
        downSellIn(item)
        if(sellInTimeout(item)){
            upQuality(item,2)
        }else{
            upQuality(item,1)
        }
    }

    private fun downSellIn(item: Item){
        item.sellIn -= 1
    }
    private fun sellInTimeout(item: Item) = item.sellIn < 0

    private fun upQuality(item: Item, upPoint : Int){
        if(item.quality +upPoint <= 50){
            item.quality += upPoint
        }else{
            maxQuality(item)
        }
    }

    private fun maxQuality(item: Item){
        item.quality =50
    }

}

class BackStageTicketItemHandler : GlidedRoseSystemController{
    override fun passOneDay(item: Item) {
        val todayLeftSellIn = item.sellIn
        downSellIn(item)
        checkTicket(item,todayLeftSellIn)
    }

    private fun downSellIn(item: Item){
        item.sellIn -= 1
    }
    private fun sellInTimeout(item: Item) = item.sellIn < 0

    private fun checkTicket(item: Item,sellIn : Int){
        when{
            sellInTimeout(item) -> zeroQuality(item)
           sellIn <= 5  -> upQuality(item,3)
           sellIn <= 10 -> upQuality(item,2)
            else -> upQuality(item,1)
        }
    }

    private fun upQuality(item: Item, upPoint : Int){
        if(item.quality +upPoint <= 50){
            item.quality += upPoint
        }else{
            maxQuality(item)
        }
    }

    private fun maxQuality(item: Item){
        item.quality =50
    }

    private fun zeroQuality(item: Item){
        item.quality =0
    }
}

interface GlidedRoseSystemController{
    fun passOneDay(item: Item)
}


