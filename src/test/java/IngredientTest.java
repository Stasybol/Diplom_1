import praktikum.Ingredient;
import praktikum.IngredientType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTest {
    private IngredientType type;
    private String name;
    private float price;

    private Ingredient ingredient;

    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "Тип ингридиента, название, цена: {0} {1} {2}")
    public static Object[][] setData() {
        return new Object[][]{
                {IngredientType.SAUCE, "hot sauce", 100F},
                {IngredientType.FILLING, "dinosaur", 200.0F},
                {IngredientType.SAUCE, "chili sauce", 300.00F}
        };
    }

    @Before
    public void setUp() {
        ingredient = new Ingredient(type, name, price);
    }

    @Test
    public void getTypeTest(){
        assertEquals("Тип ингредиента не совпадает", type, ingredient.getType());
    }

    @Test
    public void getNameTest(){
        assertEquals("Название ингредиента не совпадает", name, ingredient.getName());
    }

    @Test
    public void getPriceTest(){
        assertEquals("Цена ингредиента не совпадает", price, ingredient.getPrice(), 0.01);
    }

}

