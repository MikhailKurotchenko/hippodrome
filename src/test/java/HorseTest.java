import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class HorseTest {

    @Test
    public void initWithFirstNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 5));
    }

    @Test
    public void initWithFirstNullMessage() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 5));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "'\s', 9",
            "'\t', 5",
            "'', 8"
    })
    public void initWithFirstSpaces(String name, double speed) {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse(name, speed));
    }


    @ParameterizedTest
    @CsvSource({
            "'\s', 9",
            "'\t', 5",
            "'', 8"
    })
    public void initWithFirstSpacesMessage(String name, double speed) {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(name, speed));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }


    @Test
    public void initWithSecondNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse("null", -6));
    }


    @Test
    public void initWithSecondNegativeMessage() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("null", -6));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void initWithThirdNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse("null", 6, -8));
    }


    @Test
    public void initWithThirdNegativeMessage() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("null", 6, -8));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void getName() {
        Horse horse = new Horse("horse", 8);
        assertEquals("horse", horse.getName());
    }

    @Test
    public void getSpeed() {
        Horse horse = new Horse("horse", 8);
        assertEquals(8, horse.getSpeed());
    }

    @Test
    public void getDistance() {
        Horse horse = new Horse("horse", 8, 9);
        assertEquals(9, horse.getDistance());
    }

    @Test
    public void getDistanceIsNull() {
        Horse horse = new Horse("horse", 8);
        assertEquals(0, horse.getDistance());
    }

    @Test
    public void moveParameters() {
        try (MockedStatic<Horse> horse = Mockito.mockStatic(Horse.class)) {
            Horse horse1 = new Horse("dfg", 5);
            horse1.move();
            horse.verify(() -> {
                Horse.getRandomDouble(0.2, 0.9);
            });
        }
    }


    @ParameterizedTest
    @CsvSource({
            "0.2, 0.9",
    }
    )
    public void moveWithDestination(double min, double max) {
        try (MockedStatic<Horse> horse = Mockito.mockStatic(Horse.class)) {
            horse.when(() -> Horse.getRandomDouble(min, max)).thenReturn(8.0);
            Horse horse1 = new Horse("dfg", 5);
            horse1.move();
            assertEquals(40.0, horse1.getDistance());
        }
    }

}
