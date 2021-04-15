/* ***************************************************************
* Autor: Alan Bonfim Santos
* Matricula: 201911912
* Inicio: 04/04/2021 18:21
* Ultima alteracao: 14/04/2021 18:17
* Nome: ThreadOnibusAzul.java
* Funcao: controla o movimento do onibus azul e a passagem pelo tunel
*************************************************************** */
package threads;

import controle.ControladorPrincipal;

import javafx.application.Platform;
import javafx.scene.image.ImageView;

public class ThreadOnibusAzul extends Thread {
  private ControladorPrincipal controlador;
  private int velocidade = 25;
  private int posicaoX = -58;
  private int posicaoY;
  private ImageView onibus;

  public ThreadOnibusAzul(ControladorPrincipal controlador){
    this.controlador = controlador;
    this.onibus = controlador.getOnibusAzul();
    posicaoY = (int) onibus.getY();
  }

  @Override
  public void run(){
    try{
      while(true){
        //o -29 eh para "mover" o eixo x para o centro da imagem

        moverEixoX(110-29);//move o onibus ate a posicao passada
        verificaTunel1();//verifica se o tunel esta liberado, se nao ele para o onibus
        alteraEstadoTunel1();//altera o estado do tunel, nesse caso ele fica como ocupado
        controlador.setVisivelBandeiraEntradaVermelho1(true);//levanta a "bandeira"
        moverPeloTunel(130-29);//faz as animacoes das curvas e move pelo tunel
        alteraEstadoTunel1();//altera o estado do tunel, nesse caso ele fica como desocupado
        controlador.setVisivelBandeiraEntradaVermelho1(false);//abaixa a "bandeira"

        //mesma sequencia de comandos que a anterior, mas em relacao ao tunel 2
        moverEixoX(350-29);
        verificaTunel2();
        alteraEstadoTunel2();
        controlador.setVisivelBandeiraEntradaVermelho2(true);
        moverPeloTunel(370-29);
        alteraEstadoTunel2();
        controlador.setVisivelBandeiraEntradaVermelho2(false);

        moverEixoX(600);//move o onibus ate depois do fim da pista

        posicaoX = -58;
        Platform.runLater( () -> onibus.setX(-58));//faz o onibus voltar para a posicao original
    }//fim while
  }//fim try
    catch(InterruptedException e) { }
  }//fim metodo run

  private void verificaTunel1() throws InterruptedException{
    while(controlador.getTunelLiberado1() == false){
      Thread.sleep(1);
    }
  }//fim metodo verificaTunel1

  private void verificaTunel2() throws InterruptedException{
    while(controlador.getTunelLiberado2() == false){
      Thread.sleep(1);
    }
  }//fim metodo verificaTunel1

  private void alteraEstadoTunel1(){
    if(controlador.getTunelLiberado1())
      controlador.setTunelLiberado1(false);
    else
      controlador.setTunelLiberado1(true);
  }//fim metodo alteraEstadoTunel1

  private void alteraEstadoTunel2(){
    if(controlador.getTunelLiberado2())
      controlador.setTunelLiberado2(false);
    else
      controlador.setTunelLiberado2(true);
  }//fim metodo alteraEstadoTunel2

  private void moverPeloTunel(int posicaoParada) throws InterruptedException{
    moverAmbosEixosDescida(posicaoParada);
    posicaoParada+=100;
    moverEixoX(posicaoParada);
    posicaoParada+=20;
    moverAmbosEixosSubida(posicaoParada);
  }//fim metodo moverPeloTunel

  private void moverEixoX(int posicaoParada) throws InterruptedException{
    while(posicaoX != posicaoParada){
      Thread.sleep(velocidade);
      final int CONST = posicaoX;
      Platform.runLater( () -> onibus.setX(CONST));
      posicaoX++;
    }
  }//fim metodo moverEixoX

  private void moverAmbosEixosDescida(int posicaoParada) throws InterruptedException{
    //nesse while o onibus esta rotacionando para baixo enquanto move nos eixos x e y
    int rotacao = 0;
    while(posicaoX != posicaoParada-10){
      Thread.sleep(velocidade);
      final int CONST_X = posicaoX;
      final int CONST_Y = posicaoY;
      final int ROTACAO = rotacao;
      Platform.runLater( () -> onibus.setX(CONST_X));
      Platform.runLater( () -> onibus.setY(CONST_Y));
      Platform.runLater( () -> onibus.setRotate(ROTACAO));
      rotacao+=3;
      posicaoX++;
      posicaoY++;
    }//fim while

    //nesse while o onibus esta rotacionando para cima enquanto move nos eixos x e y
    while(posicaoX != posicaoParada){
      Thread.sleep(velocidade);
      final int CONST_X = posicaoX;
      final int CONST_Y = posicaoY;
      final int ROTACAO = rotacao;
      Platform.runLater( () -> onibus.setX(CONST_X));
      Platform.runLater( () -> onibus.setY(CONST_Y));
      Platform.runLater( () -> onibus.setRotate(ROTACAO));
      rotacao-=3;
      posicaoX++;
      posicaoY++;
    }//fim while

    Platform.runLater( () -> onibus.setRotate(0));
  }//fim metodo moverAmbosEixosDescida

  private void moverAmbosEixosSubida(int posicaoParada) throws InterruptedException{
    int rotacao = 0;
    //nesse while o onibus esta rotacionando para cima enquanto move nos eixos x e y
    while(posicaoX != posicaoParada-10){
      Thread.sleep(velocidade);
      final int CONST_X = posicaoX;
      final int CONST_Y = posicaoY;
      final int ROTACAO = rotacao;
      Platform.runLater( () -> onibus.setX(CONST_X));
      Platform.runLater( () -> onibus.setY(CONST_Y));
      Platform.runLater( () -> onibus.setRotate(ROTACAO));
      rotacao-=3;
      posicaoX++;
      posicaoY--;
    }//fim while

    //nesse while o onibus esta rotacionando para baixo enquanto move nos eixos x e y
    while(posicaoX != posicaoParada){
      Thread.sleep(velocidade);
      final int CONST_X = posicaoX;
      final int CONST_Y = posicaoY;
      final int ROTACAO = rotacao;
      Platform.runLater( () -> onibus.setX(CONST_X));
      Platform.runLater( () -> onibus.setY(CONST_Y));
      Platform.runLater( () -> onibus.setRotate(ROTACAO));
      rotacao+=3;
      posicaoX++;
      posicaoY--;
    }//fim while

    Platform.runLater( () -> onibus.setRotate(0));//garantindo que o onibus esteja reto ao fim da curva
  }//fim metodo moverAmbosEixosSubida

  public void setVelocidade(int novaVelocidade){
    this.velocidade = novaVelocidade;
  }
  
  public int getVelocidade(){
    return this.velocidade;
  }
}
