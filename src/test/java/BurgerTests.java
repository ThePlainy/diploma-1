import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.*;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTests {

    Burger burger;
    Bun testBun;
    Ingredient testIngredient;

    @Spy
    Database database;

    @Before
    public void startup(){
        burger = new Burger();
        database = new Database();
    }

    @Test
    public void bunShouldBeSet(){
        List<Bun> buns = database.availableBuns();
        testBun = buns.get(0);
        burger.setBuns(testBun);
        System.out.println(testBun.name);
        assertThat(burger.bun, is(testBun));
    }

    @Test
    public void shouldAddIngredient(){
        List<Ingredient> ingredients = database.availableIngredients();
        testIngredient = ingredients.get(3);
        burger.addIngredient(testIngredient);
        assertTrue(burger.ingredients.contains(testIngredient));
    }

    @Test
    public void shouldRemoveIngredient(){
        List<Ingredient> ingredients = database.availableIngredients();
        testIngredient = ingredients.get(3);
        burger.addIngredient(testIngredient);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void shouldMoveIngredients(){
        List<Ingredient> ingredients = database.availableIngredients();
        testIngredient = ingredients.get(3);
        burger.addIngredient(testIngredient);
        testIngredient = ingredients.get(0);
        burger.addIngredient(testIngredient);
        burger.moveIngredient(0,1);
        assertThat(burger.ingredients.get(0), is(testIngredient));
    }


}
