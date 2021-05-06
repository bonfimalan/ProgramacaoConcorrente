/* ***************************************************************
* Autor: Alan Bonfim Santos
* Matricula: 201911912
* Inicio: 03/05/2021 17:08
* Ultima alteracao: 05/05/2021 22:02
* Nome: OnibusVariavelTravemento.java
* Funcao: thread que implementa o algoritmo da variavel de travamento
*************************************************************** */

package novas_threads;

import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;

import utilidades.VariaveisGlobais;

public class OnibusVariavelTravemento extends OnibusSuperThread{

  @Override
  public void run(){
    try{
      if(super.DIRECAO == 1){
        while(super.ligado){
          //o -29 eh para "mover" o eixo x para o centro da imagem
          super.moverEixoX(110-29);//move o onibus ate a posicao passada
          acessarRegiaoCritica1();
          super.moverPeloTunel(130-29);//faz as animacoes das curvas e move pelo tunel
          sairRegiaoCritica1();

          //mesma sequencia de comandos que a anterior, mas em relacao ao tunel 2
          super.moverEixoX(350-29);
          acessarRegiaoCritica2();
          super.moverPeloTunel(370-29);
          sairRegiaoCritica2();

          super.moverEixoX(600);//move o onibus ate depois do fim da pista

          super.posicaoX = -58;
          Platform.runLater( () -> super.onibus.setX(-58));//faz o onibus voltar para a posicao original
        }//fim while
      }//fim if
      else{
        while(super.ligado){
          //o -29 eh para "mover" o eixo x para o centro da imagem
          super.moverEixoX(490-29);//move o onibus ate a posicao passada
          acessarRegiaoCritica2();
          super.moverPeloTunel(470-29);//faz as animacoes das curvas e move pelo tunel
          sairRegiaoCritica2();
        
          //mesma sequencia de comandos que a anterior, mas em relacao ao tunel 1
          super.moverEixoX(250-29);
          acessarRegiaoCritica1();
          super.moverPeloTunel(230-29);
          sairRegiaoCritica1();

          super.moverEixoX(-58);//move o onibus ate depois do fim da pista

          super.posicaoX = 600;
          Platform.runLater( () -> super.onibus.setX(600));//faz o onibus voltar para a posicao original
        }//fim while
      }//fim else
    }//fim try
    catch(InterruptedException e) { }
  }//fim metodo run

  public OnibusVariavelTravemento(int direcao, AnchorPane painel) {
    super(direcao, painel);
  }//fim OnibusVariavelTravemento
  
  //CONCERTAR A REGIAO CRITICA

  //------------------------------------------------------------------------------
  //metodos para acessar a regiao critica com a solucao da variavel de travamento
  private void acessarRegiaoCritica1() throws InterruptedException{
    while(VariaveisGlobais.travaTunel1 != 0)
      Thread.sleep(3);
    VariaveisGlobais.travaTunel1 = 1;
  }

  private void sairRegiaoCritica1(){
    VariaveisGlobais.travaTunel1 = 0;
  }

  private void acessarRegiaoCritica2() throws InterruptedException{
    while(VariaveisGlobais.travaTunel2 != 0)
      Thread.sleep(3);
    VariaveisGlobais.travaTunel2 = 1;
  }

  private void sairRegiaoCritica2(){
    VariaveisGlobais.travaTunel2 = 0;
  }
  //------------------------------------------------------------------------------
}
