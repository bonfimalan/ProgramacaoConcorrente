/* ***************************************************************
* Autor: Alan Bonfim Santos
* Matricula: 201911912
* Inicio: 04/05/2021 17:51
* Ultima alteracao: 05/05/2021 22:39
* Nome: MenuVelocidadeAdicao.java
* Funcao: Menu que possui os botoes de adicionar e sliders de velocidade
          do onibus
*************************************************************** */

package view;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import controle.ControladorPrincipal;

public class MenuVelocidadeAdicao extends VBox{
  //sliders de velocidade
  private Slider[] sliders;
  //botoes para adiconar onibus
  private Button[] botoes;
  private HBox hBoxControles;

  private ControladorPrincipal controlador;
  private int proximoIndiceThread;

  public MenuVelocidadeAdicao(String nomeOnibus, int proximoIndiceThread, ControladorPrincipal controlador, int numeroOnibus, int tamanhoSlider){
    this.controlador = controlador;
    this.proximoIndiceThread = proximoIndiceThread;

    botoes = new Button[numeroOnibus];
    sliders = new Slider[numeroOnibus];
    //inicializando os botoes e sliders
    int indiceBotao;
    for(int i=0; i<numeroOnibus; i++){
      sliders[i] = new Slider();
      sliders[i].setMaxWidth(tamanhoSlider);
      sliders[i].setMin(5);
      sliders[i].setMax(25);
      sliders[i].setValue(25);
      sliders[i].setBlockIncrement(5);
      sliders[i].setMinorTickCount(5);
      botoes[i] = new Button("ADD");
    }
    hBoxControles = new HBox(botoes);

    configurarBotoes(numeroOnibus);
    super.getChildren().addAll(new Label("Painel de controle onibus " + nomeOnibus), hBoxControles);
  }//fim MenuVelociade

  private void configurarBotoes(int numeroOnibus){
    for(int i=0; i<numeroOnibus; i++){
      final int POSICAO = i;
      botoes[POSICAO].setOnAction(actionEvent -> {
        //liga a thread de acordo com a proxima disponivel
        controlador.ligarThread(proximoIndiceThread);
        configuarVelocidade(proximoIndiceThread, POSICAO);
        proximoIndiceThread++;
        //remove o botao da hbox
        hBoxControles.getChildren().remove(botoes[POSICAO]);
        //adiciona o slider no lugar
        hBoxControles.getChildren().add(POSICAO, sliders[POSICAO]);
      });
    }
  }

  private void configuarVelocidade(int indiceThread, int POSICAO){
    sliders[POSICAO].setOnMouseClicked(event -> {
      controlador.setThreadVelocidade(indiceThread, (int) sliders[POSICAO].getValue());
    });
    sliders[POSICAO].setOnMouseExited(event -> {
      controlador.setThreadVelocidade(indiceThread, (int) sliders[POSICAO].getValue());
    });
  }
}
