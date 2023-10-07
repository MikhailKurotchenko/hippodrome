import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @Test
    @Timeout(value = 22)
    public void main() throws Exception {
            Main.main(new String[] {"1"});
        }
}
