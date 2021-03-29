/* ***************************************************************
* Autor: Alan Bonfim Santos
* Matricula: 201911912
* Inicio: 24/03/2021 18:59
* Ultima alteracao: 24/03/2021 21:17
* Nome: Principal.java
* Funcao: inicia a GUI pelo metodo start
*************************************************************** */
import controles.PrincipalControlador;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Principal extends Application{
  public static void main(String args){
    launch(args);
  }

  @Override
  public void start(Stage stagePrimario) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/principal_view.fxml"));

    //instanciando e configurando o construtor para o fxml
    PrincipalControlador controle = new PrincipalControlador();
    loader.setController(controle);

    Parent root = loader.load();

    Scene scene = new Scene(root);
    //Manipulando o stage
    stagePrimario.setTitle("Arvore Genealogica");//Definindo o nome do app
    stagePrimario.getIcons().add(new Image("/recursos/imagens/arvoreView.png"));//Definindo a imagem do app
    stagePrimario.setResizable(false);
    
    stagePrimario.setScene(scene);
    stagePrimario.show();
  }

}