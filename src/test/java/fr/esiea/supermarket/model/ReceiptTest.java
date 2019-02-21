package fr.esiea.supermarket.model;

import fr.esiea.supermarket.model.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.*;

public class ReceiptTest {

	@Test
	public void testAllReceipt(){		
		Receipt receipt = new Receipt();

		List<ReceiptItem> testItems = new ArrayList<>();
		List<Discount> testDiscounts = new ArrayList<>();
		
		Product toothbrush = new Product("toothbrush", ProductUnit.Each);
		receipt.addProduct(toothbrush, 1.0, 1.0, 1.0);
		testItems.add(new ReceiptItem(toothbrush, 1.0, 1.0, 1.0));

		Product soap = new Product("soap", ProductUnit.Each);		
		receipt.addProduct(soap, 1.0, 2.0, 2.0);
		testItems.add(new ReceiptItem(soap, 1.0, 2.0, 2.0));		
		
		Discount dToothbrush = new Discount(toothbrush, "Winter discount", 0.5);
		receipt.addDiscount(dToothbrush);
		testDiscounts.add(dToothbrush);
		
		Discount dSoap = new Discount(soap, "Winter discount", 1.0);
		receipt.addDiscount(dSoap);
		testDiscounts.add(dSoap);
		
			
        Assertions.assertThat(receipt.getTotalPrice()).isEqualTo(1.5);
        Assertions.assertThat(receipt.getItems().equals(testItems)).isTrue();
        Assertions.assertThat(receipt.getDiscounts().equals(testDiscounts)).isTrue();
	}
}