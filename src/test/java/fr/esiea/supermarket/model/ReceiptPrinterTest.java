package fr.esiea.supermarket;

import fr.esiea.supermarket.model.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.Objects;

public class ReceiptPrinterTest {

	@Test
	public void testReceiptPrinter(){
		ReceiptPrinter rp1 = new ReceiptPrinter();
		ReceiptPrinter rp2 = new ReceiptPrinter(20);

		SupermarketCatalog catalog = new FakeCatalog();
                Product toothbrush = new Product("toothbrush", ProductUnit.Each);
                catalog.addProduct(toothbrush, 0.99);
                Product apples = new Product("apples", ProductUnit.Kilo);
                catalog.addProduct(apples, 1.99);
                Product soap = new Product("soap", ProductUnit.Kilo);
                catalog.addProduct(soap, 2.5);

                ShoppingCart cart = new ShoppingCart();
                cart.addItemQuantity(apples, 2.5);
                cart.addItemQuantity(toothbrush, 3.0);
                cart.addItemQuantity(soap, 1.0);

                Teller teller = new Teller(catalog);
                //teller.addSpecialOffer(SpecialOfferType.ThreeForTwo, toothbrush, 3.0);
                Receipt receipt = teller.checksOutArticlesFrom(cart);

                Assertions.assertThat(rp2.printReceipt(receipt)).isNotBlank();
	}
}