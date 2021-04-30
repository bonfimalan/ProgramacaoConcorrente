/* ***************************************************************
* Autor: Alan Bonfim Santos
* Matricula: 201911912
* Inicio: 30/04/2021 15:30
* Ultima alteracao: 15/04/2021 17:39
* Nome: ControladorPrincipal.java
* Funcao: Controla as modificacoes no fxml e algumas acoes nas 
          threads
*************************************************************** */
package controle;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

public class ControladorMenu implements Initializable{
  @FXML ChoiceBox opcoes;

  @Override
  public void initialize(URL location, ResourceBundle resources){
    opcoes.getItems().addAll("Protocolo das bandeira", "Variavel de Travamento", "Estrita Alternancia", "Peterson");

  }
  
}
