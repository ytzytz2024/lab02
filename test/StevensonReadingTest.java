import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Objects;
import org.junit.Before;
import org.junit.Test;
import weather.StevensonReading;

/**
 * A JUnit test class for the StevensonReading class.
 */
public class StevensonReadingTest {
  
  private StevensonReading example;
  
  @Before
  public void setUp() {
    example = new StevensonReading(9, 2, 9, 4);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidDewPoint() {
    new StevensonReading(1, 2, 9, 4);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidWindSpeed() {
    new StevensonReading(9, 2, -1, 4);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidTotalRain() {
    new StevensonReading(9, 2, 9, -1);
  }
  
  @Test
  public void testTemperature() {
    assertEquals(9, example.getTemperature());
  }
  
  @Test
  public void testDewPoint() {
    assertEquals(2, example.getDewPoint());
  }

  @Test
  public void testWindSpeed() {
    assertEquals(9, example.getWindSpeed());
  }

  @Test
  public void testTotalRain() {
    assertEquals(4, example.getTotalRain());
  }

  @Test
  public void testRelativeHumidity() {
    assertEquals(61, example.getRelativeHumidity()); 
  }

  @Test
  public void testHeatIndex() {
    assertEquals(40, example.getHeatIndex());
  }
  
  @Test
  public void testWindChill() {
    assertEquals(7, example.getWindChill());
  }
  
  @Test
  public void testToString() {
    assertEquals("Reading: T = 9, D = 2, v = 9, rain = 4", example.toString());
  }
  
  @Test
  public void testEquals() {
    assertTrue(example.equals(example));
    assertTrue(example.equals(new StevensonReading(9, 2, 9, 4)));
    String b = "that";
    assertFalse(example.equals(b));
    assertFalse(new StevensonReading(9, 2, 9, 4).equals(new StevensonReading(9, 2, 9, 3)));
    assertFalse(new StevensonReading(9, 2, 9, 4).equals(new StevensonReading(9, 2, 8, 4)));
    assertFalse(new StevensonReading(9, 2, 9, 4).equals(new StevensonReading(9, 1, 9, 4)));
    assertFalse(new StevensonReading(9, 2, 9, 4).equals(new StevensonReading(8, 2, 9, 4)));
  }
  
  @Test
  public void testHashCode() {
    StevensonReading that = new StevensonReading(9, 2, 9, 4);
    int thatHashCode = Objects.hash(that.getTemperature(), that.getDewPoint(), 
        that.getWindSpeed(), that.getTotalRain());
    assertEquals(thatHashCode, example.hashCode());
  }
}
