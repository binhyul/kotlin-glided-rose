package com.gildedrose


class SystemFactory {

    private val itemSystem: GlidedRoseSystemController = ItemSystem()
    private val agedItemSystem: GlidedRoseSystemController = AgedItemSystem()
    private val ticketItemSystem: GlidedRoseSystemController = TicketSystem()

    fun getItemSystem(item: Item): GlidedRoseSystemController? {
        return when (item.name) {
            CHEESE_NAME -> agedItemSystem
            BACKSTAGE_PASS_TICKET_NAME -> ticketItemSystem
            LEGEND_NAME -> null
            else -> itemSystem
        }
    }


}