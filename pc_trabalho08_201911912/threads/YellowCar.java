/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 07/06/2021 21:41
 * Last update: 09/06/2021 23:34
 * Name: YellowCar.java
 * Function: thread with the yellow car moviments
 *******************************************************************/

 package threads;

import global.Variables;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

public class YellowCar extends SuperThread{
  @Override
  public void run(){
    try {
      Variables.blueYellow.acquire();//this part is because of the car initial position
    } catch (InterruptedException e1) { }


    while(true){
      try{
        //going down
        car.setRotate(180);
        goDown(380);
        Variables.blueYellow.release();//exit the blue-yellow critic region
        goDown(510);
        Variables.grayYellow.acquire();//try to enter the gray-yellow critic region
        goDown(530);

        //going to the right
        car.setRotate(90);
        goRight(340);
        Variables.yellowGreen.acquire();//try to enter the yellow-green critic region
        Variables.grayYellowGreen.acquire();//try to enter the gray-yellow-green critic region
        goRight(360);

        //going up
        car.setRotate(0);
        goUp(490);
        Variables.grayYellow.release();//exit the gray-yellow critic region
        Variables.grayYellowGreen.release();//exit the gray-yellow-green critic region
        goUp(360);
        //try to access several critic regions to prevents deadlock
        Variables.blueYellow.acquire();//try to enter the blue-yellow critic region
        Variables.blackYellowBlue.acquire();//try to enter the black-yellow-blue critic region
        Variables.blackYellow.acquire();//try to enter the black-yellow-green critic region
        Variables.blackYellowGreen.acquire();//try to enter the black-yellow-green critic region
        goUp(340);

        //going to the left
        car.setRotate(270);
        goLeft(320);
        Variables.blackYellowGreen.release();//exit the black-yellow-green critic region
        Variables.yellowGreen.release();//exit the yellow-green critic region
        goLeft(250);
        //Variables.blueYellow.acquire();//try to enter the blue-yellow critic region
        //Variables.blackYellowBlue.acquire();//try to enter the black-yellow-blue critic region
        Variables.blackYellow.release();//exit the black-yellow critic region
        goLeft(210);
        Variables.blackYellowBlue.release();//exit the black-yellow-blue critic region
        goLeft(120);
      }
      catch(InterruptedException e) { }
    }//end try
  }//end run

  public YellowCar(Slider sliderSpeed, ImageView car) {
    super(sliderSpeed, car);
  }
  
}
