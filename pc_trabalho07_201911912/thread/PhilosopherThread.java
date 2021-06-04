/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 30/05/2021 13:36
 * Last update: 04/06/2021 13:20
 * Name: PhilosopherThread.java
 * Function: thread that does the actions of the philosopher
 *******************************************************************/

package thread;

import controle.MainController;
import global.Variables;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PhilosopherThread  extends Thread{
  private MainController controller;
  private ImageView fork;
  private ImageView actionCircle;
  private Slider thinkingSlider;
  private Slider eatingSlider;
  private int thinkingTime = 2000;
  private int eatingTime = 2000;
  private final int ID;
  private final int LEFT;
  private final int RIGHT;

  //objects to the mouse event
  private AnchorPane pane;
  private Stage stage;
  private ImageView paneImage;

  @Override
  public void run(){
    while(true){
      try{
        think();
        takeFork();
        eat();
        returnFork();
      }
      catch(InterruptedException e){ }
    }
  }//end run

  public PhilosopherThread(int id, ImageView fork, ImageView actionCircle, Slider thinkingSlider, Slider eatingSlider, MainController controller){
    this.controller = controller;
    this.ID = id;
    this.LEFT = (id-1+5) % 5;
    this.RIGHT = (id+1) % 5;
    this.fork = fork;
    this.actionCircle = actionCircle;
    this.thinkingSlider = thinkingSlider;
    this.eatingSlider = eatingSlider;

    //the method that configs the two sliders
    configSliderThinking();
    configSliderEating();

    //calls to the methods that configs the mouse event in the circle image and the window
    configImageShowCase();
    configMouseEvent();
  }//end constructor

  public void think() throws InterruptedException{
    System.out.println("Filosofo " + ID + ": to pensando to pensando");
    int cont = 1000;
    
    //yeah, maybe there's a lot of calls to the semaphore
    //acceess the critic region
    //the thinkingTime is a shared resource between the slider in the javafx thread and this thread
    Variables.alanMutex.acquire(); 
    while(cont<thinkingTime){
      Variables.alanMutex.release();
      sleep(1000); //it sleeps for 1 second
      cont+=1000;
      Variables.alanMutex.acquire();
    }
    Variables.alanMutex.release();
  }//end think

  //take the fork
  public void takeFork() throws InterruptedException{
    Variables.mutex.acquire();
    Variables.states[ID] = Variables.HUNGRY;
    hungryImage();//this changes the circle gif
    System.out.println("Filosofo " + ID + ": o pai ta com fome!!!!");
    test(ID);
    Variables.mutex.release();
    Variables.arraySemaphore[ID].acquire();
  }//end takeFork

  public void eat() throws InterruptedException{
    System.out.println("Filosofo " + ID + ": hmmmmm espaguetezinho");
    int cont = 1000;

    //acceess the critic region
    //the thinkingTime is a shared resource between the slider in the javafx thread and this thread
    Variables.alanMutex.acquire(); 
    while(cont<eatingTime){
      Variables.alanMutex.release();
      sleep(1000); //it sleeps for 1 second
      cont+=1000;
      Variables.alanMutex.acquire();
    }
    Variables.alanMutex.release();
  }//end eat

  public void returnFork() throws InterruptedException{
    Variables.mutex.acquire();
    Variables.states[ID] = Variables.THINKING;
    thinkingImage();//this changes the circle gif
    test(LEFT);
    test(RIGHT);
    //commands that changes the interface, it gives back the forks
    controller.getThread(ID).changeFork(); //now this philosophe don't have any fork
    controller.changeVisible(ID); //the fork is back to the table
    controller.changeVisible(RIGHT); //the fork is back to the table
    Variables.mutex.release();
  }//end returnFork

  public void test(int id) throws InterruptedException{
    int rightFork = (id +1)%5;
    int leftFork = (id-1+5)%5;
    if(Variables.states[id] == Variables.HUNGRY 
        && Variables.states[leftFork] != Variables.EATING 
        && Variables.states[rightFork] != Variables.EATING){
      
      //commands that changes the interface, it take the forks
      controller.changeVisible(id); //the left fork has the same id to the philosophe
      controller.changeVisible(rightFork);
      controller.getThread(id).changeFork(); //the philosophe takes the two forks

      Variables.states[id] = Variables.EATING;
      controller.getThread(id).eatingImage();//this changes the circle gif

      Variables.arraySemaphore[id].release();
    }
  }//and test

  public void configMouseEvent(){
    //show the stage in the mouse position
    actionCircle.setOnMouseEntered(mouseEvent -> {
      stage.setX(mouseEvent.getScreenX() + 5);
      stage.setY(mouseEvent.getScreenY() + 5);
      stage.show();
    });
    //move the stage with the mouse
    actionCircle.setOnMouseMoved(mouseEvent -> {
      stage.setX(mouseEvent.getScreenX() + 5);
      stage.setY(mouseEvent.getScreenY() + 5);
    });
    //close the stage
    actionCircle.setOnMouseExited(mouseEvent -> {
      stage.close();
    });
  }//end configMouseEvent

  //configs to the mouse event in the images
  public void configImageShowCase(){
    paneImage = new ImageView();
    pane = new AnchorPane();
    stage = new Stage();

    //when the circle around the philosopher changes, it changes the paneImage too
    actionCircle.imageProperty().addListener( (v, oldValue, newValue) -> {
        paneImage.setImage(newValue);
    });

    //configs to the pane
    pane.setPrefSize(200, 200);
    pane.getChildren().setAll(paneImage);

    //cofigs to the image size
    paneImage.setFitHeight(200);
    paneImage.setFitWidth(200);

    //adding a scene to the stage
    Scene scene = new Scene(pane);
    stage.setScene(scene);
    stage.initStyle(StageStyle.UNDECORATED);
  }//end configImageShowCase

  public void changeFork(){
    if(fork.isVisible()) fork.setVisible(false);
    else fork.setVisible(true);
  }//end chage fork

  public void thinkingImage(){
    actionCircle.setImage(new Image("/resources/images/thinking2.gif"));
  }

  public void hungryImage(){
    actionCircle.setImage(new Image("/resources/images/hungry.gif"));
  }

  public void eatingImage(){
    actionCircle.setImage(new Image("/resources/images/eating.gif"));
  }

  public int getIdThread(){
    return this.ID;
  }

  public int getLeft(){
    return this.LEFT;
  }

  public int getRight(){
    return this.RIGHT;
  }

  //configurate the thinking slider
  private void configSliderThinking() {
    thinkingSlider.valueProperty().addListener( (v, oldValue, newValue) ->{
      try{
        Variables.alanMutex.acquire();
        thinkingTime = newValue.intValue() * 1000;
        Variables.alanMutex.release();
      } catch(InterruptedException e){ }
    });
  }//end configSliderThinking

  
  //configurate the eating slider
  private void configSliderEating(){
    eatingSlider.valueProperty().addListener( (v, oldValue, newValue) ->{
      try{
        Variables.alanMutex.acquire();
        eatingTime = newValue.intValue() * 1000;
        Variables.alanMutex.release();
      }catch(InterruptedException e){ }
    });
  }//end configSliderEating
}
