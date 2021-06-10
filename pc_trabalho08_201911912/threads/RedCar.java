/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 07/06/2021 21:41
 * Last update: 09/06/2021 23:41
 * Name: RedCar.java
 * Function: thread with the red car moviments
 *******************************************************************/

package threads;

import global.Variables;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

public class RedCar extends SuperThread{
  @Override
  public void run(){
    try {
      Variables.blackRed.acquire();//this part is because of the car initial position
      Variables.blackRedGreen.acquire();
      Variables.redGreen.acquire();
    } catch (InterruptedException e1) { }

    while(true){
      try{
        //going to the right
        car.setRotate(90);
        goRight(490);
        Variables.blackRed.release();//exit the black-red critic region
        Variables.blackRedGreen.release();//exit the black-red-green critic region
        goRight(600);
        Variables.redGreen.release();//exit the red-green critic region
        goRight(680);
        Variables.grayRed.acquire();//try to enter the gray-red critic region
        goRight(700);

        //going up
        car.setRotate(0);
        goUp(170);
        
        //going to the left
        car.setRotate(270);
        goLeft(660);
        Variables.grayRed.release();//exit the gray-red critic region
        goLeft(580);
        Variables.redPink.acquire();//try to enter the red-pink critic region
        goLeft(470);
        //try to access several critic regions to prevents deadlock
        Variables.redGreen.acquire();//try to enter the red-green critic region
        Variables.blackRedGreen.acquire();//try to enter the black-red-green critic region
        Variables.blackRed.acquire();//try to enter the black-red critic region
        Variables.blackRedPink.acquire();//try to enter the black-red-pink critic region
        goLeft(450);

        //going down
        car.setRotate(180);
        goDown(210);
        Variables.redPink.release();//exit the red-pink critic region
        Variables.blackRedPink.release();//exit the black-red-pink critic region
        goDown(340);
        //Variables.redGreen.acquire();//try to enter the red-green critic region
        //Variables.blackRedGreen.acquire();//try to enter the black-red-green critic region
        goDown(360);
      }
      catch(InterruptedException e) { }
    }//end try
  }//end run
  
  public RedCar(Slider sliderSpeed, ImageView car) {
    super(sliderSpeed, car);
  }
  
}
