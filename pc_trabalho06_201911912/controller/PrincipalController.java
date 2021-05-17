/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 12/05/2021 17:27
 * Last update: 17/05/2021 16:31
 * Name: PrincipalController.java
 * Function: class that has the methods to the fxml scene
 *******************************************************************/

package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import threads.*;
import view.AditionMenu;
import global.Variables;

public class PrincipalController implements Initializable{
  @FXML AnchorPane pane;
  @FXML VBox vBoxConsumers;
  @FXML VBox vBoxProducers;
  @FXML Label labelCount;

  private SuperThread[] threads = new SuperThread[10];
  private AditionMenu producerMenu;
  private AditionMenu consumerMenu;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    producerMenu = new AditionMenu(this, 0);
    consumerMenu = new AditionMenu(this, 5);

    consumerMenu.setLayoutX(305);

    //creates 5 producers and 5 consumers
    for(int i=0; i<5; i++)
      threads[i] = new Producer(vBoxProducers, true, this);
    for(int i=5; i<10; i++)
      threads[i] = new Consumer(vBoxConsumers, false, this);

    //adding the menus to the pane
    pane.getChildren().addAll(producerMenu, consumerMenu);
  }//end initialize

  public void startThread(int id){
    threads[id].start();
  }

  public void setThreadSpeed(int id, int speed){
    threads[id].setSpeed(speed);
  }

  //method that is called when the application is closed
  public void onClose(){
    for(SuperThread thread : threads){
      if(thread.isAlive())
        thread.interrupt();
    }
  }//end onClose

  public void updateCont(){
    this.labelCount.setText(String.valueOf(Variables.semaphoreFull.availablePermits()));
  }
}//end class
