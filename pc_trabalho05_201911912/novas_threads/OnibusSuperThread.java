/* ***************************************************************
* Autor: Alan Bonfim Santos
* Matricula: 201911912
* Inicio: 30/04/2021 14:40
* Ultima alteracao: 14/04/2021 18:17
* Nome: OnibusSuperThread.java
* Funcao: super classe que possui os metodos de movimento base 
*         de ambos os onibus
*************************************************************** */
package novas_threads;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public abstract class OnibusSuperThread extends Thread{
  protected int velocidade;
  protected ImageView onibus;
  protected Text velocidadeText;

  //esta variavel garante uma forma de desativar as threads
  protected boolean ligado = true;

  //1 = indo para a direita, 1 = indo para a esquerda
  protected final int DIRECAO;

  protected int posicaoX;
  protected int posicaoY;
  protected int rotacao = 0;

  public OnibusSuperThread(int direcao, AnchorPane painel, Text velocidadeText){
    this.DIRECAO = direcao;
    this.velocidadeText = velocidadeText;
    //determina se sera um onibus azul(indo para a direita) ou um onibus vermelho(indo para a esquerda)
    if(DIRECAO == 1){
      posicaoX = -58;
      posicaoY = 216;
      onibus.setImage(new Image("/recursos/imagens/onibusAzul.png"));
      onibus.setLayoutX(posicaoX);
      onibus.setLayoutY(posicaoY);
      Platform.runLater( () -> painel.getChildren().add(onibus));
    }
    else{
      posicaoX = 600;
      posicaoY = 254;
      onibus.setImage(new Image("/recursos/imagens/onibusVermelho.png"));
      onibus.setLayoutX(posicaoX);
      onibus.setLayoutY(posicaoY);
      Platform.runLater( () -> painel.getChildren().add(onibus));
    }
  }

  @Override
  public void run(){
    //metodo run
  }

  //"desliga" a thread
  public void desligar(){
    ligado = false;
  }

  //-----------------------------------------------------------------------------
  //metodos para modificar as velocidades
  public void diminuirVelocidade(){
    if(velocidade < 25){
      velocidade +=5;
      velocidadeText.setText(Integer.toString(1000/velocidade) + "km/h");
    }
  }

  public void aumentarVelocidade(){
    if(velocidade > 5){
      velocidade -=5;
      velocidadeText.setText(Integer.toString(1000/velocidade) + "km/h");
    }
  }
  //-----------------------------------------------------------------------------

  protected void moverPeloTunel(int posicaoParada) throws InterruptedException{
    moverAmbosEixosDescida(posicaoParada);
    posicaoParada+=(100 * DIRECAO);
    moverEixoX(posicaoParada);
    posicaoParada+=(20 * DIRECAO);
    moverAmbosEixosSubida(posicaoParada);
  }//fim metodo moverPeloTunel

  protected void moverEixoX(int posicaoParada) throws InterruptedException{
    while(posicaoX != posicaoParada && ligado){
      posicaoX += DIRECAO;
      Thread.sleep(velocidade);
      Platform.runLater( () -> onibus.setLayoutX(posicaoX));
    }
  }//fim metodo moverEixoX

  protected void moverAmbosEixosDescida(int posicaoParada) throws InterruptedException{
    //nesse while o onibus esta rotacionando para baixo enquanto move nos eixos x e y
    while(posicaoX != posicaoParada-10 && ligado){
      Thread.sleep(velocidade);
      Platform.runLater( () ->{ 
        onibus.setLayoutX(posicaoX);
        onibus.setLayoutY(posicaoY);
        onibus.setRotate(rotacao);
      });
      rotacao+=3;
      posicaoX+=DIRECAO;
      posicaoY++;
    }//fim while

    //nesse while o onibus esta rotacionando para cima enquanto move nos eixos x e y
    while(posicaoX != posicaoParada && ligado){
      Thread.sleep(velocidade);
      Platform.runLater( () ->{ 
        onibus.setLayoutX(posicaoX);
        onibus.setLayoutY(posicaoY);
        onibus.setRotate(rotacao);
      });
      rotacao-=3;
      posicaoX+=DIRECAO;
      posicaoY++;
    }//fim while

    rotacao = 0;
    Platform.runLater( () -> onibus.setRotate(0));
  }//fim metodo moverAmbosEixosDescida

  protected void moverAmbosEixosSubida(int posicaoParada) throws InterruptedException{
    //nesse while o onibus esta rotacionando para cima enquanto move nos eixos x e y
    while(posicaoX != posicaoParada-10 && ligado){
      Thread.sleep(velocidade);
      Platform.runLater( () ->{ 
        onibus.setLayoutX(posicaoX);
        onibus.setLayoutY(posicaoY);
        onibus.setRotate(rotacao);
      });
      rotacao-=3;
      posicaoX+=DIRECAO;
      posicaoY--;
    }//fim while

    //nesse while o onibus esta rotacionando para baixo enquanto move nos eixos x e y
    while(posicaoX != posicaoParada && ligado){
      Thread.sleep(velocidade);
      Platform.runLater( () ->{ 
        onibus.setLayoutX(posicaoX);
        onibus.setLayoutY(posicaoY);
        onibus.setRotate(rotacao);
      });
      rotacao+=3;
      posicaoX+=DIRECAO;
      posicaoY--;
    }//fim while

    rotacao = 0;
    Platform.runLater( () -> onibus.setRotate(0));//garantindo que o onibus esteja reto ao fim da curva
  }//fim metodo moverAmbosEixosSubida
}
