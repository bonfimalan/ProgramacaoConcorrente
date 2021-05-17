/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 16/05/2021 18:06
 * Last update: 16/05/2021 19:39
 * Name: SuperThread.java
 * Function: the thread that holds all the moviments to the ninja and
 *           the additon of the tracks
 *******************************************************************/
package threads;

import components.Track;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public abstract class SuperThread extends Thread{
  protected ImageView ninja;
  protected int ninjaId;
  private int speed = 25;
  private Track track;

  public SuperThread(VBox vBox, boolean isProducer){
    track = new Track(isProducer);

    this.ninja = track.getNinja();

    if(isProducer){
      ninjaId = 1;
    }
    else{
      ninjaId = 2;
      track.setLayoutX(305);
    }

    track.setVisible(false);
    vBox.getChildren().add(track);
  }//end constructor

  @Override
  public void run(){
    //
  }

  public void activateTrack(){
    this.track.setVisible(true);
  }

  public void setSpeed(int speed){
    this.speed = speed;
  }

  public void rotation(int option) throws InterruptedException{
    if(option == 0){
      while(ninja.getRotate() < 180){
        Thread.sleep(1);
        Platform.runLater( () -> ninja.setRotate(ninja.getRotate() + 1) );
      }
      Platform.runLater( () -> ninja.setRotate(180) );
    }
    if(option == 1){
      while(ninja.getRotate() > 0){
        Thread.sleep(1);
        Platform.runLater( () -> ninja.setRotate(ninja.getRotate() -1) );
      }
      Platform.runLater( () -> ninja.setRotate(0) );
    }
  }//end rotation

  public void catchBox() throws InterruptedException{
    rotation(0);
    while(ninja.getLayoutX() > 16){
      Thread.sleep(speed);
      Platform.runLater( () -> ninja.setLayoutX(ninja.getLayoutX() - 1) );
    }
    
  }//end catchBox

  public void deliverBox() throws InterruptedException{
    rotation(1);
    while(ninja.getLayoutX() < 255){
      Thread.sleep(speed);
      Platform.runLater( () -> ninja.setLayoutX(ninja.getLayoutX() + 1) );
    }
  }//end deliverBox
}//end class
