/* ***************************************************************
* Autor: Alan Bonfim Santos
* Matricula: 201911912
* Inicio: 24/03/2021 21:34
* Ultima alteracao: 27/03/2021 17:43
* Nome: ThreadPai.java
* Funcao: executa os comandos relacionados ao pai
*************************************************************** */

package threads;

import controles.PrincipalControlador;
import javafx.application.Platform;

public class ThreadPai extends Thread{
  private PrincipalControlador controlador;
  private int idade = 0;

  /* ***************************************************************
  * Metodo: setControlador
  * Funcao: define o controlador presente nessa classe
  * Parametros: o controlador do fxml
  * Retorno: void
  *************************************************************** */
  public void setControlador(PrincipalControlador controlador){
    this.controlador=controlador;
  }

  /* ***************************************************************
  * Metodo: run
  * Funcao: inicia as threads dos 3 filhos de acordo com a idade do
  *         pai, tambem responsavel por alterar as imagens e a idade 
  *         no fxml que sao relacionadas ao pai
  * Parametros: nenhum
  * Retorno: void
  *************************************************************** */
  @Override
  public void run(){
    controlador.alterarImgPai("/recursos/imagens/pessoa1.png");
    try {
      aumentarIdadePara(6);//passaram 6 anos
      controlador.alterarImgPai("/recursos/imagens/pessoa2.png");

      aumentarIdadePara(15);//passaram 15 anos
      controlador.alterarImgPai("/recursos/imagens/pessoa3.png");

      aumentarIdadePara(22);//passaram 22 anos ao total

      //nasce o primeiro filho
      controlador.iniciarPrimeiroFilho();
    
      aumentarIdadePara(25); //passaram 25 anos ao total

      //nasce o segundo filho
      controlador.iniciarSegundoFilho();

      aumentarIdadePara(30);//passaram 30 anos ao total
      controlador.alterarImgPai("/recursos/imagens/pessoa4.png");

      aumentarIdadePara(32);//passaram 32 anos ao total

      //nasce o terceiro filho
      controlador.iniciarTerceiroFilho();

      aumentarIdadePara(60);//passaram 60 anos ao total
      controlador.alterarImgPai("/recursos/imagens/pessoa5.png");

      aumentarIdadePara(90);//passaram 90 anos ao total
    }//fim try
    catch (InterruptedException e) {
      //
    }

    //o pai morreu
    controlador.alterarImgPai("/recursos/imagens/lapidePrincipal1.png");
    controlador.alterarLapideImgPai("/recursos/imagens/lapideSemVegetacao.png");

  }//fim metodo run

  /* ***************************************************************
  * Metodo: aumentarIdadePara
  * Funcao: aumenta a idade para a que foi passada por paramentro,
  *         aumentando 1 ano a cada segundo e atualizando no fxml
  * Parametros: recebe a idade que quer chegar
  * Retorno: void
  *************************************************************** */
  public void aumentarIdadePara(int novaIdade) throws InterruptedException{
    while(idade != novaIdade){
      Thread.sleep(1000);
      idade++;
      Platform.runLater( () ->controlador.atualizaIdadePai(idade));
    }
  }//fim metodo aumentarIdade
}
