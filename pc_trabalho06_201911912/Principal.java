/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 12/06/2021 16:16
 * Last update:
 * Name: Principal.java
 * Function: 
 *******************************************************************/
import controller.PrincipalController;
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
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main_view.fxml"));
    PrincipalController controller = new PrincipalController();
    loader.setController(controller);

    Parent root = loader.load();

    Scene scene = new Scene(root);

    primaryStage.setScene(scene);
    primaryStage.show();
  }
}