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

import java.util.Arrays;

import static org.mockito.Mockito.times;

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
    public void priceWithTwoIngredientTest(){
        Mockito.when(bun.getPrice()).thenReturn(100F);
        Mockito.when(ingredient1.getPrice()).thenReturn(150F);
        Mockito.when(ingredient2.getPrice()).thenReturn(200F);

        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        Assert.assertEquals("Ошибочная сумма",550F, burger.getPrice(), 0.01);
    }

    @Test
    public void priceWithRemoveTest(){
        Mockito.when(bun.getPrice()).thenReturn(100F);
        Mockito.when(ingredient1.getPrice()).thenReturn(150F);

        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.removeIngredient(1);
        Assert.assertEquals("Ошибочная сумма",350F, burger.getPrice(), 0.01);
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

        String receiptBeforeMove = burger.getReceipt();
        System.out.println(receiptBeforeMove);
        String[] linesBeforeMove = receiptBeforeMove.split("\\r\\n");
        System.out.println(Arrays.toString(linesBeforeMove));
        Assert.assertEquals("Ингредиент не на том месте", "= sauce ingredient1 =", linesBeforeMove[1]);
        Assert.assertEquals("Ингредиент не на том месте","= filling ingredient2 =", linesBeforeMove[2]);

        burger.moveIngredient(1, 0);
        String receiptAfterMove = burger.getReceipt();
        String[] linesAfterMove = receiptAfterMove.split("\\r\\n");
        Assert.assertEquals("Ингредиент не на том месте", "= filling ingredient2 =", linesAfterMove[1]);
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

        Mockito.when(burger.getPrice()).thenReturn(700F);

        String lineSeparator = System.lineSeparator();
        String expectedReceipt = "(==== black bun ====)" + lineSeparator +
                "= sauce chili sauce =" + lineSeparator +
                "= filling dinosaur =" + lineSeparator +
                "(==== black bun ====)" + lineSeparator +
                lineSeparator +
                "Price: 700,000000" + lineSeparator;

        String actualReceipt = burger.getReceipt();

        Mockito.verify(bun, times(2)).getName();
        Mockito.verify(ingredient1, times(1)).getType();
        Mockito.verify(ingredient1, times(1)).getName();

        Assert.assertEquals("В рецепте ошибка", expectedReceipt, actualReceipt);
    }
}
