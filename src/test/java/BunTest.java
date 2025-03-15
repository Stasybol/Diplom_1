import praktikum.Bun;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunTest {
    private String name;
    private float price;
    private Bun bun;

    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[][] setData() {
        return new Object[][]{
                {"black bun", 100F},
                {"white bun", 200.0F},
                {"red bun", 300.00F}
        };
    }

    @Before
    public void setUp() {
        bun = new Bun(name, price);
    }

    @Test
    public void getNameTest(){
        assertEquals("Название булочки не совпадает", name, bun.getName());
    }

    @Test
    public void getPriceTest(){
        assertEquals("Цена булочки не совпадает", price, bun.getPrice(), 0.0001);
    }

}

