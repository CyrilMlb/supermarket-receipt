package CyrilMlb.model;

import CyrilMlb.model.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTest {
	private String name = "toothbrush";
	private Product toothbrush = new Product(name, ProductUnit.Each);
	
	private Product toothbrush2 = new Product(name, ProductUnit.Each);	
	private Product soap = new Product("soap", ProductUnit.Each);
	
	@Test
	public void testProductGetName(){		
        Assertions.assertThat(toothbrush.getProduct()).isEqualTo(name);
	}
	
	@Test
	public void testProductGetUnit(){		
        Assertions.assertThat(toothbrush.getUnit()).isEqualTo(ProductUnit.Each);
	}
	
	@Test
	public void testProductEqualsNULL(){
        Assertions.assertThat(toothbrush.equals(null)).isFalse();
	}
	
	@Test
	public void testProductEqualsProduct(){
        Assertions.assertThat(toothbrush.equals(toothbrush)).isTrue();
	}
	
	@Test
	public void testProductEqualsClone(){
        Assertions.assertThat(toothbrush.equals(toothbrush2)).isTrue();
	}	
	
	@Test
	public void testProductEqualsDifferent(){
        Assertions.assertThat(toothbrush.equals(soap)).isFalse();
	}
	
	@Test
	public void testProductHashCode(){		
        Assertions.assertThat(toothbrush.hashCode()).isEqualTo(Objects.hash("toothbrush",ProductUnit.Each));
	}
}
