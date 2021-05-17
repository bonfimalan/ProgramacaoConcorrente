/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 16/05/2021 18:56
 * Last update: 16/05/2021 19:30
 * Name: AditionMenu.java
 * Function: add the menu with the option to add a thread and then 
 *           change its speed
 *******************************************************************/
package view;

import controller.PrincipalController;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

public class AditionMenu extends HBox{
  private Button[] buttons;
  private Slider[] sliders;
  private PrincipalController controller;
  private int nextID;

  public AditionMenu(PrincipalController controller, int initialID){
    super();
    super.setPrefSize(295, 75);

    nextID = initialID;

    buttons = new Button[5];
    sliders = new Slider[5];

    for(int i=0; i<5; i++){
      sliders[i] = new Slider();
      sliders[i].setMaxWidth(25);
      sliders[i].setMin(5);
      sliders[i].setMax(25);
      sliders[i].setValue(25);
      sliders[i].setBlockIncrement(5);
      sliders[i].setMinorTickCount(5);
      buttons[i] = new Button("ADD");
    }

    configButtons();

    this.controller = controller;
  
    super.getChildren().addAll(buttons);
  }

  private void configButtons(){
    for(int i=0; i<5; i++){
      final int POSICAO = i;
      buttons[POSICAO].setOnAction(actionEvent -> {
        //start the thread by the position
        controller.startThread(nextID);
        configSpeed(nextID, POSICAO);
        nextID++;
        //remove the button
        super.getChildren().remove(buttons[POSICAO]);
        //add the slider in the same place
        super.getChildren().add(POSICAO, sliders[POSICAO]);
      });
    }
  }

  private void configSpeed(int indiceThread, int POSICAO){
    sliders[POSICAO].setOnMouseClicked(event -> {
      controller.setThreadSpeed(indiceThread, (int) sliders[POSICAO].getValue());
    });
    sliders[POSICAO].setOnMouseExited(event -> {
      controller.setThreadSpeed(indiceThread, (int) sliders[POSICAO].getValue());
    });
  }
}
