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

public abstract class OnibusSuperThread extends Thread{
  protected int velocidade;
  protected ImageView onibus;

  //esta variavel garante uma forma de desativar as threads
  protected boolean ligado = true;

  //1 = indo para a direita, 1 = indo para a esquerda
  protected final int DIRECAO;

  protected int posicaoX;
  protected int posicaoY;
  protected int rotacao = 0;

  public OnibusSuperThread(int direcao){
    this.DIRECAO = direcao;
    if(DIRECAO == 1){
      posicaoX = -58;
      posicaoY = 216;
      onibus.setImage(new Image("/recursos/imagens/onibusAzul.png"));
    }
    else{
      posicaoX = 600;
      posicaoY = 254;
      onibus.setImage(new Image("/recursos/imagens/onibusVermelho.png"));
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

  //retorna o onibus, util para posiciona-lo na tela
  public ImageView getOnibus(){
    return this.onibus;
  }

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

  //metodos de acesso a velocidade
  public void setVelocidade(int novaVelocidade){
    this.velocidade = novaVelocidade;
  }
  
  public int getVelocidade(){
    return this.velocidade;
  }
}
