package weather;

import java.util.Objects;

/**
 * A Stevenson reading is one specific weather reading. 
 * It takes temperature, dew point, wind speed, and total rain as inputs.
 */
public final class StevensonReading implements WeatherReading {
  private final double temperature;
  private final double dewPoint;
  private final double windSpeed;
  private final int totalRain;
  
  /**
   * Constructs a Stevenson reading in terms of 
   * its temperature, dew point, wind speed, and total rain.
   * 
   * @param temperature the air temperature in Celsius
   * @param dewPoint    the dew point temperature in Celsius
   * @param windSpeed   the wind speed in miles per hour
   * @param totalRain   the total rain received in the last 24 hours in millimeters
   */
  public StevensonReading(double temperature, double dewPoint, double windSpeed, int totalRain) {
    if ((temperature < dewPoint) || (windSpeed < 0) || (totalRain < 0)) {
      throw new IllegalArgumentException("Invalid arguments!");
    }
    this.temperature = temperature;
    this.dewPoint = dewPoint;
    this.windSpeed = windSpeed;
    this.totalRain = totalRain;
  }
  
  @Override
  public int getTemperature() {
    return (int) Math.round(this.temperature);
  }

  @Override
  public int getDewPoint() {
    return (int) Math.round(this.dewPoint);
  }

  @Override
  public int getWindSpeed() {
    return (int) Math.round(this.windSpeed);
  }

  @Override
  public int getTotalRain() {
    return this.totalRain;
  }

  @Override
  public int getRelativeHumidity() {
    double actualVaporPressure = 6.11 * Math.pow(10, (7.5 * this.dewPoint) 
        / (237.3 + this.dewPoint));
    double saturatedVaporPressure = 6.11 
        * Math.pow(10, (7.5 * this.temperature) / (237.3 + this.temperature));
    return (int) Math.round((100 * actualVaporPressure / saturatedVaporPressure));
  }

  @Override
  public int getHeatIndex() {
    double c1 = -8.78469475556; 
    double c2 = 1.61139411; 
    double c3 = 2.33854883889;
    double c4 = -0.14611605; 
    double c5 = -0.012308094; 
    double c6 = -0.0164248277778;
    double c7 = 0.002211732; 
    double c8 = 0.00072546; 
    double c9 = -0.000003582;
    double t = this.temperature; 
    double r = 100 * (6.11 * Math.pow(10, (7.5 * this.dewPoint) / (237.3 + this.dewPoint))) 
        / (6.11 * Math.pow(10, (7.5 * this.temperature) / (237.3 + this.temperature)));
    return (int) Math.round(c1 + c2 * t + c3 * r + c4 * t * r + c5 * t * t 
        + c6 * r * r + c7 * t * t * r + c8 * t * r * r + c9 * t * t * r * r);
  }

  @Override
  public int getWindChill() {
    double temInF = 9.0 / 5.0 * this.temperature + 32;
    double windChill = 35.74 + 0.6215 * temInF - 35.75 * Math.pow(this.windSpeed, 0.16) 
        + 0.4275 * temInF * Math.pow(this.windSpeed, 0.16);
    return (int) Math.round((windChill - 32) * (5.0 / 9.0));
  }
  
  @Override
  public String toString() {
    return String.format("Reading: T = %d, D = %d, v = %d, rain = %d", 
        this.getTemperature(), this.getDewPoint(), this.getWindSpeed(), this.getTotalRain());
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    } 
    if (!(o instanceof StevensonReading)) {
      return false;
    }
    StevensonReading that = (StevensonReading) o;
    return (this.temperature == that.temperature) && (this.dewPoint == that.dewPoint) 
        && (this.windSpeed == that.windSpeed) && (this.totalRain == that.totalRain);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(this.getTemperature(), this.getDewPoint(), 
        this.getWindSpeed(), this.getTotalRain());
  }
  /*
  public static void main(String arg[]) {
    StevensonReading a = new StevensonReading(9, 2, 9, 4);
    StevensonReading b = a;
    System.out.println(a.getRelativeHumidity());
    System.out.println(a.getHeatIndex());
    System.out.println(a.getWindChill());
    System.out.println(a.toString());
    if (a == b) {
      System.out.println("wrong");
    }
    if (a.equals(b)) {
      System.out.println("Right");
    }
    System.out.println(a.hashCode());
    System.out.println(b.hashCode());
   
  }
  */ 

  
}

