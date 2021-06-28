package com.gildedrose


class SystemFactory {

    private val itemSystem: GlidedRoseSystemController = ItemSystem()
    private val agedItemSystem: GlidedRoseSystemController = AgedItemSystem()
    private val ticketItemSystem: GlidedRoseSystemController = TicketSystem()

    fun getItemSystem(item: Item): GlidedRoseSystemController? {
        return when  {
            item.name.contains(CHEESE_NAME) -> agedItemSystem
            item.name.contains(BACKSTAGE_PASS_TICKET_NAME) -> ticketItemSystem
            item.name.contains(LEGEND_NAME) -> null
            else -> itemSystem
        }
    }


}