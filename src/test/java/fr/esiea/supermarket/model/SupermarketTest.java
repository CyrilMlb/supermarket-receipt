package fr.esiea.supermarket.model;

import fr.esiea.supermarket.model.offer.*;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import java.util.*;

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

		receipt = teller.checksOutArticlesFrom(cart);
        Assertions.assertThat(receipt.getTotalPrice()).isEqualTo(53.5);
    }

    @Test
    public void testBundle(){
        SupermarketCatalog catalog = new FakeCatalog();
        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, 10.0);
        Product toothpaste = new Product("toothpaste", ProductUnit.Each);
        catalog.addProduct(toothpaste, 10.0);
        Product hairbrush = new Product("hairbrush", ProductUnit.Each);
        catalog.addProduct(hairbrush, 10.0);
        Product soap = new Product("soap", ProductUnit.Each);
        catalog.addProduct(soap, 10.0);

        ShoppingCart cart = new ShoppingCart();
        Teller teller = new Teller(catalog);

        cart.addItemQuantity(toothbrush, 2.0);
        cart.addItemQuantity(toothpaste, 2.0);
        cart.addItemQuantity(hairbrush, 2.0);

        Bundle bundle = new Bundle();
        bundle.addProduct(toothbrush, 5.0);
        bundle.addProduct(soap, 5.0);
        teller.addBundle(bundle);
 
        Bundle bundle2 = new Bundle();
        bundle2.addProduct(toothbrush, 1.0);
        bundle2.addProduct(toothpaste, 1.0);
        bundle2.addProduct(hairbrush, 1.0);    
        teller.addBundle(bundle2);

        Bundle bundle3 = new Bundle();
        teller.addBundle(bundle3);

        Receipt receipt = teller.checksOutArticlesFrom(cart);
        Assertions.assertThat(receipt.getTotalPrice()).isEqualTo(54.0);
    }
}
