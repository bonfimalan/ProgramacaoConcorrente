/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 07/06/2021 21:41
 * Last update: 09/06/2021 23:00
 * Name: BlackCar.java
 * Function: thread with the black car moviments
 *******************************************************************/

package threads;

import global.Variables;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

public class BlackCar extends SuperThread{
  @Override
  public void run(){
    try {
      Variables.blackYellowBlue.acquire();//this part is because of the car initial position
      Variables.blackBlue.acquire();
      Variables.blackYellow.acquire();
    } catch (InterruptedException e1) { }

    while(true){
      try{
        //going to the right
        car.setRotate(90);
        goRight(270);
        Variables.blackYellowBlue.release();//exit the black-yellow-green critic region
        Variables.blackBlue.release();//exit the black-blue critic region
        goRight(340);
        Variables.blackGreen.acquire();//try to enter the black-green critic region
        Variables.blackYellowGreen.acquire();//try to enter the black-yellow-green critic region
        goRight(380);
        Variables.blackYellowGreen.release();//exit the black-yellow-green critic region
        Variables.blackYellow.release();//exit the black-yellow critic region
        goRight(450);
        Variables.blackRed.acquire();//try to enter the black-red critic region
        Variables.blackRedGreen.acquire();//try to enter the black-red-green critic region
        goRight(470);

        //going up
        car.setRotate(0);
        goUp(320);
        Variables.blackRedGreen.release();//exit the black-red-green critic region
        Variables.blackGreen.release();//exit the black-green critic region
        goUp(190);
        Variables.blackPink.acquire();//try to enter the black-pink critic region
        Variables.blackRedPink.acquire();//try to enter the black-red-pink critic region
        goUp(170);

        //going to the left
        car.setRotate(270);
        goLeft(420);
        Variables.blackRed.release();//exit the black-red critic region
        Variables.blackRedPink.release();//exit the black-red-pink critic region
        goLeft(360);
        Variables.blackBrown.acquire();//try to enter the black-brown critic region
        Variables.blackBrownPink.acquire();//try to enter the black-brown-pink critic region
        goLeft(320);
        Variables.blackBrownPink.release();//exit the black-brown-pink critic region
        Variables.blackPink.release();//exit the black-pink critic region
        goLeft(250);
        Variables.blackBlue.acquire();//try to enter the black-blue critic region
        Variables.blackBrownBlue.acquire();//try to enter the black-brown-blue critic region
        goLeft(230);

        //going down
        car.setRotate(180);
        goDown(210);
        Variables.blackBrownBlue.release();//exit the black-brown-blue critic region
        Variables.blackBrown.release();//exit the black-brown critic region
        goDown(340);
        Variables.blackYellow.acquire();//try to enter the black-yellow critic region
        Variables.blackYellowBlue.acquire();//try to enter the black-yellow-blue critic region
        goDown(360);
      }
      catch(InterruptedException e) { }
    }//end try
  }//end run
  
  public BlackCar(Slider sliderSpeed, ImageView car, Label labelSpeed) {
    super(sliderSpeed, car, labelSpeed);
  }
  
}
