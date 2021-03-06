/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 30/05/2021 12:36
 * Last update: 04/06/2021 13:03
 * Name: MainController.java
 * Function: controls the changes in the main view
 *******************************************************************/

package controle;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;

import global.Variables;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import thread.PhilosopherThread;

public class MainController implements Initializable{
  @FXML Slider sliderThinking0;
  @FXML Slider sliderThinking1;
  @FXML Slider sliderThinking2;
  @FXML Slider sliderThinking3;
  @FXML Slider sliderThinking4;

  @FXML Slider sliderEating0;
  @FXML Slider sliderEating1;
  @FXML Slider sliderEating2;
  @FXML Slider sliderEating3;
  @FXML Slider sliderEating4;
  
  @FXML ImageView fork0;
  @FXML ImageView fork1;
  @FXML ImageView fork2;
  @FXML ImageView fork3;
  @FXML ImageView fork4;
  
  @FXML ImageView philosopherForks0;
  @FXML ImageView philosopherForks1;
  @FXML ImageView philosopherForks2;
  @FXML ImageView philosopherForks3;
  @FXML ImageView philosopherForks4;

  @FXML ImageView circle0;
  @FXML ImageView circle1;
  @FXML ImageView circle2;
  @FXML ImageView circle3;
  @FXML ImageView circle4;


  private Slider[] slidersThinking;
  private Slider[] slidersEating;
  private ImageView[] forks;
  private ImageView[] philosopherForks;
  private ImageView[] circles;
  private PhilosopherThread[] threads = new PhilosopherThread[5];

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    //creating a array that has the sliders and pass the reference to the class atribute
    Slider[] slidersReference = {sliderThinking0, sliderThinking1, sliderThinking2, sliderThinking3, sliderThinking4};
    slidersThinking = slidersReference;

    Slider[] slidersReference2 = {sliderEating0, sliderEating1, sliderEating2, sliderEating3, sliderEating4};
    slidersEating = slidersReference2;

    //creating a array that has the forks and pass the reference to the class atribute
    ImageView[] forksReference = {fork0, fork1, fork2, fork3, fork4};
    forks = forksReference;

    ImageView[] philosopherForksReference = {philosopherForks0, philosopherForks1, philosopherForks2, philosopherForks3, philosopherForks4};
    philosopherForks = philosopherForksReference;

    ImageView[] circlesReference = {circle0, circle1, circle2, circle3, circle4};
    circles = circlesReference;
    instanceAndStartThreads();
  }//end initialize
  
  //method that creates the threads, starts the threads and create the semaphores
  public void instanceAndStartThreads(){
    for(int i=0; i<5; i++){
      threads[i] = new PhilosopherThread(i, philosopherForks[i], circles[i], slidersThinking[i], slidersEating[i], this);
      Variables.arraySemaphore[i] = new Semaphore(0);
    }

    for(PhilosopherThread thread : threads)
      thread.start();
  }//end instanceAndStartThreads

  public PhilosopherThread getThread(int id){
    return threads[id];
  }//end getThread

  //chage the visibility of the fork in the table
  public void changeVisible(int id){
    if(forks[id].isVisible()){
      forks[id].setVisible(false);
    } else{
      forks[id].setVisible(true);
    }
  }//end returnFork

  //method that is called in the main when the application is closed
  public void onClose(){
    for(PhilosopherThread thread : threads)
      thread.interrupt();
  }//end onClose
}
