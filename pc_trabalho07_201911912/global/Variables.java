/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 30/05/2021 17:06
 * Last update: 01/06/2021 15:45
 * Name: Variables.java
 * Function: global variables
 *******************************************************************/

package global;

import java.util.concurrent.Semaphore;

public class Variables {
  public static Semaphore mutex = new Semaphore(1);
  public static Semaphore[] arraySemaphore = new Semaphore[5];
  public static int[] states = new int[5];

  public final static int THINKING = 0;
  public final static int HUNGRY = 1;
  public final static int EATING = 2;
}
