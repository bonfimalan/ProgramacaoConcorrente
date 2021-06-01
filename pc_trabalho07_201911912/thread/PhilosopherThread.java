/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 30/05/2021 13:36
 * Last update: 01/06/2021 19:56
 * Name: PhilosopherThread.java
 * Function: thread that does the actions of the philosopher
 *******************************************************************/

package thread;

import controle.MainController;
import global.Variables;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PhilosopherThread  extends Thread{
  private MainController controller;
  private ImageView fork;
  private ImageView actionCircle;
  private Slider speedSlider;
  private int time = 2000;
  private final int ID;
  private final int LEFT;
  private final int RIGHT;

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

  public PhilosopherThread(int id, ImageView fork, ImageView actionCircle, Slider speedSlider, MainController controller){
    this.controller = controller;
    this.ID = id;
    this.LEFT = (id-1+5) % 5;
    this.RIGHT = (id+1) % 5;
    this.fork = fork;
    this.actionCircle = actionCircle;
    this.speedSlider = speedSlider;
    configSliderSpeed();
  }//end constructor

  public void think() throws InterruptedException{
    System.out.println("Filosofo " + ID + ": to pensando to pensando");
    sleep(time);
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
    sleep(time);
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
      controller.getThread(id).changeFork(); //the philosophe takes the two forks
      controller.changeVisible(id); //the left fork has the same id to the philosophe
      controller.changeVisible(rightFork);

      Variables.states[id] = Variables.EATING;
      controller.getThread(id).eatingImage();//this changes the circle gif

      Variables.arraySemaphore[id].release();
    }
  }//and test

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

  //configurate the speed slider
  private void configSliderSpeed(){
    speedSlider.setOnMouseClicked(mouseEvent ->{
      time = (int) speedSlider.getValue();
    });
    speedSlider.setOnMouseExited(mouseEvent -> {
      time = (int) speedSlider.getValue();
    });
  }//end configSliderSpeed
}
