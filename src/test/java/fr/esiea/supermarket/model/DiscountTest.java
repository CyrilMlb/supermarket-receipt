package fr.esiea.supermarket.model;

import fr.esiea.supermarket.model.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DiscountTest {
	private Product toothbrush = new Product("toothbrush", ProductUnit.Each);
	private String desc = "Winter Discount";
	private double discountAmount = 0.2;
	
	private Discount discount = new Discount(toothbrush, desc, discountAmount);

	@Test
	public void testDiscountGetProduct(){		
        Assertions.assertThat(discount.getProduct()).isEqualTo(toothbrush);
	}
	
	@Test
	public void testDiscountGetDescription(){
        Assertions.assertThat(discount.getDescription()).isEqualTo(desc);
	}
	
	@Test
	public void testDiscountGetDiscountAmount(){
        Assertions.assertThat(discount.getDiscountAmount()).isEqualTo(discountAmount);	
	}
}