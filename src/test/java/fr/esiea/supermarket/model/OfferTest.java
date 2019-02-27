package fr.esiea.supermarket.model;

import fr.esiea.supermarket.model.*;
import fr.esiea.supermarket.model.offer.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OfferTest {
	private Product toothbrush = new Product("toothbrush", ProductUnit.Each);

	@Test
	public void testXforAmount(){		
		//XforAmount offer = new XforAmount(5, toothbrush, 3.0);

		//Assertions.assertThat(offer.getProduct().equals(toothbrush)).isTrue();

		//Assertions.assertThat(offer.getSpecialDiscount(1.0, 1.0).getDiscountAmount()).isEqualTo(0.0);
		//Assertions.assertThat(offer.getSpecialDiscount(5.0, 1.0).getDiscountAmount()).isEqualTo(3.0);
	}
}