import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Database;
import praktikum.Ingredient;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class BurgerParameterizedTests {

    Burger burger;
    Database database;
    int bunIndex;
    int[] ingredientIndex;
    Float price;

    public BurgerParameterizedTests(int bunIndex, int[] ingredientIndex, Float price){
        this.bunIndex=bunIndex;
        this.ingredientIndex=ingredientIndex;
        this.price=price;
    }

    @Parameterized.Parameters
    public static Object [][] getData(){
        return new Object[][]{
                {0, new int[]{3}, new Float(300)},
                {0, new int[]{3,0}, new Float(400)},
                {0, new int[]{0,3}, new Float(400)},
                {0, new int[]{}, new Float(200)},
        };
    }

    @Before
    public void startup(){

        MockitoAnnotations.initMocks(this);
        burger = new Burger();
        database = Mockito.spy(Database.class);

        List<Bun> buns = database.availableBuns();
        burger.setBuns(buns.get(bunIndex));
        List<Ingredient> ingredients = database.availableIngredients();
        for (int index : ingredientIndex ) {
            burger.addIngredient(ingredients.get(index));
        }

    }

    @Test
    public void shouldReturnPrice(){
        assertThat(burger.getPrice(), is(price));
    }

    @Test
    public void shouldReturnReceipt(){
        StringBuilder receipt = new StringBuilder(String.format("(==== %s ====)%n", burger.bun.getName()));
        for (Ingredient ingredient : burger.ingredients) {
            receipt.append(String.format("= %s %s =%n", ingredient.getType().toString().toLowerCase(),
                    ingredient.getName()));
        }
        receipt.append(String.format("(==== %s ====)%n", burger.bun.getName()));
        receipt.append(String.format("%nPrice: %f%n", price));

        assertThat(burger.getReceipt(), is(receipt.toString()));
    }
}
