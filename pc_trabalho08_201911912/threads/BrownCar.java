/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 07/06/2021 21:41
 * Last update:
 * Name: BrownCar.java
 * Function: thread with the brown car moviments
 *******************************************************************/

package threads;

import global.Variables;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

public class BrownCar extends SuperThread {
  @Override
  public void run(){
    try {
      Variables.grayBrown.acquire();//this part is because of the car initial position
    } catch (InterruptedException e1) { }

    while(true){
      try{
        //going down
        car.setRotate(180);
        goDown(40);
        Variables.grayBrown.release();
        goDown(170);
        Variables.blueBrown.acquire();//enter the blue-brown critic region
        goDown(190);

        //going to the right
        car.setRotate(90);
        goRight(230);
        Variables.blackBrown.acquire();
        Variables.blackBrownBlue.acquire();
        goRight(270);
        Variables.blueBrown.release();
        Variables.blackBrownBlue.release(); 
        goRight(340);
        Variables.brownPink.acquire();
        Variables.blackBrownPink.acquire();
        goRight(360);

        //going up
        car.setRotate(0);
        goUp(150);
        Variables.blackBrown.release();
        Variables.blackBrownPink.release();
        goUp(20);
        Variables.grayBrown.acquire();
        Variables.grayBrownPink.acquire();
        goUp(0);

        //going to the left
        car.setRotate(270);
        goLeft(320);
        Variables.grayBrownPink.release();
        Variables.brownPink.release();
        goLeft(120);
      }//end try
      catch(InterruptedException e){ }
    }//end while
  }//end run

  public BrownCar(Slider sliderSpeed, ImageView car) {
    super(sliderSpeed, car);
  }//end constructor
}
