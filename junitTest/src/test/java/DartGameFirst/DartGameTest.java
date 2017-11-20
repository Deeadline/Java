package DartGameFirst;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DartGameTest {
    private DartGame game = new DartGame();
    @Test
    public void shootTest(){
        game.shoot(2,3, 1);
        assertEquals(495, game.getFirstPoints());
    }
    @Test (expected = IllegalArgumentException.class)
    public void shootTestIllegalArgumentException(){
        game.shoot(200,3,1);
    }
}
