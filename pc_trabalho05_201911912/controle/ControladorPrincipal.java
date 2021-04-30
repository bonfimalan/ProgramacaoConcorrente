/* ***************************************************************
* Autor: Alan Bonfim Santos
* Matricula: 201911912
* Inicio: 04/04/2021 17:51
* Ultima alteracao: 15/04/2021 17:39
* Nome: ControladorPrincipal.java
* Funcao: Controla as modificacoes no fxml e algumas acoes nas 
          threads
*************************************************************** */
package controle;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import novas_threads.OnibusSuperThread;
import threads.ThreadOnibusAzul;
import threads.ThreadOnibusVermelho;

public class ControladorPrincipal implements Initializable{
  //imagens
  @FXML ImageView onibusAzul;
  @FXML ImageView onibusVermelho;
  @FXML ImageView bandeiraEntradaAzul1;
  @FXML ImageView bandeiraEntradaAzul2;
  @FXML ImageView bandeiraEntradaVermelho1;
  @FXML ImageView bandeiraEntradaVermelho2;

  //Text com as velocidades de cada onibus
  @FXML Text velocidadeOnibusAzul;
  @FXML Text velocidadeOnibusVermelho;

  @FXML AnchorPane painel;

  private ThreadOnibusAzul threadOnibusAzul;
  private ThreadOnibusVermelho threadOnibusVermelho;

  //adiciona 4 possiveis onibus
  private OnibusSuperThread[] threads = new OnibusSuperThread[4];

  private boolean tunelLiberado1 = true;
  private boolean tunelLiberado2 = true;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    bandeiraEntradaAzul1.setVisible(false);
    bandeiraEntradaAzul2.setVisible(false);
    bandeiraEntradaVermelho1.setVisible(false);
    bandeiraEntradaVermelho2.setVisible(false);

    //posicionando os onibus
    onibusAzul.setX(-54);
    onibusVermelho.setX(600);

    threadOnibusAzul = new ThreadOnibusAzul(this);
    threadOnibusVermelho = new ThreadOnibusVermelho(this);

    velocidadeOnibusAzul.setText("40km/h");
    velocidadeOnibusVermelho.setText("40km/h");

    threadOnibusAzul.start();
    threadOnibusVermelho.start();
  }//fim metodo initialize

  public void iniciar(String opcao){
    switch (opcao){
      case "Protocolo das bandeira":
        break;
      case "Variavel de Travamento":
        break;
      case "Estrita Alternancia":
        break;
      case "Peterson":
        break;
    }
  }

  //metodo que eh executado ao usuario fechar o programa
  public void fechar(){
    threadOnibusAzul.interrupt();
    threadOnibusVermelho.interrupt();
    System.exit(0);
  }//fim metodo fechar

  //============================================================================================

  //metodos que mudam a velocidade dos onibus
  @FXML
  public void aumentarVelocidadeAzul(ActionEvent evento){
    int velocidade = threadOnibusAzul.getVelocidade();
    velocidade -= 5;

    if(velocidade >= 5){
      threadOnibusAzul.setVelocidade(velocidade);
      velocidadeOnibusAzul.setText(Integer.toString(1000/velocidade) + "km/h");
    }
  }//fim metodo aumentarVelocidadeAzul

  @FXML
  public void diminuirVelocidadeAzul(ActionEvent evento){
    int velocidade = threadOnibusAzul.getVelocidade();
    velocidade += 5;

    if(velocidade <= 25){
      threadOnibusAzul.setVelocidade(velocidade);
      velocidadeOnibusAzul.setText(Integer.toString(1000/velocidade) + "km/h");
    }
  }//fim metodo aumentarVelocidadeAzul

  @FXML
  public void aumentarVelocidadeVermelho(ActionEvent evento){
    int velocidade = threadOnibusVermelho.getVelocidade();
    velocidade -= 5;

    if(velocidade >= 5){
      threadOnibusVermelho.setVelocidade(velocidade);
      velocidadeOnibusVermelho.setText(Integer.toString(1000/velocidade) + "km/h");
    }
  }//fim metodo aumentarVelocidadeVermelho

  @FXML
  public void diminuirVelocidadeVermelho(ActionEvent evento){
    int velocidade = threadOnibusVermelho.getVelocidade();
    velocidade += 5;

    if(velocidade <= 25){
      threadOnibusVermelho.setVelocidade(velocidade);
      velocidadeOnibusVermelho.setText(Integer.toString(1000/velocidade) + "km/h");
    }
  }//fim metodo diminuirVelocidadeVermelho

  //============================================================================================

  //get e set
  public void setVisivelBandeiraEntradaAzul1(boolean estado){
    bandeiraEntradaAzul1.setVisible(estado);
  }

  public void setVisivelBandeiraEntradaAzul2(boolean estado){
    bandeiraEntradaAzul2.setVisible(estado);
  }

  public void setVisivelBandeiraEntradaVermelho1(boolean estado){
    bandeiraEntradaVermelho1.setVisible(estado);
  }

  public void setVisivelBandeiraEntradaVermelho2(boolean estado){
    bandeiraEntradaVermelho2.setVisible(estado);
  }

  public ImageView getOnibusAzul(){
    return this.onibusAzul;
  }

  public ImageView getOnibusVermelho(){
    return this.onibusVermelho;
  }

  public boolean getTunelLiberado1(){
    return this.tunelLiberado1;
  }

  public void setTunelLiberado1(boolean valor){
    this.tunelLiberado1 = valor;
  }

  public boolean getTunelLiberado2(){
    return this.tunelLiberado2;
  }

  public void setTunelLiberado2(boolean valor){
    this.tunelLiberado2 = valor;
  }
}
