/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 12/05/2021 17:26
 * Last update: 17/05/2021 17:15
 * Name: Producer.java
 * Function: thread that has the actions of the producer
 *******************************************************************/

package threads;

import javafx.application.Platform;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;

import java.util.concurrent.Semaphore;

import global.Variables;
import controller.PrincipalController;

public class Producer extends SuperThread{
  private PrincipalController controller;

  public Producer(VBox vBox, boolean isProducer, PrincipalController controller){
    super(vBox, isProducer);
    this.controller = controller;
  }

  @Override
  public void run(){
    super.activateTrack();
    try{
      while(true){
        super.catchBox();
        Platform.runLater( () -> ninja.setImage(new Image("/resources/images/ninja" + ninjaId + "box.png", 40, 30, false, false)));
        super.deliverBox();
        Variables.semaphoreEmpity.acquire();

        //enter the critical region
        Variables.semaphoreMutex.acquire();

        Platform.runLater( () -> {
          ninja.setImage(new Image("/resources/images/ninja" + ninjaId + ".png", 30, 30, false, false));
          controller.updateCont();
        });

        //exit the critical region
        Variables.semaphoreMutex.release();

        Variables.semaphoreFull.release();
      }
    }
    catch (InterruptedException e){ }
  }//end run

  
}//end class Producer