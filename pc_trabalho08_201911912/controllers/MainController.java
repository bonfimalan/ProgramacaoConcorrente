/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 07/06/2021 19:04
 * Last update: 10/06/2021 22:20
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

  //speed labels
  @FXML private Label labelSpeedGray;
  @FXML private Label labelSpeedBrown;
  @FXML private Label labelSpeedPink;
  @FXML private Label labelSpeedBlue;
  @FXML private Label labelSpeedYellow;
  @FXML private Label labelSpeedBlack;
  @FXML private Label labelSpeedGreen;
  @FXML private Label labelSpeedRed;

  //works
  @FXML private ImageView work1;
  @FXML private ImageView work2;
  @FXML private ImageView work3;
  @FXML private ImageView work4;
  @FXML private ImageView work5;
  @FXML private ImageView work6;
  @FXML private ImageView work7;
  private ImageView works[];

  @FXML private CheckBox hideWorksCheckBox;

  //debbugging atributes
  @FXML private VBox vboxPositions;
  @FXML private Label xPosition;
  @FXML private Label yPosition;
  @FXML private CheckBox debbugingCheckBox;
  @FXML private AnchorPane paneTrack;
  @FXML private ImageView trackImageView;
  private SuperThread[] threads = new SuperThread[8];

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    threads[0] = new GrayCar(sliderGray, grayCar, labelSpeedGray);
    threads[1] = new BrownCar(sliderBrown, brownCar, labelSpeedBrown);
    threads[2] = new PinkCar(sliderPink, pinkCar, labelSpeedPink);
    threads[3] = new BlueCar(sliderBlue, blueCar, labelSpeedBlue);
    threads[4] = new BlackCar(sliderBlack, blackCar, labelSpeedBlack);
    threads[5] = new RedCar(sliderRed, redCar, labelSpeedRed);
    threads[6] = new YellowCar(sliderYellow, yellowCar, labelSpeedYellow);
    threads[7] = new GreenCar(sliderGreen, greenCar, labelSpeedGreen);

    for(SuperThread t : threads)
      t.start();

    ImageView[] worksReference = {work1, work2, work3, work4, work5,work6,work7};
    works = worksReference;
    

    checkBoxDebbuggingConfig();
    checkBoxWorksConfig();
  }//end initialize

  public void onClose(){
    for(SuperThread t : threads)
      t.interrupt();
  }//end onClose

  public void checkBoxWorksConfig(){
    hideWorksCheckBox.setCursor(Cursor.HAND);
    hideWorksCheckBox.setOnAction(event -> {
      if(hideWorksCheckBox.isSelected()){
        for(ImageView imageView : works)
          imageView.setVisible(false);
      }
      else{
        for(ImageView imageView : works)
          imageView.setVisible(true);
      }
    });
  }//end checkBoxWorksConfig

  //Debugging  tools ----------------------------------------------------------
  public void checkBoxDebbuggingConfig(){
    debbugingCheckBox.setCursor(Cursor.HAND);
    debbugingCheckBox.setOnAction( actionEvent -> {
      //check box is marked
      if(debbugingCheckBox.isSelected()){
        setMousePositionConfiguration();
        vboxPositions.setVisible(true);
        trackImageView.setImage(new Image("/resources/images/trackDebugging.png"));
        //change the cursor that appears when the mouse is over the anchor pane to a cross
        Image cursor = new Image("/resources/images/cursor.png", 15, 15, false, false);
        paneTrack.setCursor(new ImageCursor(cursor, 7.5, 7.5));
      }
      else{
        trackImageView.setImage(new Image("/resources/images/track.png"));
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
