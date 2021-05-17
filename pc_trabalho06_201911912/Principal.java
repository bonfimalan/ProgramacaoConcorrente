/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 12/05/2021 16:16
 * Last update: 17/05/2021 17:02
 * Name: Principal.java
 * Function: behold! the main class that starts everything
 *******************************************************************/
import controller.PrincipalController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import threads.*;
import controller.*;

public class Principal extends Application{
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main_view.fxml"));
    PrincipalController controller = new PrincipalController();
    loader.setController(controller);

    Parent root = loader.load();

    Scene scene = new Scene(root);

    primaryStage.setTitle("Is that what ninjas do?");
    primaryStage.getIcons().add(new Image("/resources/images/box.png"));
    primaryStage.setOnCloseRequest(event -> {
      controller.onClose();
    });
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}