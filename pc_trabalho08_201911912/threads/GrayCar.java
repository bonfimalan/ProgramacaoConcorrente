/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 07/06/2021 21:00
 * Last update: 09/06/2021 21:38
 * Name: GrayCar.java
 * Function: thread with the gray car moviments
 *******************************************************************/

package threads;

import global.Variables;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

public class GrayCar extends SuperThread{
  @Override
  public void run(){
    while(true){
      try{//in regions that has tree cars I have to acquire the interction first and then release the other critic region
        //going to the right
        car.setRotate(90);
        goRight(120);
        Variables.grayBrown.acquire();//try to enter the gray-brown critic region
        goRight(340);
        Variables.grayPink.acquire();//try to enter the gray-prink critic region
        Variables.grayBrownPink.acquire();//try to enter the gray-brown-pink critic region
        goRight(380);
        Variables.grayBrown.release();//exit the gray-brown critic region
        Variables.grayBrownPink.release();//exit the gray-brown-pink critic region
        goRight(600);
        Variables.grayPink.release();//exit the gray-pink critic region
        goRight(700);

        //going down
        car.setRotate(180);
        goDown(170);
        Variables.grayRed.acquire();//try to enter the gray-red critic region
        goDown(380);
        Variables.grayRed.release();//exit the gray-red critic region
        goDown(530);

        //going to the left
        car.setRotate(270);
        goLeft(580);
        //try to access all the tree critic region
        //because it can cause deadlock in those three cars
        //so all the tree are one critic region to this car
        Variables.grayGreen.acquire();//try to enter the gray-green critic region
        Variables.grayYellow.acquire();//try to enter the gray-yellow critic region
        Variables.grayYellowGreen.acquire();//try to enter the gray-yellow-green critic region
        goLeft(360);
        goLeft(320);
        Variables.grayGreen.release();//exit the gray-green critic region
        Variables.grayYellowGreen.release();//exit the gray-yellow-green critic region
        goLeft(100);
        Variables.grayYellow.release();//exit the gray-yellow critic region
        goLeft(0);

        //going up
        car.setRotate(0);
        goUp(360);
        Variables.grayBlue.acquire();//try to enter the gray-blue critic region
        goUp(150);
        Variables.grayBlue.release();//exit the gray-blue critic region
        goUp(0);
      }//end try
      catch(InterruptedException e) { }
    }//end while
  }//end run

  public GrayCar(Slider sliderSpeed, ImageView car) {
    super(sliderSpeed, car);
  }//end constructor
}//end GrayCar class
