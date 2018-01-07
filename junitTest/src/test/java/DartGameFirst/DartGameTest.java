package DartGameFirst;

import org.junit.*;

import static com.sun.deploy.trace.Trace.flush;
import static org.junit.Assert.*;

public class DartGameTest {

    static private DartGame dart;

    @BeforeClass
    public static void setUpClass() {
        dart = new DartGame();
        assertNotNull(dart);
    }
    static int value, value2;
    @After
    public void update(){
        value = dart.getFirstShooterPoints();
        value2 = dart.getSecondShooterPoints();
    }

    @Test
    public void testGetFirstShooterPoints() {
        assertEquals(value, dart.getFirstShooterPoints());
    }

    @Test
    public void testGetSecondShooterPoints() {
        assertEquals(value2, dart.getSecondShooterPoints());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShootTestPointsIllegalArgumentException() {
        dart.shoot(200, 2, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShootTestMultiplierIllegalArgumentException() {
        dart.shoot(20, 4, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shootTestIllegalArgumentException() {
        dart.shoot(200, 3, 1);
    }

    @Test
    public void testShoot() {
        dart.shoot(20, 3, 1);
        assertEquals(441, dart.getFirstShooterPoints());
    }

    @Test
    public void testShoot1() {
        dart.shoot(20, 3, 1);
        assertEquals(381, dart.getFirstShooterPoints());
    }

    @Test
    public void testShoot2() {
        dart.shoot(20, 3, 1);
        assertEquals(321, dart.getFirstShooterPoints());
    }

    @Test
    public void testShoot3() {
        dart.shoot(20, 3, 1);
        assertEquals(261, dart.getFirstShooterPoints());
    }

    @Test
    public void testShoot4() {
        dart.shoot(20, 3, 1);
        assertEquals(201, dart.getFirstShooterPoints());
    }

    @Test
    public void testShoot5() {
        dart.shoot(20, 3, 1);
        assertEquals(141, dart.getFirstShooterPoints());
    }

    @Test
    public void testShoot6() {
        dart.shoot(20, 3, 1);
        assertEquals(81, dart.getFirstShooterPoints());
    }

    @Test
    public void testShoot7() {
        dart.shoot(15, 3, 1);
        assertEquals(36, dart.getFirstShooterPoints());
    }

    @Test
    public void testShoot8() {
        dart.shoot(18, 2, 1);
        assertEquals(0, dart.getFirstShooterPoints());
    }

}