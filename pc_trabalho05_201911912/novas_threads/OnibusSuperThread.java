/* ***************************************************************
* Autor: Alan Bonfim Santos
* Matricula: 201911912
* Inicio: 30/04/2021 14:40
* Ultima alteracao: 05/05/2021 22:02
* Nome: OnibusSuperThread.java
* Funcao: super classe que possui os metodos de movimento base 
*         de ambos os onibus
*************************************************************** */
package novas_threads;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public abstract class OnibusSuperThread extends Thread{
  protected int velocidade = 25;
  protected ImageView onibus;

  //esta variavel garante uma forma de desativar as threads
  protected boolean ligado = true;

  //1 = indo para a direita, 1 = indo para a esquerda
  protected final int DIRECAO;

  protected int posicaoX;
  protected int posicaoY;
  protected int rotacao = 0;

  public OnibusSuperThread(int direcao, AnchorPane painel){
    this.DIRECAO = direcao;
    onibus = new ImageView();

    //determina se sera um onibus azul(indo para a direita) ou um onibus vermelho(indo para a esquerda)
    if(DIRECAO == 1){
      posicaoX = -58;
      posicaoY = 216;
      onibus.setImage(new Image("/recursos/imagens/onibusAzul.png", 56, 24, false, false));
      onibus.setX(posicaoX);
      onibus.setY(posicaoY);
      onibus.setVisible(true);
      Platform.runLater( () -> painel.getChildren().add(onibus));
    }
    else{
      posicaoX = 600;
      posicaoY = 254;
      onibus.setImage(new Image("/recursos/imagens/onibusVermelho.png", 56, 24, false, false));
      onibus.setX(posicaoX);
      onibus.setY(posicaoY);
      onibus.setVisible(true);
      Platform.runLater( () -> painel.getChildren().add(onibus));
    }
  }

  @Override
  public void run(){
    //metodo run
  }

  //"desliga" a thread e remove a imagem do Anchor Pane
  public void desligar(AnchorPane painel){
    painel.getChildren().remove(onibus);
    ligado = false;
  }

  //-----------------------------------------------------------------------------
  //metodos para modificar a velocidade
  public void setVelocidade(int novaVelocidade){
    this.velocidade = novaVelocidade;
  }
  //-----------------------------------------------------------------------------

  protected void moverPeloTunel(int posicaoParada) throws InterruptedException{
    moverAmbosEixosDescendoEixoY(posicaoParada);
    posicaoParada+=(100 * DIRECAO);
    moverEixoX(posicaoParada);
    posicaoParada+=(20 * DIRECAO);
    moverAmbosEixosSubindoEixoY(posicaoParada);
  }//fim metodo moverPeloTunel

  protected void moverEixoX(int posicaoParada) throws InterruptedException{
    while(posicaoX != posicaoParada && ligado){
      posicaoX += DIRECAO;
      Thread.sleep(velocidade);
      Platform.runLater( () -> onibus.setX(posicaoX));
    }
  }//fim metodo moverEixoX

  protected void moverAmbosEixosDescendoEixoY(int posicaoParada) throws InterruptedException{
    //nesse while o onibus esta rotacionando para baixo enquanto move nos eixos x e y
    int novaPosicao = posicaoParada +(-10 * DIRECAO);
    while(posicaoX != novaPosicao && ligado){
      Thread.sleep(velocidade);
      Platform.runLater( () ->{ 
        onibus.setX(posicaoX);
        onibus.setY(posicaoY);
        onibus.setRotate(rotacao);
      });
      rotacao+=3;
      posicaoX+=DIRECAO;
      posicaoY+=DIRECAO;
    }//fim while

    //nesse while o onibus esta rotacionando para cima enquanto move nos eixos x e y
    while(posicaoX != posicaoParada && ligado){
      Thread.sleep(velocidade);
      Platform.runLater( () ->{ 
        onibus.setX(posicaoX);
        onibus.setY(posicaoY);
        onibus.setRotate(rotacao);
      });
      rotacao-=3;
      posicaoX+=DIRECAO;
      posicaoY+=DIRECAO;
    }//fim while

    rotacao = 0;
    Platform.runLater( () -> onibus.setRotate(0));
  }//fim metodo moverAmbosEixosDescida

  protected void moverAmbosEixosSubindoEixoY(int posicaoParada) throws InterruptedException{
    //y++ = subidada do onibus vermelho
    //y-- = subida do onibus azul
    int modificadorY = DIRECAO * -1;
    int novaPosicao = posicaoParada +(-10 * DIRECAO);
    //nesse while o onibus esta rotacionando para cima enquanto move nos eixos x e y
    while(posicaoX != novaPosicao && ligado){
      Thread.sleep(velocidade);
      Platform.runLater( () ->{ 
        onibus.setX(posicaoX);
        onibus.setY(posicaoY);
        onibus.setRotate(rotacao);
      });
      rotacao-=3;
      posicaoX+=DIRECAO;
      posicaoY+=modificadorY;
    }//fim while

    //nesse while o onibus esta rotacionando para baixo enquanto move nos eixos x e y
    while(posicaoX != posicaoParada && ligado){
      Thread.sleep(velocidade);
      Platform.runLater( () ->{ 
        onibus.setX(posicaoX);
        onibus.setY(posicaoY);
        onibus.setRotate(rotacao);
      });
      rotacao+=3;
      posicaoX+=DIRECAO;
      posicaoY+=modificadorY;
    }//fim while

    rotacao = 0;
    Platform.runLater( () -> onibus.setRotate(0));//garantindo que o onibus esteja reto ao fim da curva
  }//fim metodo moverAmbosEixosSubida
}
