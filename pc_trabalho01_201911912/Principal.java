
/********************************************************************
* Autor: Alan Bonfim Santos
* Inicio: 25/02/2021 15:20:20
* Ultima alteracao: 03/03/2021 23:47:25
* Nome: Jogo da Memoria
* Funcao: Um jogo da memoria simples com um total de 10 combinacoes
********************************************************************/
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import controles.PrincipalController;

public class Principal extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  public void start(Stage stagePrimario) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/principal_view.fxml"));

    //instanciando e cofigurando o construtor para o fxmdl
    PrincipalController controle = new PrincipalController();
    loader.setController(controle);

    Parent root = loader.load();

    Scene scene = new Scene(root, 1024, 600);
    //Manipulando o stage
    stagePrimario.setTitle("Jogo da Memoria");//Definindo o nome do app
    stagePrimario.getIcons().add(new Image("/recursos/imagens/icone.png"));//Definindo a imagem do app
    
    stagePrimario.setScene(scene);
    stagePrimario.show();
  }
}