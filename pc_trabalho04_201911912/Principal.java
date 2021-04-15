/* ***************************************************************
* Autor: Alan Bonfim Santos
* Matricula: 201911912
* Inicio: 04/04/2021 17:39
* Ultima alteracao: 14/04/2021 18:35
* Nome: Principal.java
* Funcao: inicia a janela com o fxml
*************************************************************** */

import controle.ControladorPrincipal;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Principal extends Application{
  public static void main(String[] args){
    launch(args);
  }

  @Override
  public void start(Stage stagePrimario) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/princiapal_view.fxml"));

    //criando adicionando o controlador ao fxml
    ControladorPrincipal controlador = new ControladorPrincipal();
    loader.setController(controlador);

    Parent root = loader.load();

    Scene scene = new Scene(root);

    //manipulando o stage
    stagePrimario.setTitle("Problema dos Trens");
    stagePrimario.setOnCloseRequest(e -> controlador.fechar());

    stagePrimario.setScene(scene);
    stagePrimario.show();
  }//fim metodo start

}