import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import praktikum.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerParameterizedTests {

    Burger burger;
    int bunIndex;
    int[] ingredientIndex;
    Float price;
    List<Bun> buns = new ArrayList<>();
    List<Ingredient> ingredients = new ArrayList<>();

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

        Bun bun1 = Mockito.mock(Bun.class);
        when(bun1.getName()).thenReturn("black bun");
        when(bun1.getPrice()).thenReturn(100F);
        Bun bun2 = Mockito.mock(Bun.class);
        when(bun2.getName()).thenReturn("white bun");
        when(bun2.getPrice()).thenReturn(200F);
        Bun bun3 = Mockito.mock(Bun.class);
        when(bun3.getName()).thenReturn("red bun");
        when(bun3.getPrice()).thenReturn(300F);
        Ingredient ingredient1 = Mockito.mock(Ingredient.class);
        when(ingredient1.getName()).thenReturn("hot sauce");
        when(ingredient1.getPrice()).thenReturn(100F);
        when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        Ingredient ingredient2 = Mockito.mock(Ingredient.class);
        when(ingredient2.getName()).thenReturn("sour cream");
        when(ingredient2.getPrice()).thenReturn(200F);
        when(ingredient2.getType()).thenReturn(IngredientType.SAUCE);
        Ingredient ingredient3 = Mockito.mock(Ingredient.class);
        when(ingredient3.getName()).thenReturn("chili sauce");
        when(ingredient3.getPrice()).thenReturn(300F);
        when(ingredient3.getType()).thenReturn(IngredientType.SAUCE);
        Ingredient ingredient4 = Mockito.mock(Ingredient.class);
        when(ingredient4.getName()).thenReturn("cutlet");
        when(ingredient4.getPrice()).thenReturn(100F);
        when(ingredient4.getType()).thenReturn(IngredientType.FILLING);
        Ingredient ingredient5 = Mockito.mock(Ingredient.class);
        when(ingredient5.getName()).thenReturn("dinosaur");
        when(ingredient5.getPrice()).thenReturn(200F);
        when(ingredient4.getType()).thenReturn(IngredientType.FILLING);
        Ingredient ingredient6 = Mockito.mock(Ingredient.class);
        when(ingredient6.getName()).thenReturn("sausage");
        when(ingredient6.getPrice()).thenReturn(300F);
        when(ingredient4.getType()).thenReturn(IngredientType.FILLING);

        buns.add(bun1);
        buns.add(bun2);
        buns.add(bun3);

        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        ingredients.add(ingredient3);
        ingredients.add(ingredient4);
        ingredients.add(ingredient5);
        ingredients.add(ingredient6);


        burger.setBuns(buns.get(bunIndex));
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
