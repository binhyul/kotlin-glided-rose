package com.gildedrose.system.factory

import com.gildedrose.BACKSTAGE_PASS_TICKET_NAME
import com.gildedrose.CHEESE_NAME
import com.gildedrose.Item
import com.gildedrose.LEGEND_NAME
import com.gildedrose.system.AgedItemSystem
import com.gildedrose.system.ItemSystem
import com.gildedrose.system.LegendSystem
import com.gildedrose.system.TicketSystem
import com.gildedrose.system.controller.GlidedRoseSystemController


class SystemFactory {

    private val itemSystem: GlidedRoseSystemController = ItemSystem()
    private val agedItemSystem: GlidedRoseSystemController = AgedItemSystem()
    private val ticketItemSystem: GlidedRoseSystemController = TicketSystem()
    private val legendSystem : GlidedRoseSystemController = LegendSystem()

    fun getItemSystem(item: Item): GlidedRoseSystemController {
        return when  {
            item.name.contains(CHEESE_NAME) -> agedItemSystem
            item.name.contains(BACKSTAGE_PASS_TICKET_NAME) -> ticketItemSystem
            item.name.contains(LEGEND_NAME) -> legendSystem
            else -> itemSystem
        }
    }


}