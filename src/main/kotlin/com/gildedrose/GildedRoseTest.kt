package com.gildedrose

import com.gildedrose.system.AgedItemSystem
import com.gildedrose.system.ItemSystem
import com.gildedrose.system.LegendSystem
import com.gildedrose.system.TicketSystem
import com.gildedrose.system.factory.SystemFactory
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

    private val conjured = CONJURED
    private val normalItemName = "Item"
    private val cheeseItemName = CHEESE_NAME
    private val passTicketName = BACKSTAGE_PASS_TICKET_NAME

    private val legendItemName = LEGEND_NAME
    private val legendItemQuality = 80
    private val legendItem = Item(legendItemName, 10, legendItemQuality)

    private val factory = SystemFactory()
    private val itemSystem = ItemSystem()
    private val agedItemSystem = AgedItemSystem()
    private val ticketSystem = TicketSystem()

    @Test
    fun `일반 아이템이면 일반 아이템 시스템에 들어간다`() {
        val item = Item(normalItemName, 10, 10)
        val gildedRose = GildedRose(arrayOf(item))
        val system = factory.getItemSystem(item)
        assert(system is ItemSystem)
    }

    @Test
    fun `치즈면 에이징 아이템 시스템에 들어간다`() {
        val item = Item(cheeseItemName, 10, 10)
        val gildedRose = GildedRose(arrayOf(item))
        val system = factory.getItemSystem(item)
        assert(system is AgedItemSystem)
    }

    @Test
    fun `백 스테이지 티켓이면 티켓 아이템 시스템에 들어간다`() {
        val item = Item(passTicketName, 10, 10)
        val gildedRose = GildedRose(arrayOf(item))
        val system = factory.getItemSystem(item)
        assert(system is TicketSystem)
    }

    @Test
    fun `전설 아이템이면 전설 시스템에 들어간다`() {
        val item = legendItem
        val gildedRose = GildedRose(arrayOf(item))
        val system = factory.getItemSystem(item)
        assert(system is LegendSystem)
    }

    /**
     * 일반 아이템
     */

    @Test
    fun `일반아이템_퀄리티가 0 이상이고 전설템이 아니면 퀄리티가 떨어진다`() {
        val item = Item(normalItemName, 10, 10)
        val gildedRose = GildedRose(arrayOf(item))
        gildedRose.updateQuality()
        assert(item.quality == 9)
    }

    @Test
    fun `일반아이템_퀄리티가 0 이상에서 더 떨어질순 없다`() {
        val item = Item(normalItemName, 10, 0)
        val gildedRose = GildedRose(arrayOf(item))
        gildedRose.updateQuality()
        assert(item.quality >= MIN_QUALITY)
    }

    @Test
    fun `판매일자가 다된 일반아이템 퀄리티가 2배로 떨어진다`() {
        val item = Item(normalItemName, 0, 30)
        val gildedRose = GildedRose(arrayOf(item))
        gildedRose.updateQuality()
        assert(item.quality == 28)

        gildedRose.updateQuality()
        assert(item.quality == 26)
    }

    @Test
    fun `아이템 판매일자는 음수가 될수 있다`() {
        val item = Item(normalItemName, 0, 30)
        val gildedRose = GildedRose(arrayOf(item))
        gildedRose.updateQuality()
        assert(item.sellIn == -1)
    }

    /**
     * 치즈 - 에이징되는 아이템
     */

    @Test
    fun `판매일자가 남아있는 치즈 퀄리티는 시간이 지나면 퀄리티가 1 올라간다`() {
        val item = Item(cheeseItemName, 10, 1)
        val gildedRose = GildedRose(arrayOf(item))
        gildedRose.updateQuality()
        assert(item.quality == 2)
    }

    @Test
    fun `치즈 퀄리티는 판매일자가 다되고 시간이 가면 퀄리티가 2씩 올라간다`() {
        val item = Item(cheeseItemName, 0, 30)
        val gildedRose = GildedRose(arrayOf(item))
        gildedRose.updateQuality()
        assert(item.quality == 32)
    }

    @Test
    fun `치즈 퀄리티는 시간이 지나도 퀄리티가 50을 넘을 수 없다`() {
        val item = Item(cheeseItemName, 10, 50)
        val gildedRose = GildedRose(arrayOf(item))
        gildedRose.updateQuality()
        assert(item.quality <= MAX_QUALITY)
    }

    /**
     * 티켓 아이템
     */

    @Test
    fun `백스테이지 입장권은 판매 기한 10일 이전까지 퀄리티가 1씩 증가한다`() {
        val item = Item(passTicketName, 11, 10)
        val gildedRose = GildedRose(arrayOf(item))
        gildedRose.updateQuality()
        assert(item.quality == 11)
    }

    @Test
    fun `백스테이지 입장권은 판매 기한 10일 전이 되면 퀄리티가 2씩 증가한다`() {
        val item = Item(passTicketName, 10, 10)
        val gildedRose = GildedRose(arrayOf(item))
        gildedRose.updateQuality()
        assert(item.quality == 12)
    }

    @Test
    fun `백스테이지 입장권은 판매 기한 5일전이 되면 퀄리티가 3씩 증가한다`() {
        val item = Item(passTicketName, 5, 10)
        val gildedRose = GildedRose(arrayOf(item))
        gildedRose.updateQuality()
        assert(item.quality == 13)
    }

    @Test
    fun `백스테이지 입장권은 판매 기한이 다 되면 퀄리티가 0이 된다`() {
        val item = Item(passTicketName, 0, 10)
        val gildedRose = GildedRose(arrayOf(item))
        gildedRose.updateQuality()
        assert(item.quality == 0)
    }

    @Test
    fun `백스테이지 입장권은 시간이 지나도 퀄리티가 50을 넘을 수 없다`() {
        val item = Item(passTicketName, 5, 49)
        val gildedRose = GildedRose(arrayOf(item))
        gildedRose.updateQuality()
        assert(item.quality <= MAX_QUALITY)
    }

    @Test
    fun `백스테이지 입장권은 시간이 지나도 퀄리티가 50을 넘을 수 없다2`() {
        val item = Item(passTicketName, 10, 49)
        val gildedRose = GildedRose(arrayOf(item))
        gildedRose.updateQuality()
        assert(item.quality <= MAX_QUALITY)
    }

    @Test
    fun `백스테이지 입장권은 시간이 지나도 퀄리티가 50을 넘을 수 없다3`() {
        val item = Item(passTicketName, 11, 50)
        val gildedRose = GildedRose(arrayOf(item))
        gildedRose.updateQuality()
        assert(item.quality <= MAX_QUALITY)
    }

    /**
     * 전설 아이템
     */

    @Test
    fun `전설템이면 퀄리티가 떨어지지 않는다`() {
        val gildedRose = GildedRose(arrayOf(legendItem))
        gildedRose.updateQuality()
        assert(legendItem.quality == 80)
    }

    @Test
    fun `전설템이 아니면 판매기한이 줄어든다`() {
        val cheeseItemSellIn = 10
        val normalItemSellIn = 11
        val passTicketItemSellIn = 12

        val cheeseItem = Item(cheeseItemName, cheeseItemSellIn, 10)
        val normalItem = Item(normalItemName, normalItemSellIn, 10)
        val passTicketItem = Item(passTicketName, passTicketItemSellIn, 10)

        val gildedRose = GildedRose(arrayOf(cheeseItem, passTicketItem, normalItem))
        gildedRose.updateQuality()
        assert(cheeseItem.sellIn == 9)
        assert(normalItem.sellIn == 10)
        assert(passTicketItem.sellIn == 11)
    }


    @Test
    fun `전설템이면 판매기한이 1줄어든다`() {
        val gildedRose = GildedRose(arrayOf(legendItem))
        gildedRose.updateQuality()
        assert(legendItem.sellIn == 9)
    }

    /**
     *
     * ConjuredItem 관련 테스트 케이스
     */

    /**
     * 마법의 아이템 판별
     */
    @Test
    fun `마법에 걸린 일반 아이템`() {
        val item = Item(conjured + normalItemName, 10, 10)
        assert(itemSystem.isConjuredItem(item.name))
    }

    @Test
    fun `마법에 걸린 숙성(치즈) 아이템`() {
        val item = Item(conjured + cheeseItemName, 10, 10)
        assert(agedItemSystem.isConjuredItem(item.name))
    }

    @Test
    fun `마법에 걸린 티켓 아이템`() {
        val item = Item(conjured + passTicketName, 10, 10)
        assert(ticketSystem.isConjuredItem(item.name))
    }

    /**
     * 마법에 걸린 아이템 시스템 체크
     */


    @Test
    fun `일반 마법 아이템이면 일반 아이템 시스템에 들어간다`() {
        val item = Item(conjured + normalItemName, 10, 10)
        val gildedRose = GildedRose(arrayOf(item))
        val system = factory.getItemSystem(item)
        assert(system is ItemSystem)
    }

    @Test
    fun `마법에 걸린 치즈면 에이징 아이템 시스템에 들어간다`() {
        val item = Item(conjured + cheeseItemName, 10, 10)
        val gildedRose = GildedRose(arrayOf(item))
        val system = factory.getItemSystem(item)
        assert(system is AgedItemSystem)
    }

    @Test
    fun `마법에 걸린 백 스테이지 티켓이면 티켓 아이템 시스템에 들어간다`() {
        val item = Item(conjured + passTicketName, 10, 10)
        val gildedRose = GildedRose(arrayOf(item))
        val system = factory.getItemSystem(item)
        assert(system is TicketSystem)
    }


    /**
     * 일반 마법 아이템
     */
    @Test
    fun `마법 일반아이템_퀄리티가 0 이상이고 전설템이 아니면 퀄리티가 떨어진다`() {
        val item = Item(conjured + normalItemName, 10, 10)
        val gildedRose = GildedRose(arrayOf(item))
        gildedRose.updateQuality()
        assert(item.quality == 8)
    }

    @Test
    fun `마법 일반아이템_퀄리티가 0 이상에서 더 떨어질순 없다`() {
        val item = Item(conjured + normalItemName, 10, 0)
        val gildedRose = GildedRose(arrayOf(item))
        gildedRose.updateQuality()
        assert(item.quality >= MIN_QUALITY)
    }

    @Test
    fun `판매일자가 다된 마법 일반아이템 퀄리티가 4배로 떨어진다`() {
        val item = Item(conjured + normalItemName, 0, 30)
        val gildedRose = GildedRose(arrayOf(item))
        gildedRose.updateQuality()
        assert(item.quality == 26)

        gildedRose.updateQuality()
        assert(item.quality == 22)
    }

    /**
     * 에이징 되는 마법 아이템
     */

    @Test
    fun `판매일자가 남아있는 마법의 치즈 퀄리티는 시간이 지나면 퀄리티가 2 올라간다`() {
        val item = Item(conjured + cheeseItemName, 10, 1)
        val gildedRose = GildedRose(arrayOf(item))
        gildedRose.updateQuality()
        assert(item.quality == 3)
    }

    @Test
    fun `마법의 치즈 퀄리티는 판매일자가 다되고 시간이 가면 퀄리티가 4씩 올라간다`() {
        val item = Item(conjured + cheeseItemName, 0, 30)
        val gildedRose = GildedRose(arrayOf(item))
        gildedRose.updateQuality()
        print("${item.quality}")
        assert(item.quality == 34)
    }

    @Test
    fun `마법의 치즈 퀄리티는 시간이 지나도 퀄리티가 50을 넘을 수 없다`() {
        val item = Item(conjured + cheeseItemName, 10, 49)
        val gildedRose = GildedRose(arrayOf(item))
        gildedRose.updateQuality()
        assert(item.quality <= MAX_QUALITY)
    }

    /**
     * 마법의 티켓 아이템
     */

    @Test
    fun `마법의 백스테이지 입장권은 판매 기한 10일 이전까지 퀄리티가 2씩 증가한다`() {
        val item = Item(conjured + passTicketName, 11, 10)
        val gildedRose = GildedRose(arrayOf(item))
        gildedRose.updateQuality()
        assert(item.quality == 12)
    }

    @Test
    fun `마법의 백스테이지 입장권은 판매 기한 10일 전이 되면 퀄리티가 4씩 증가한다`() {
        val item = Item(conjured + passTicketName, 10, 10)
        val gildedRose = GildedRose(arrayOf(item))
        gildedRose.updateQuality()
        assert(item.quality == 14)
    }

    @Test
    fun `마법의 백스테이지 입장권은 판매 기한 5일전이 되면 퀄리티가 3씩 증가한다`() {
        val item = Item(conjured + passTicketName, 5, 10)
        val gildedRose = GildedRose(arrayOf(item))
        gildedRose.updateQuality()
        assert(item.quality == 16)
    }

    @Test
    fun `마법의 백스테이지 입장권은 판매 기한이 다 되면 퀄리티가 0이 된다`() {
        val item = Item(conjured + passTicketName, 0, 10)
        val gildedRose = GildedRose(arrayOf(item))
        gildedRose.updateQuality()
        assert(item.quality == 0)
    }

    @Test
    fun `마법의 백스테이지 입장권은 시간이 지나도 퀄리티가 50을 넘을 수 없다`() {
        val item = Item(conjured + passTicketName, 5, 49)
        val gildedRose = GildedRose(arrayOf(item))
        gildedRose.updateQuality()
        assert(item.quality <= MAX_QUALITY)
    }


    @Test
    fun `마법의 백스테이지 입장권은 시간이 지나도 퀄리티가 50을 넘을 수 없다2`() {
        val item = Item(conjured + passTicketName, 10, 49)
        val gildedRose = GildedRose(arrayOf(item))
        gildedRose.updateQuality()
        assert(item.quality <= MAX_QUALITY)
    }

    @Test
    fun `마법의 백스테이지 입장권은 시간이 지나도 퀄리티가 50을 넘을 수 없다3`() {
        val item = Item(conjured + passTicketName, 11, 50)
        val gildedRose = GildedRose(arrayOf(item))
        gildedRose.updateQuality()
        assert(item.quality <= MAX_QUALITY)
    }
}