import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HippodromeTest {
    @Test
    public void initWithFirstNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
    }

    @Test
    public void initWithFirstNullMessage() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void initWithEmptyList() {
        assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    public void initWithEmptyListMessage() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    public void getHorses() {
        ArrayList<Horse> list30 = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list30.add(new Horse("horse" + i, i + 1));
        }


        Hippodrome hippodrome = new Hippodrome(list30);

        assertEquals(list30, hippodrome.getHorses());
    }

    @Test
    public void move() {
        ArrayList<Horse> list50 = new ArrayList<>();
        Horse mockHorse = Mockito.mock(Horse.class);
        for (int i = 0; i < 50; i++) {
            list50.add(mockHorse);
        }

        Hippodrome hippodrome = new Hippodrome(list50);
        hippodrome.move();

        Mockito.verify(mockHorse, Mockito.times(50)).move();
    }


    @Test
    public void getWinner() {
        ArrayList<Horse> list50 = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list50.add(new Horse("horse" + i, i + 1, i + 1));
        }

        Hippodrome hippodrome = new Hippodrome(list50);

        assertEquals(list50.get(49), hippodrome.getWinner());
    }
}
