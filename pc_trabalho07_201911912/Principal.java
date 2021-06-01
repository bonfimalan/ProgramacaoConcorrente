/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 30/05/2021 12:34
 * Last update: 30/05/2021 12:43
 * Name: Principal.java
 * Function: main class, that starts the javafx application
 *******************************************************************/

import controle.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

 public class Principal extends Application{
   public static void main(String[] args) {
     launch(args);
   }

  @Override
  public void start(Stage stage) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main_view.fxml"));
    MainController controller = new MainController();
    loader.setController(controller);

    Parent root = loader.load();

    Scene scene = new Scene(root);

    stage.setOnCloseRequest(event -> {
      controller.onClose();
      System.exit(0);
    });
    stage.setScene(scene);
    stage.show();
  }
 }