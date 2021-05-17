/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 12/05/2021 17:26
 * Last update: 17/05/2021 16:41
 * Name: Consumer.java
 * Function: thread that has the actions of the consumer
 *******************************************************************/

package threads;

import javafx.scene.layout.VBox;
import javafx.application.Platform;
import javafx.scene.image.Image;

import java.util.concurrent.Semaphore;

import global.Variables;
import controller.PrincipalController;

public class Consumer extends SuperThread{
  private PrincipalController controller;

  public Consumer(VBox vBox, boolean isProducer, PrincipalController controller){
    super(vBox, isProducer);
    this.controller = controller;
  }

  @Override
  public void run(){
    super.activateTrack();
    try{
      while(true){
        super.catchBox();
        Variables.semaphoreFull.acquire();

        //enter the critical region
        Variables.semaphoreMutex.acquire();

        Platform.runLater( () -> {
          ninja.setImage(new Image("/resources/images/ninja" + ninjaId + "box.png", 40, 30, false, false));
          controller.updateCont();
        });
        
        //exit the critical region
        Variables.semaphoreMutex.release();

        Variables.semaphoreEmpity.release();

        super.deliverBox();
        Platform.runLater( () -> ninja.setImage(new Image("/resources/images/ninja" + ninjaId + ".png", 30, 30, false, false)));
      }
    }
    catch (InterruptedException e){ }
  }//end run

}
