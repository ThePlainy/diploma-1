import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.*;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTests {

    Burger burger;

    @Mock
    Database database;
    List<Bun> buns;
    List<Ingredient> ingredients;

    @Before
    public void startup(){
        burger = new Burger();
        database = new Database();
    }

    @Test
    public void bunShouldBeSet(){
        buns = database.availableBuns();
        burger.setBuns(buns.get(0));
        assertThat(burger.bun, is(buns.get(0)));
    }

    @Test
    public void shouldAddIngredient(){
        ingredients = database.availableIngredients();
        burger.addIngredient(ingredients.get(3));
        assertTrue(burger.ingredients.contains(ingredients.get(3)));
    }

    @Test
    public void shouldRemoveIngredient(){
        ingredients = database.availableIngredients();
        burger.addIngredient(ingredients.get(3));
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void shouldMoveIngredients(){
        ingredients = database.availableIngredients();
        burger.addIngredient(ingredients.get(3));
        burger.addIngredient(ingredients.get(0));
        burger.moveIngredient(0,1);
        assertThat(burger.ingredients.get(0), is(ingredients.get(0)));
    }


}
