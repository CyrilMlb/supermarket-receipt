package fr.esiea.supermarket.model;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

public class SupermarketTest {

    @Test
    public void testSomething() {
        SupermarketCatalog catalog = new FakeCatalog();
        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, 0.99);
        Product hairbrush = new Product("hairbrush", ProductUnit.Each);
        catalog.addProduct(hairbrush, 3.5);
        Product soap = new Product("soap", ProductUnit.Each);
        catalog.addProduct(soap, 2.5);
        Product apples = new Product("apples", ProductUnit.Kilo);
        catalog.addProduct(apples, 1.99);
        Product bananas = new Product("bananas", ProductUnit.Kilo);
        catalog.addProduct(bananas, 0.5);
        Product cherries = new Product("cherries", ProductUnit.Kilo);
        catalog.addProduct(cherries, 3.9);


        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(apples, 2.5);
        Teller teller = new Teller(catalog);
        Receipt receipt = teller.checksOutArticlesFrom(cart);
        Assertions.assertThat(receipt.getTotalPrice()).as("Price for 2.5kg of apples.").isEqualTo(2.5*1.99);

        cart.addItemQuantity(toothbrush, 3.0);
        cart.addItemQuantity(hairbrush, 5.0);
        cart.addItem(soap);
        cart.addItemQuantity(bananas, 1.0);
        cart.addItemQuantity(bananas, 1.0);
        cart.addItemQuantity(cherries, 5.0);

        teller.addSpecialOffer(SpecialOfferType.ThreeForTwo, toothbrush, 3.0);
        teller.addSpecialOffer(SpecialOfferType.ThreeForTwo, soap, 3.5);
        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, cherries, 10.0);
        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, soap, 10.5);
        teller.addSpecialOffer(SpecialOfferType.TwoForAmount, bananas, 2.0);
        teller.addSpecialOffer(SpecialOfferType.TwoForAmount, soap, 2.5);
        teller.addSpecialOffer(SpecialOfferType.FiveForAmount, hairbrush, 5.0);
        teller.addSpecialOffer(SpecialOfferType.FiveForAmount, soap, 5.5);
        teller.addSpecialOffer(null, soap, 0.0);

		receipt = teller.checksOutArticlesFrom(cart);
    }
}
