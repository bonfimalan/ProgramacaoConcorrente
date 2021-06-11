/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 07/06/2021 21:41
 * Last update: 07/06/2021 19:20
 * Name: PinkCar.java
 * Function: thread with the pink car moviments
 *******************************************************************/

 package threads;

import global.Variables;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

public class PinkCar extends SuperThread{
  @Override
  public void run(){
    try {
      Variables.grayPink.acquire();//this part is because of the car initial position
    } catch (InterruptedException e1) { }

    while(true){
      try{
        //going down
        car.setRotate(180);
        goDown(40);
        Variables.grayPink.release();
        goDown(170);
        Variables.redPink.acquire();//try to enter the red-pink critic region
        goDown(190);

        //going to the left
        car.setRotate(270);
        goLeft(470);
        Variables.blackPink.acquire();//try to enter the black-pink critic region
        Variables.blackRedPink.acquire();//try to enter the black-red-pink critic region
        goLeft(430);
        Variables.redPink.release();
        Variables.blackRedPink.release();
        goLeft(360);
        Variables.brownPink.acquire();//try to enter the brown-pink critic region
        Variables.blackBrownPink.acquire();//try to enter the black-brown-pink critic region
        goLeft(340);

        //going up
        car.setRotate(0);
        goUp(150);
        Variables.blackPink.release();
        Variables.blackBrownPink.release();
        goUp(20);
        Variables.grayPink.acquire();//try to enter the gray-pink critic region
        Variables.grayBrownPink.acquire();//try to enter the gray-brown-pink critic region
        goUp(0);

        //going to the right
        car.setRotate(90);
        goRight(360);
        Variables.brownPink.release();
        Variables.grayBrownPink.release();

        goRight(580);
      }
      catch(InterruptedException e) { }
    }//end try
  }//end run
  
  public PinkCar(Slider sliderSpeed, ImageView car, Label labelSpeed) {
    super(sliderSpeed, car, labelSpeed);
  }
}
