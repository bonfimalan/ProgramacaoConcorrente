/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 07/06/2021 19:00
 * Last update: 10/06/2021 01:00
 * Name: Principal.java
 * Function: the main class that loads the fxml to the stage and shows it
 *******************************************************************/

import controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

 public class Principal extends Application{
   public static void main(String[] args) {
     launch(args);
   }

  @Override
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/main_view.fxml"));
    MainController controller = new MainController();
    loader.setController(controller);
    
    Parent root = loader.load();

    Scene scene = new Scene(root, 850, 530);
    
    primaryStage.setOnCloseRequest(event ->{
      controller.onClose();
      System.exit(0);
    });
    primaryStage.setTitle("Alan the bald in the Alanverso - traffic edition");
    primaryStage.getIcons().add(new Image("/resources/images/alan.png"));
    primaryStage.setResizable(false);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
 }