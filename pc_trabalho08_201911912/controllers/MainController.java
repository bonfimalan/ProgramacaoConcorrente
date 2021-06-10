/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 07/06/2021 19:04
 * Last update:
 * Name: MainController.java
 * Function: holds the atributes and methods that affect the scene
 *******************************************************************/

package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import threads.*;

public class MainController implements Initializable{
  //slider that controlls the speed for each car
  @FXML private Slider sliderGray;
  @FXML private Slider sliderBrown;
  @FXML private Slider sliderPink;
  @FXML private Slider sliderBlue;
  @FXML private Slider sliderBlack;
  @FXML private Slider sliderRed;
  @FXML private Slider sliderYellow;
  @FXML private Slider sliderGreen;

  //cars
  @FXML private ImageView grayCar;
  @FXML private ImageView brownCar;
  @FXML private ImageView pinkCar;
  @FXML private ImageView blueCar;
  @FXML private ImageView yellowCar;
  @FXML private ImageView blackCar;
  @FXML private ImageView greenCar;
  @FXML private ImageView redCar;

  //debbugging atributes
  @FXML private VBox vboxPositions;
  @FXML private Label xPosition;
  @FXML private Label yPosition;
  @FXML private CheckBox debbugingCheckBox;
  @FXML private AnchorPane paneTrack;

  private SuperThread[] threads = new SuperThread[8];

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    threads[0] = new GrayCar(sliderGray, grayCar);
    threads[1] = new BrownCar(sliderBrown, brownCar);
    threads[2] = new PinkCar(sliderPink, pinkCar);
    threads[3] = new BlueCar(sliderBlue, blueCar);
    threads[4] = new BlackCar(sliderBlack, blackCar);
    threads[5] = new RedCar(sliderRed, redCar);
    threads[6] = new YellowCar(sliderYellow, yellowCar);
    threads[7] = new GreenCar(sliderGreen, greenCar);

    threads[0].start();
    threads[1].start();
    threads[2].start();
    threads[3].start();
    threads[4].start();
    threads[5].start();
    threads[6].start();
    threads[7].start();

    checkBoxConfig();
  }//end initialize

  public void onClose(){
    for(SuperThread t : threads)
      t.interrupt();
  }//end onClose

  //Debugging  tools ----------------------------------------------------------
  public void checkBoxConfig(){
    debbugingCheckBox.setOnAction( actionEvent -> {
      //check box is marked
      if(debbugingCheckBox.isSelected()){
        setMousePositionConfiguration();
        vboxPositions.setVisible(true);
        //change the cursor that appears when the mouse is over the anchor pane to a cross
        Image cursor = new Image("/resources/images/cursor.png", 15, 15, false, false);
        paneTrack.setCursor(new ImageCursor(cursor, 7.5, 7.5));
      }
      else{
        paneTrack.setOnMouseMoved(null);
        paneTrack.setCursor(Cursor.DEFAULT);
        vboxPositions.setVisible(false);
      }
    });
  }//end checkBoxConfig

  //show the position of the anchor pane where the mouse is, it help calculate the trajectory
  public void setMousePositionConfiguration(){
    paneTrack.setOnMouseMoved( mouseEvent -> {
      xPosition.setText(String.valueOf((int) mouseEvent.getX()));
      yPosition.setText(String.valueOf((int) mouseEvent.getY()));
    });
  }//end setMousePositionConfiguration
  //---------------------------------------------------------------------------
}
