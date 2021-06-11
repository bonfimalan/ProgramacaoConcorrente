/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 07/06/2021 21:41
 * Last update: 10/06/2021 01:09
 * Name: BlueCar.java
 * Function: thread with the gray car moviments
 *******************************************************************/

package threads;

import global.Variables;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

public class BlueCar extends SuperThread{
  @Override
  public void run(){
    try {
      Variables.blackBrownBlue.acquire();//this part is because of the car initial position
      Variables.blueBrown.acquire();
      Variables.blackBlue.acquire();
    } catch (InterruptedException e1) { }

    while(true){
      try{
        //going to the left
        car.setRotate(270);
        goLeft(230);
        Variables.blackBrownBlue.release();//exit the black-brown-blue critic region
        Variables.blackBlue.release();//exit the black-blue critic region
        goLeft(100);
        Variables.blueBrown.release();//exit the blue-brown critic region
        goLeft(20);
        Variables.grayBlue.acquire();//try to enter the gray-blue critic region
        goLeft(0);

        //going down
        car.setRotate(180);
        goDown(360);

        //going to the rigth
        car.setRotate(90);
        goRight(40);
        Variables.grayBlue.release();//exit the gray-blue critic region
        goRight(120);
        Variables.blueYellow.acquire();//try to enter the blue-yellow critic region
        goRight(230);
        //try to access several critic regions to prevents deadlock
        Variables.blueBrown.acquire();//try to enter the blue-brown critic region
        Variables.blackBrownBlue.acquire();//try to enter the black-brown-blue critic region
        Variables.blackBlue.acquire();//try to enter the black-blue critic region
        Variables.blackYellowBlue.acquire();//try to enter the black-blue-yellow critic region
        goRight(250);

        //going up
        car.setRotate(0);
        goUp(320);
        Variables.blackYellowBlue.release();//exit the black-blue-yellow critic region
        Variables.blueYellow.release();//exit the blue-yellow critic region
        goUp(190);
        //Variables.blueBrown.acquire();//try to enter the blue-brown critic region
        //Variables.blackBrownBlue.acquire();//try to enter the black-brown-blue critic region
        goUp(170);
      }
      catch(InterruptedException e) { }
    }//end try
  }//end run
  
  public BlueCar(Slider sliderSpeed, ImageView car, Label labelSpeed) {
    super(sliderSpeed, car, labelSpeed);
  }
  
}
