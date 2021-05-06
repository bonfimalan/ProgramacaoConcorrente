/* ***************************************************************
* Autor: Alan Bonfim Santos
* Matricula: 201911912
* Inicio: 05/05/2021 22:16
* Ultima alteracao: 05/05/2021 22:45
* Nome: OnibusPeterson.java
* Funcao: thread que implementa a solucao de perterson
*************************************************************** */
package novas_threads;

import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;
import utilidades.VariaveisGlobais;

public class OnibusPeterson extends OnibusSuperThread{
  private int numeroProcesso;
  @Override
  public void run(){
    try{
      if(super.DIRECAO == 1){
        while(super.ligado){
          //o -29 eh para "mover" o eixo x para o centro da imagem
          super.moverEixoX(110-29);//move o onibus ate a posicao passada
          acessarRegiaoCritica1(numeroProcesso);
          super.moverPeloTunel(130-29);//faz as animacoes das curvas e move pelo tunel
          sairRegiaoCritica1(numeroProcesso);

          //mesma sequencia de comandos que a anterior, mas em relacao ao tunel 2
          super.moverEixoX(350-29);
          acessarRegiaoCritica2(numeroProcesso);
          super.moverPeloTunel(370-29);
          sairRegiaoCritica2(numeroProcesso);

          super.moverEixoX(600);//move o onibus ate depois do fim da pista

          super.posicaoX = -58;
          Platform.runLater( () -> super.onibus.setX(-58));//faz o onibus voltar para a posicao original
        }//fim while
      }//fim if
      else{
        while(super.ligado){
          //o -29 eh para "mover" o eixo x para o centro da imagem
          super.moverEixoX(490-29);//move o onibus ate a posicao passada
          acessarRegiaoCritica2(numeroProcesso);
          super.moverPeloTunel(470-29);//faz as animacoes das curvas e move pelo tunel
          sairRegiaoCritica2(numeroProcesso);
        
          //mesma sequencia de comandos que a anterior, mas em relacao ao tunel 1
          super.moverEixoX(250-29);
          acessarRegiaoCritica1(numeroProcesso);
          super.moverPeloTunel(230-29);
          sairRegiaoCritica1(numeroProcesso);

          super.moverEixoX(-58);//move o onibus ate depois do fim da pista

          super.posicaoX = 600;
          Platform.runLater( () -> super.onibus.setX(600));//faz o onibus voltar para a posicao original
        }//fim while
      }//fim else
    }//fim try
    catch(InterruptedException e) { }
  }//fim metodo run

  public OnibusPeterson(int direcao, AnchorPane painel) {
    super(direcao, painel);
    //0 = onibus azul, 1 = onibus vermelho
    if(direcao == 1)
      numeroProcesso = 0;
    else
      numeroProcesso = 1;
  }//fim OnibusPeterson

  //-----------------------------------------------------------------------------------------
  //metodos para a regiao critica
  //
  private void acessarRegiaoCritica1(int processo) throws InterruptedException{
    int outro;
    outro = 1 - processo;
    VariaveisGlobais.interessadoTunel1[processo] = true;
    VariaveisGlobais.vezPetersonTunel1 = processo;

    while(VariaveisGlobais.vezPetersonTunel1 == processo && VariaveisGlobais.interessadoTunel1[outro] == true){
      Thread.sleep(1);
    }
  }

  private void sairRegiaoCritica1(int processo){
    VariaveisGlobais.interessadoTunel1[processo] = false;
  }

  private void acessarRegiaoCritica2(int processo) throws InterruptedException{
    int outro;
    outro = 1 - processo;
    VariaveisGlobais.interessadoTunel2[processo] = true;
    VariaveisGlobais.vezPetersonTunel2 = processo;

    while(VariaveisGlobais.vezPetersonTunel2 == processo && VariaveisGlobais.interessadoTunel2[outro] == true){
      Thread.sleep(1);
    }
  }

  private void sairRegiaoCritica2(int processo){
    VariaveisGlobais.interessadoTunel2[processo] = false;
  }
  //-----------------------------------------------------------------------------------------
}
