package fr.esiea.supermarket.model;

import fr.esiea.supermarket.model.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class ReceiptItemTest {
	private Product toothbrush = new Product("toothbrush", ProductUnit.Each);
	private double price = 1.0;
	private double totalPrice = 0.9;
	private double quantity = 2.0;
	
	private ReceiptItem receiptItem = new ReceiptItem(toothbrush, quantity, price, totalPrice);
	private ReceiptItem receiptItem2 = new ReceiptItem(toothbrush, quantity, price, totalPrice);
	
	@Test
	public void testReceiptItemGetPrice(){	
        Assertions.assertThat(receiptItem.getPrice()).isEqualTo(price);	
	}
	
	@Test
	public void testReceiptItemGetProduct(){	
        Assertions.assertThat(receiptItem.getProduct()).isEqualTo(toothbrush);	
	}
	
	@Test
	public void testReceiptItemGetQuantity(){	
        Assertions.assertThat(receiptItem.getQuantity()).isEqualTo(quantity);	
	}

	@Test
	public void testReceiptItemGetTotalPrice(){	
        Assertions.assertThat(receiptItem.getTotalPrice()).isEqualTo(totalPrice);	
	}
	
	@Test
	public void testReceiptItemEqualsNULL(){
        Assertions.assertThat(receiptItem.equals(null)).isFalse();
        Assertions.assertThat(receiptItem.equals(toothbrush)).isFalse();
        Assertions.assertThat(receiptItem.equals(receiptItem)).isTrue();
	

		Product toothbrush2 = new Product("toothbrush2", ProductUnit.Each);
		ReceiptItem notReceiptItemTotalPrice = new ReceiptItem(toothbrush, quantity, price, totalPrice - 0.1);
		ReceiptItem notReceiptItemPrice = new ReceiptItem(toothbrush, quantity, price + 0.5, totalPrice);
		ReceiptItem notReceiptItemProduct = new ReceiptItem(toothbrush2, quantity, price, totalPrice);
		ReceiptItem notReceiptItemQuantity = new ReceiptItem(toothbrush, quantity + 1.0, price, totalPrice);

	    Assertions.assertThat(receiptItem.equals(notReceiptItemPrice)).isFalse();
	    Assertions.assertThat(receiptItem.equals(notReceiptItemProduct)).isFalse();
	    Assertions.assertThat(receiptItem.equals(notReceiptItemQuantity)).isFalse();
	    Assertions.assertThat(receiptItem.equals(notReceiptItemTotalPrice)).isFalse();

        Assertions.assertThat(receiptItem.equals(receiptItem2)).isTrue();
	}
	
	@Test
	public void testReceiptItemHashCode(){		
        Assertions.assertThat(receiptItem.hashCode()).isEqualTo(Objects.hash(toothbrush, price, totalPrice, quantity));
	}
}
