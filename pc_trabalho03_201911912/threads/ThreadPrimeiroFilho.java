/* ***************************************************************
* Autor: Alan Bonfim Santos
* Matricula: 201911912
* Inicio: 24/03/2021 21:34
* Ultima alteracao: 27/03/2021 17:59
* Nome: ThreadPrimeiroFilho.java
* Funcao: executa os comandos relacionados ao primeiro filho
*************************************************************** */

package threads;

import controles.PrincipalControlador;
import javafx.application.Platform;

public class ThreadPrimeiroFilho extends Thread{
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
  * Funcao: inicia a thread do primeiro neto de acordo com a idade do
  *         primeiro filho, tambem responsavel por alterar as imagens 
  *         e a idade no fxml que sao relacionadas ao primeiro filho
  * Parametros: nenhum
  * Retorno: void
  *************************************************************** */
  @Override
  public void run(){
    controlador.alterarImgPrimeiroFilho("/recursos/imagens/pessoa1.png");

    try {
      //passaram 22 anos ao total

      aumentarIdadePara(6);//passaram 28 anos ao total, idade do primeiro filho = 6
      controlador.alterarImgPrimeiroFilho("/recursos/imagens/pessoa2.png");

      aumentarIdadePara(15);//passaram 37 anos ao total, idade do primeiro filho = 15
      controlador.alterarImgPrimeiroFilho("/recursos/imagens/pessoa3.png");

      aumentarIdadePara(16);//passaram 38 anos ao total, idade do primeiro filho = 16

      //nasce o primeiro neto
      controlador.iniciarPrimeiroNeto();

      aumentarIdadePara(30);//passaram 52 anos ao total, idade do primeiro filho = 30
      controlador.alterarImgPrimeiroFilho("/recursos/imagens/pessoa4.png");

      aumentarIdadePara(60);//passaram 82 anos ao total, idade do primeiro filho = 60
      controlador.alterarImgPrimeiroFilho("/recursos/imagens/pessoa5.png");

      

      aumentarIdadePara(61);//passaram 83 anos ao total, idade do primeiro filho = 61

      //morre o primeiro filho
      controlador.alterarImgPrimeiroFilho("/recursos/imagens/lapidePrincipal1.png");
      controlador.alterarImgLapidePrimeiroFilho("/recursos/imagens/lapideSemVegetacao.png");

      //todas as linhas a seguir servem para alterar as imagens na lapide, com o crescimento da vegetacao 
      int tempoAnos = 0;

      Thread.sleep(2000);
      controlador.alterarImgLapidePrimeiroFilho("/recursos/imagens/lapideComVegetacao.png");
      //passaram 85 anos ao total
      while(tempoAnos != 5){
        controlador.alterarImgPrimeiroFilho("/recursos/imagens/lapidePrincipal" + (tempoAnos + 2) + ".png");
        tempoAnos++;
        Thread.sleep(1000);
      }//fim while, passaram 90 anos
    }//fim try 
    catch (InterruptedException e) { 
      //
    }
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
      Platform.runLater( () -> controlador.atualizaIdadePrimeiroFilho(idade));
    }
  }
}
