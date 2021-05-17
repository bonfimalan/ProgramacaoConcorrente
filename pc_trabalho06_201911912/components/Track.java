/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 16/05/2021 18:07
 * Last update: 16/05/2021 18:33
 * Name: Track.java
 * Function: a view component that adds the track with the ninja
 *******************************************************************/
package components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Track extends AnchorPane{
  private ImageView ninja;

  public Track(boolean isProducer){
    super();
    super.setPrefSize(295, 50);

    Image track;

    //creating the ninja that trasports the boxes
    if(isProducer){
      ninja = new ImageView(new Image("/resources/images/ninja1.png", 30, 30, false, false));
      track = new Image("/resources/images/track.png");
    }
    else{
      ninja = new ImageView(new Image("/resources/images/ninja2.png", 30, 30, false, false));
      track = new Image("/resources/images/track2.png");
    }
    ninja.setLayoutX(100);
    ninja.setLayoutY(10);
    //adding the background image and the ninja
    super.getChildren().addAll(new ImageView(track), ninja);
  }//end constructor

  public ImageView getNinja(){
    return this.ninja;
  }
}
