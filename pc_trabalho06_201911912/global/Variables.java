/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 12/05/2021 17:32
 * Last update: 17/05/2021 16:26
 * Name: Variables.java
 * Function: Store the global variables
 *******************************************************************/

package global;

import java.util.concurrent.Semaphore;

public abstract class Variables {
  private static final int BUFFER_SIZE = 10;

  public static Semaphore semaphoreMutex = new Semaphore(1);
  public static Semaphore semaphoreEmpity = new Semaphore(BUFFER_SIZE);
  public static Semaphore semaphoreFull= new Semaphore(0);
}
