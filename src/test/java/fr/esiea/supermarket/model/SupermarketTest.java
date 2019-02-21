package fr.esiea.supermarket.model;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

public class SupermarketTest {

    @Test
    public void testSomething() {
        SupermarketCatalog catalog = new FakeCatalog();
        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, 0.99);
        Product apples = new Product("apples", ProductUnit.Kilo);
        catalog.addProduct(apples, 1.99);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(apples, 2.5);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, toothbrush, 10.0);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        Assertions.assertThat(receipt.getTotalPrice()).as("Price for 2.5kg of apples.").isEqualTo(2.5*1.99);

        cart.addItemQuantity(apples, 1.5);
        cart.addItem(toothbrush);

        receipt = teller.checksOutArticlesFrom(cart);
    }
}
