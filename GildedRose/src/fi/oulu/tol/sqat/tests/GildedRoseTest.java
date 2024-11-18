package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {

	@Test
	public void testTheTruth() {
		assertTrue(true);
	}
	@Test
	public void exampleTest() {	
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 10, 20));
		inn.oneDay();
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		//assert quality has decreased by one
		assertEquals("Failed quality for Dexterity Vest", 19, quality);
	}
	
	 @Test
	 public void testMainMethodOutput() {
		 GildedRose.main(new String[]{});
		 assertTrue(true);
	 }
	
	@Test
    public void testNormalItemSellInDecreases() {
        GildedRose inn = new GildedRose();
        inn.setItem(new Item("+5 Dexterity Vest", 10, 20));
        inn.oneDay();
        
        List<Item> items = inn.getItems();
        int sellIn = items.get(0).getSellIn();
        
        assertEquals("Normal item sellIn did not decrease as expected", 9, sellIn);
    }

    @Test
    public void testAgedBrieSellInDecreases() {
        GildedRose inn = new GildedRose();
        inn.setItem(new Item("Aged Brie", 2, 0));
        inn.oneDay();
        
        List<Item> items = inn.getItems();
        int sellIn = items.get(0).getSellIn();
        
        assertEquals("Aged Brie sellIn did not decrease as expected", 1, sellIn);
    }

    @Test
    public void testBackstagePassesSellInDecreases() {
        GildedRose inn = new GildedRose();
        inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        inn.oneDay();
        
        List<Item> items = inn.getItems();
        int sellIn = items.get(0).getSellIn();
        
        assertEquals("Backstage passes sellIn did not decrease as expected", 14, sellIn);
    }
	
    @Test
    public void testNormalItemQualityDecreases() {
        GildedRose inn = new GildedRose();
        inn.setItem(new Item("+5 Dexterity Vest", 1, 20));
        inn.oneDay();
        
        List<Item> items = inn.getItems();
        int quality = items.get(0).getQuality();
        
        assertEquals("Normal item quality did not decrease as expected", 19, quality);
    }

    @Test
    public void testNormalItemQualityDecreasesTwiceAfterSellIn() {
        GildedRose inn = new GildedRose();
        inn.setItem(new Item("Elixir of the Mongoose", 0, 10));
        inn.oneDay();
        
        List<Item> items = inn.getItems();
        int quality = items.get(0).getQuality();
        
        assertEquals("Normal item quality did not decrease twice as expected after SellIn", 8, quality);
    }

    @Test
    public void testQualityNeverNegative() {
        GildedRose inn = new GildedRose();
        inn.setItem(new Item("Elixir of the Mongoose", 0, 0));
        inn.oneDay();
        
        List<Item> items = inn.getItems();
        int quality = items.get(0).getQuality();
        
        assertEquals("Quality should not go below zero", 0, quality);
    }

    @Test
    public void testAgedBrieIncreasesInQuality() {
        GildedRose inn = new GildedRose();
        inn.setItem(new Item("Aged Brie", 10, 20));
        inn.oneDay();
        
        List<Item> items = inn.getItems();
        int quality = items.get(0).getQuality();
        
        assertEquals("Aged Brie should increase in quality", 21, quality);
    }
    
    @Test
    public void testAgedBrieIncreasesInQualityAfterNegativeSellIn() {
        GildedRose inn = new GildedRose();
        inn.setItem(new Item("Aged Brie", -2, 20));
        inn.oneDay();
        
        List<Item> items = inn.getItems();
        int quality = items.get(0).getQuality();
        
        assertEquals("Aged Brie should increase in quality", 21, quality);
    }

    @Test
    public void testQualityDoesNotExceed50() {
        GildedRose inn = new GildedRose();
        inn.setItem(new Item("Aged Brie", 10, 50));
        inn.oneDay();
        
        List<Item> items = inn.getItems();
        int quality = items.get(0).getQuality();
        
        assertEquals("Quality should not exceed 50", 50, quality);
    }

    @Test
    public void testSulfurasQualityDoesNotChange() {
        GildedRose inn = new GildedRose();
        inn.setItem(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        inn.oneDay();
        
        List<Item> items = inn.getItems();
        int quality = items.get(0).getQuality();
        
        assertEquals("Sulfuras quality should not change", 80, quality);
    }

    @Test
    public void testSulfurasSellInDoesNotChange() {
        GildedRose inn = new GildedRose();
        inn.setItem(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        inn.oneDay();
        
        List<Item> items = inn.getItems();
        int sellIn = items.get(0).getSellIn();
        
        assertEquals("Sulfuras sellIn should not change", 0, sellIn);
    }

    @Test
    public void testBackstagePassesIncreaseBy1WhenMoreThan10Days() {
        GildedRose inn = new GildedRose();
        inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 11, 20));
        inn.oneDay();
        
        List<Item> items = inn.getItems();
        int quality = items.get(0).getQuality();
        
        assertEquals("Backstage passes should increase by 1 when more than 10 days left", 21, quality);
    }
    
    @Test
    public void testBackstagePassesIncreaseMaxWhenMoreThan10Days() {
        GildedRose inn = new GildedRose();
        inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 50));
        inn.oneDay();
        
        List<Item> items = inn.getItems();
        int quality = items.get(0).getQuality();
        
        assertEquals("Backstage passes should not increase when quality is 50", 50, quality);
    }

    @Test
    public void testBackstagePassesIncreaseBy2When10DaysOrLess() {
        GildedRose inn = new GildedRose();
        inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 6, 20));
        inn.oneDay();
        
        List<Item> items = inn.getItems();
        int quality = items.get(0).getQuality();
        
        assertEquals("Backstage passes should increase by 2 when 10 days or less", 22, quality);
    }
    
    @Test
    public void testBackstagePassesIncreaseMaxWhen10DaysOrLess() {
        GildedRose inn = new GildedRose();
        inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 10, 50));
        inn.oneDay();
        
        List<Item> items = inn.getItems();
        int quality = items.get(0).getQuality();
        
        assertEquals("Backstage passes should not increase when quality is 50", 50, quality);
    }

    @Test
    public void testBackstagePassesIncreaseBy3When5DaysOrLess() {
        GildedRose inn = new GildedRose();
        inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20));
        inn.oneDay();
        
        List<Item> items = inn.getItems();
        int quality = items.get(0).getQuality();
        
        assertEquals("Backstage passes should increase by 3 when 5 days or less", 23, quality);
    }
    
    @Test
    public void testBackstagePassesIncreaseMaxWhen5DaysOrLess() {
        GildedRose inn = new GildedRose();
        inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 50));
        inn.oneDay();
        
        List<Item> items = inn.getItems();
        int quality = items.get(0).getQuality();
        
        assertEquals("Backstage passes should not increase when quality is 50", 50, quality);
    }

    @Test
    public void testBackstagePassesDropToZeroAfterConcert() {
        GildedRose inn = new GildedRose();
        inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20));
        inn.oneDay();
        
        List<Item> items = inn.getItems();
        int quality = items.get(0).getQuality();
        
        assertEquals("Backstage passes should drop to zero after concert", 0, quality);
    }

    
    @Test
    public void testUpdateQualityWithNoItems() {
        GildedRose inn = new GildedRose(); // Empty inn, no items
        inn.oneDay(); // Update quality with an empty list
        assertTrue("Items list should be empty", inn.getItems().isEmpty());
    }

    @Test
    public void testUpdateQualityWithOneItem() {
        GildedRose inn = new GildedRose();
        inn.setItem(new Item("Elixir of the Mongoose", 5, 7)); // Regular item
        inn.oneDay();
        Item item = inn.getItems().get(0);
        assertEquals("Quality should decrease by 1", 6, item.getQuality());
        assertEquals("SellIn should decrease by 1", 4, item.getSellIn());
    }

    @Test
    public void testUpdateQualityWithMultipleItems() {
        GildedRose inn = new GildedRose();
        inn.setItem(new Item("+5 Dexterity Vest", 10, 20));
        inn.setItem(new Item("Aged Brie", 2, 0));
        inn.setItem(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));

        inn.oneDay();

        List<Item> items = inn.getItems();
        
        assertEquals("Quality should decrease for Dexterity Vest", 19, items.get(0).getQuality());
        assertEquals("Quality should increase for Aged Brie", 1, items.get(1).getQuality());
        assertEquals("Quality should remain the same for Sulfuras", 80, items.get(2).getQuality());
        assertEquals("Quality should increase for Backstage passes", 21, items.get(3).getQuality());
    }

    
    
}
