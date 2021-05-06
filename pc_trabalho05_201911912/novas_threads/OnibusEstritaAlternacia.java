/* ***************************************************************
* Autor: Alan Bonfim Santos
* Matricula: 201911912
* Inicio: 04/05/2021 16:06
* Ultima alteracao: 05/05/2021 23:08
* Nome: OnibusEstritaAlternacia.java
* Funcao: thread que usa o algoritmo de estrita alternancia
*************************************************************** */

package novas_threads;

import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;
import utilidades.VariaveisGlobais;

public class OnibusEstritaAlternacia extends OnibusSuperThread{
  @Override
  public void run(){
    try{
      if(super.DIRECAO == 1){
        while(super.ligado){
          //o -29 eh para "mover" o eixo x para o centro da imagem
          super.moverEixoX(110-29);//move o onibus ate a posicao passada
          acessarRegiaoCritica1Azul();
          super.moverPeloTunel(130-29);//faz as animacoes das curvas e move pelo tunel
          sairRegiaoCritica1Azul();

          //mesma sequencia de comandos que a anterior, mas em relacao ao tunel 2
          super.moverEixoX(350-29);
          acessarRegiaoCritica2Azul();
          super.moverPeloTunel(370-29);
          sairRegiaoCritica2Azul();

          super.moverEixoX(600);//move o onibus ate depois do fim da pista

          super.posicaoX = -58;
          Platform.runLater( () -> super.onibus.setX(-58));//faz o onibus voltar para a posicao original
        }//fim while
      }//fim if
      else{
        while(super.ligado){
          //o -29 eh para "mover" o eixo x para o centro da imagem
          super.moverEixoX(490-29);//move o onibus ate a posicao passada
          acessarRegiaoCritica2Vermelho();
          super.moverPeloTunel(470-29);//faz as animacoes das curvas e move pelo tunel
          sairRegiaoCritica2Vermelho();
        
          //mesma sequencia de comandos que a anterior, mas em relacao ao tunel 1
          super.moverEixoX(250-29);
          acessarRegiaoCritica1Vermelho();
          super.moverPeloTunel(230-29);
          sairRegiaoCritica1Vermelho();

          super.moverEixoX(-58);//move o onibus ate depois do fim da pista

          super.posicaoX = 600;
          Platform.runLater( () -> super.onibus.setX(600));//faz o onibus voltar para a posicao original
        }//fim while
      }//fim else
    }//fim try
    catch(InterruptedException e) { }
  }//fim metodo run

  public OnibusEstritaAlternacia(int direcao, AnchorPane painel) {
    super(direcao, painel);
  }//fim OnibusEstritaAlternacia

  //-----------------------------------------------------------------------------------------
  //metodos para a regiao critica
  //0 = processo onibus azul, 1 = processo onibus vermelho
  private void acessarRegiaoCritica1Azul() throws InterruptedException{
    while(VariaveisGlobais.vezTunel1 != 0){
      Thread.sleep(3);
    }
  }

  private void sairRegiaoCritica1Azul(){
    VariaveisGlobais.vezTunel1 = 1;
  }

  private void acessarRegiaoCritica2Azul() throws InterruptedException{
    while(VariaveisGlobais.vezTunel2 != 0){
      Thread.sleep(3);
    }
  }

  private void sairRegiaoCritica2Azul(){
    VariaveisGlobais.vezTunel2 = 1;
  }

  private void acessarRegiaoCritica1Vermelho() throws InterruptedException{
    while(VariaveisGlobais.vezTunel1 != 1){
      Thread.sleep(3);
    }
  }

  private void sairRegiaoCritica1Vermelho(){
    VariaveisGlobais.vezTunel1 = 0;
  }

  private void acessarRegiaoCritica2Vermelho() throws InterruptedException{
    while(VariaveisGlobais.vezTunel2 != 1){
      Thread.sleep(3);
    }
  }

  private void sairRegiaoCritica2Vermelho(){
    VariaveisGlobais.vezTunel2 = 0;
  }
  //-----------------------------------------------------------------------------------------
}
