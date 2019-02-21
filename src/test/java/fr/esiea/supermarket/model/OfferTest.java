package fr.esiea.supermarket.model;

import fr.esiea.supermarket.model.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OfferTest {
	private Product toothbrush = new Product("toothbrush", ProductUnit.Each);
	private SpecialOfferType spOffer = SpecialOfferType.TenPercentDiscount;
	private double argument = 0.7;
	
	private Offer offer = new Offer(spOffer, toothbrush, argument);

	@Test
	public void testOfferGetProduct(){		
        Assertions.assertThat(offer.getProduct()).isEqualTo(toothbrush);
	}
}