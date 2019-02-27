import fr.esiea.supermarket.model.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class ProductTest {
	private String name = "toothbrush";
	private Product toothbrush = new Product(name, ProductUnit.Each);
	private Product soap = new Product("soap", ProductUnit.Each);
	
	@Test
	public void testProductGetName(){		
        Assertions.assertThat(toothbrush.getName()).isEqualTo(name);
	}
	
	@Test
	public void testProductGetUnit(){		
        Assertions.assertThat(toothbrush.getUnit()).isEqualTo(ProductUnit.Each);
	}
	
	@Test
	public void testProductEquals(){
        Assertions.assertThat(toothbrush.equals(null)).isFalse();        
        Assertions.assertThat(toothbrush.equals(name)).isFalse();
        Assertions.assertThat(toothbrush.equals(toothbrush)).isTrue();

		Product toothbrush2 = new Product("toothbrush2", ProductUnit.Each);
		Product toothbrush3 = new Product(name, ProductUnit.Kilo);
		Product toothbrush4 = new Product(name, ProductUnit.Each);

        Assertions.assertThat(toothbrush.equals(soap)).isFalse();
        Assertions.assertThat(toothbrush.equals(toothbrush2)).isFalse();
        Assertions.assertThat(toothbrush.equals(toothbrush3)).isFalse();
        Assertions.assertThat(toothbrush.equals(toothbrush4)).isTrue();
	}
	
	@Test
	public void testProductHashCode(){		
        Assertions.assertThat(toothbrush.hashCode()).isEqualTo(Objects.hash("toothbrush",ProductUnit.Each));
	}
}