/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 07/06/2021 19:22
 * Last update: 08/06/2021 22:46
 * Name: SuperThread.java
 * Function: the class that has the cars main moviments and other 
             in common things
 *******************************************************************/

package threads;

import javafx.application.Platform;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

public abstract class SuperThread extends Thread{
  protected Slider sliderSpeed;
  protected int speed = 25;
  protected ImageView car;
  protected int carPositonX;
  protected int carPositonY;

  @Override
  public void run(){
    //
  }

  public SuperThread(Slider sliderSpeed, ImageView car){
    this.sliderSpeed = sliderSpeed;
    this.car = car;
    configSlider();
  }

  public void configSlider() {
    sliderSpeed.valueProperty().addListener( (v, oldValue, newValue) -> {
      speed = newValue.intValue();
    });
  }
  
  protected void goRight(int stopPosition) throws InterruptedException{
    carPositonX = (int) car.getLayoutX();
    stopPosition-=20;//decrementing the image size, so it will stop in the right position
    while(carPositonX < stopPosition){
      sleep(speed);
      carPositonX++;
      Platform.runLater( () -> car.setLayoutX(carPositonX));
    }
  }

  protected void goLeft(int stopPosition) throws InterruptedException{
    carPositonX = (int) car.getLayoutX();
    while(carPositonX > stopPosition){
      sleep(speed);
      carPositonX--;
      Platform.runLater( () -> car.setLayoutX(carPositonX));
    }
  }

  protected void goUp(int stopPosition) throws InterruptedException{
    carPositonY = (int) car.getLayoutY();
    while(carPositonY > stopPosition){
      sleep(speed);
      carPositonY--;
      Platform.runLater( () -> car.setLayoutY(carPositonY));
    }
  }

  protected void goDown(int stopPosition) throws InterruptedException{
    carPositonY = (int) car.getLayoutY();
    stopPosition-=20;//decrementing the image size, so it will stop in the right position
    while(carPositonY < stopPosition){
      sleep(speed);
      carPositonY++;
      Platform.runLater( () -> car.setLayoutY(carPositonY));
    }
  }
}
