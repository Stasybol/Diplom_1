import praktikum.IngredientType;
import org.junit.Assert;
import org.junit.Test;

import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

public class IngredientTypeTest {
    IngredientType[] ingredients = IngredientType.values();

    @Test
    public void enumTest(){
        Assert.assertEquals("Количество ингредиентов должно быть 2", 2, ingredients.length);
    }
}
