import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.*;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTests {

    Burger burger;

    @Mock
    Bun bun1;
    Ingredient ingredient1;
    Ingredient ingredient2;


    @Before
    public void startup(){
        burger = new Burger();
    }

    @Test
    public void bunShouldBeSet(){
        burger.setBuns(bun1);
        assertThat(burger.bun, is(bun1));
    }

    @Test
    public void shouldAddIngredient(){
        burger.addIngredient(ingredient1);
        assertTrue(burger.ingredients.contains(ingredient1));
    }

    @Test
    public void shouldRemoveIngredient(){
        burger.addIngredient(ingredient1);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void shouldMoveIngredients(){
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(0,1);
        assertThat(burger.ingredients.get(0), is(ingredient1));
    }


}
