/* ***************************************************************
* Autor: Alan Bonfim Santos
* Matricula: 201911912
* Inicio: 30/04/2021 15:30
* Ultima alteracao: 30/04/2021 16:08
* Nome: ControladorMenu.java
* Funcao: Controlador para o menu de selecao de solucao
*************************************************************** */
package controle;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

public class ControladorMenu implements Initializable{
  @FXML ChoiceBox<String> opcoes;

  private ControladorPrincipal controlador;

  public ControladorMenu(ControladorPrincipal controlador){
    this.controlador = controlador;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources){
    opcoes.getItems().addAll("Protocolo das bandeira", "Variavel de Travamento", "Estrita Alternancia", "Peterson");
    opcoes.setValue("Protocolo das bandeira");
  }
  
  @FXML
  public void aplicar(ActionEvent e){
    controlador.iniciar(opcoes.getValue());
  }
}
