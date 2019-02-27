package fr.esiea.supermarket.model;

import fr.esiea.supermarket.model.offer.*;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

public class SupermarketTest {

    @Test
    public void testSomething() {
        SupermarketCatalog catalog = new FakeCatalog();
        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, 1.0);
        Product toothpaste = new Product("toothpaste", ProductUnit.Kilo);
        catalog.addProduct(toothpaste, 15.0);
        Product hairbrush = new Product("hairbrush", ProductUnit.Each);
        catalog.addProduct(hairbrush, 3.5);
        Product soap = new Product("soap", ProductUnit.Each);
        catalog.addProduct(soap, 2.5);
        Product apples = new Product("apples", ProductUnit.Kilo);
        catalog.addProduct(apples, 2.0);
        Product bananas = new Product("bananas", ProductUnit.Kilo);
        catalog.addProduct(bananas, 0.5);
        Product cherries = new Product("cherries", ProductUnit.Kilo);
        catalog.addProduct(cherries, 4.0);

        Product none = new Product("none", ProductUnit.Kilo);
        catalog.addProduct(none, 100.0);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(apples, 2.5);
        Teller teller = new Teller(catalog);
        Receipt receipt = teller.checksOutArticlesFrom(cart);
        Assertions.assertThat(receipt.getTotalPrice()).as("Price for 2.5kg of apples.").isEqualTo(2.5*2.0);

        cart.addItemQuantity(toothbrush, 3.0);
        cart.addItemQuantity(toothpaste, 2.0);
        cart.addItemQuantity(hairbrush, 5.0);
        cart.addItem(soap);
        cart.addItemQuantity(bananas, 1.0);
        cart.addItemQuantity(bananas, 1.0);
        cart.addItemQuantity(cherries, 5.0);
        cart.addItemQuantity(apples, 1.0);
        cart.addItemQuantity(none, 0.0);

        teller.addSpecialOffer(new ThreeForTwo(toothbrush));
        teller.addSpecialOffer(new PercentDiscount(cherries, 50));
        teller.addSpecialOffer(new XforAmount(2, bananas, 1.0));
        teller.addSpecialOffer(new XforAmount(5, hairbrush, 1.0));

        teller.addSpecialOffer(new XforAmount(5, apples, 1.0));
        teller.addSpecialOffer(new ThreeForTwo(toothpaste));
        teller.addSpecialOffer(new PercentDiscount(none, 50.0));

        teller.addSpecialOffer(null);

	    Product false3for2 = new Product("false3for2", ProductUnit.Kilo);
        catalog.addProduct(false3for2, 1.0);
        Product false10 = new Product("false10", ProductUnit.Kilo);
        catalog.addProduct(false10, 1.0);
        Product false2 = new Product("false2", ProductUnit.Kilo);
        catalog.addProduct(false2, 1.0);
        Product false5 = new Product("false5", ProductUnit.Kilo);
        catalog.addProduct(false5, 1.0);

        cart.addItem(false3for2);
        cart.addItem(false10);
        cart.addItem(false2);
        cart.addItem(false5);

        /*teller.addSpecialOffer(SpecialOfferType.ThreeForTwo, false3for2, 3.5);
        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, false10, 10.5);
        teller.addSpecialOffer(SpecialOfferType.TwoForAmount, false2, 2.5);
        teller.addSpecialOffer(SpecialOfferType.FiveForAmount, false5, 5.5);*/

		receipt = teller.checksOutArticlesFrom(cart);

        Assertions.assertThat(receipt.getTotalPrice()).isEqualTo(57.5);
    }
}
