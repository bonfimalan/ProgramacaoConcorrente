/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 07/06/2021 21:41
 * Last update: 09/06/2021 23:39
 * Name: GreenCar.java
 * Function: thread with the green car moviments
 *******************************************************************/

package threads;

import global.Variables;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

public class GreenCar extends SuperThread{
  @Override
  public void run(){
    try {
      Variables.grayGreen.acquire();//this part is because of the car initial position
    } catch (InterruptedException e1) { }

    while(true){
      try{
        //going up
        car.setRotate(0);
        goUp(490);
        Variables.grayGreen.release();//exit the gray-green critic region
        goUp(360);
        Variables.redGreen.acquire();//try to enter the red-green critic region
        goUp(340);

        //going to the left
        car.setRotate(270);
        goLeft(470);
        //try to access several critic regions to prevents deadlock
        Variables.yellowGreen.acquire();//try to enter the yellow-green critic region
        Variables.blackYellowGreen.acquire();//try to enter the black-yellow-green critic region
        Variables.blackGreen.acquire();//try to enter the black-green critic region
        Variables.blackRedGreen.acquire();//try to enter the black-red-green critic region
        goLeft(430);
        Variables.redGreen.release();//exit the red-green critic region
        Variables.blackRedGreen.release();//exit the black-red-green critic region
        goLeft(360);
        //Variables.yellowGreen.acquire();//try to enter the yellow-green critic region
        //Variables.blackYellowGreen.acquire();//try to enter the black-yellow-green critic region
        goLeft(340);

        //going down
        car.setRotate(180);
        goDown(380);
        Variables.blackGreen.release();//exit the black-green critic region
        Variables.blackYellowGreen.release();//exit the black-yellow-green critic region
        goDown(510);
        Variables.grayGreen.acquire();//try to enter the gray-green critic region
        Variables.grayYellowGreen.acquire();//try to enter the gray-yellow-green critic region
        goDown(530);

        //goinf to the right
        car.setRotate(90);
        goRight(380);
        Variables.yellowGreen.release();//exit the yellow-green critic region
        Variables.grayYellowGreen.release();//exit the gray-yellow-green critic region
        goRight(580);
      }
      catch(InterruptedException e) { }
    }//end try
  }//end run
  
  public GreenCar(Slider sliderSpeed, ImageView car) {
    super(sliderSpeed, car);
    //TODO Auto-generated constructor stub
  }
}