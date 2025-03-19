import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient1;

    @Mock
    private Ingredient ingredient2;

    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void priceTest(){
        Mockito.when(bun.getPrice()).thenReturn(100F);
        Mockito.when(ingredient1.getPrice()).thenReturn(200F);

        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        Assert.assertEquals("Ошибочная сумма",400F, burger.getPrice(), 0.01);
    }

    @Test
    public void priceWithoutIngredientTest(){
        Mockito.when(bun.getPrice()).thenReturn(100F);

        burger.setBuns(bun);
        Assert.assertEquals("Ошибочная сумма",200F, burger.getPrice(), 0.01);
    }

    @Test
    public void receiptWithAddIngredientTest(){
        Mockito.when(bun.getName()).thenReturn("Bun");
        Mockito.when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredient1.getName()).thenReturn("ingredient1");

        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        String receipt = burger.getReceipt();
        Assert.assertTrue("В рецепте нет ингредиента", receipt.contains("ingredient1"));
    }

    @Test
    public void receiptWithRemoveTest(){
        Mockito.when(bun.getName()).thenReturn("Bun");
        Mockito.when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredient1.getName()).thenReturn("ingredient1");
        Mockito.when(ingredient2.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(ingredient2.getName()).thenReturn("ingredient2");

        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.removeIngredient(1);
        String receiptRemove = burger.getReceipt();
        Assert.assertFalse("В рецепте не удален ингредиент", receiptRemove.contains("ingredient2"));
    }

    @Test
    public void receiptWithMoveTest(){
        Mockito.when(bun.getName()).thenReturn("Bun");
        Mockito.when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredient1.getName()).thenReturn("ingredient1");
        Mockito.when(ingredient2.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(ingredient2.getName()).thenReturn("ingredient2");

        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.moveIngredient(1, 0);
        String receiptAfterMove = burger.getReceipt();
        String[] linesAfterMove = receiptAfterMove.split("\\r\\n");
        Assert.assertEquals("Ингредиент не на том месте ", "= filling ingredient2 =", linesAfterMove[1]);
        Assert.assertEquals("Ингредиент не на том месте", "= sauce ingredient1 =", linesAfterMove[2]);
    }


    @Test
    public void testGetReceipt() {
        Mockito.when(bun.getName()).thenReturn("black bun");
        Mockito.when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredient1.getName()).thenReturn("chili sauce");
        Mockito.when(ingredient2.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(ingredient2.getName()).thenReturn("dinosaur");

        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        String lineSeparator = System.lineSeparator();
        String expectedReceipt = "(==== black bun ====)" + lineSeparator +
                "= sauce chili sauce =" + lineSeparator +
                "= filling dinosaur =" + lineSeparator +
                "(==== black bun ====)" + lineSeparator +
                lineSeparator +
                "Price: 700,000000" + lineSeparator;

        String actualReceipt = burger.getReceipt();
        Assert.assertEquals("В рецепте ошибка", expectedReceipt, actualReceipt);
    }
}
