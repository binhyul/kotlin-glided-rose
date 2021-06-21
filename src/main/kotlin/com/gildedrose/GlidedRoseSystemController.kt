package com.gildedrose

class ItemSystem :GlidedRoseSystem(){
    override fun passOneDay(item: Item) {
        downSellIn(item)
        if(sellInTimeout(item)){
            downQuality(item,2)
        }else{
            downQuality(item,1)
        }
    }

    private fun downQuality(item: Item, downPoint : Int){
        if(item.quality > downPoint){
            item.quality -= downPoint
        }else{
            zeroQuality(item)
        }
    }
}

class AgedItemSystem : GlidedRoseSystem(){
    override fun passOneDay(item: Item) {
        downSellIn(item)
        if(sellInTimeout(item)){
            upQuality(item,2)
        }else{
            upQuality(item,1)
        }
    }

    private fun upQuality(item: Item, upPoint : Int){
        if(item.quality +upPoint <= 50){
            item.quality += upPoint
        }else{
            maxQuality(item)
        }
    }
}

class BackStageTicketSystem : GlidedRoseSystem(){
    override fun passOneDay(item: Item) {
        val todayLeftSellIn = item.sellIn
        downSellIn(item)
        checkTicket(item,todayLeftSellIn)
    }


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
}

abstract class GlidedRoseSystem: GlidedRoseSystemController{
     fun downSellIn(item: Item){
        item.sellIn -= 1
    }
     fun sellInTimeout(item: Item) = item.sellIn < 0

     fun maxQuality(item: Item){
        item.quality =50
    }

     fun zeroQuality(item: Item){
        item.quality =0
    }
}

interface GlidedRoseSystemController{
    fun passOneDay(item: Item)
}


