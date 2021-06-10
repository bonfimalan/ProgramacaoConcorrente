/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 08/06/2021 16:33
 * Last update:
 * Name: Varibles.java
 * Function: class with the global variables
 *******************************************************************/

package global;

import java.util.concurrent.Semaphore;

public abstract class Variables {
  //Semaphores to region wherer pass 2 cars
  public static Semaphore grayBrown = new Semaphore(1);
  public static Semaphore grayPink = new Semaphore(1);
  public static Semaphore grayRed = new Semaphore(1);
  public static Semaphore grayGreen = new Semaphore(1);
  public static Semaphore grayYellow = new Semaphore(1);
  public static Semaphore grayBlue = new Semaphore(1);

  public static Semaphore blackBrown = new Semaphore(1);
  public static Semaphore blackBlue = new Semaphore(1);
  public static Semaphore blackYellow = new Semaphore(1);
  public static Semaphore blackGreen = new Semaphore(1);
  public static Semaphore blackRed = new Semaphore(1);
  public static Semaphore blackPink = new Semaphore(1);

  public static Semaphore blueBrown = new Semaphore(1);
  public static Semaphore blueYellow = new Semaphore(1);

  public static Semaphore redPink = new Semaphore(1);
  public static Semaphore redGreen = new Semaphore(1);

  public static Semaphore brownPink = new Semaphore(1);
  public static Semaphore yellowGreen = new Semaphore(1);

  //semaphores to regions where pass 3 cars
  public static Semaphore grayBrownPink = new Semaphore(1);
  public static Semaphore grayYellowGreen = new Semaphore(1);

  public static Semaphore blackBrownBlue = new Semaphore(1);
  public static Semaphore blackBrownPink = new Semaphore(1);
  public static Semaphore blackRedPink = new Semaphore(1);
  public static Semaphore blackRedGreen = new Semaphore(1);
  public static Semaphore blackYellowGreen = new Semaphore(1);
  public static Semaphore blackYellowBlue = new Semaphore(1);
}
