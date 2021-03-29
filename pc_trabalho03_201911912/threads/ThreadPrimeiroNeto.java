/* ***************************************************************
* Autor: Alan Bonfim Santos
* Matricula: 201911912
* Inicio: 24/03/2021 21:34
* Ultima alteracao: 27/03/2021 18:15
* Nome: ThreadPrimeiroNeto.java
* Funcao: executa os comandos relacionados ao primeiro neto
*************************************************************** */

package threads;

import controles.PrincipalControlador;
import javafx.application.Platform;

public class ThreadPrimeiroNeto extends Thread{
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
  * Funcao: inicia a thread do bisneto de acordo com a idade do
  *         primeiro neto, tambem responsavel por alterar as imagens 
  *         e a idade no fxml que sao relacionadas ao primeiro neto
  * Parametros: nenhum
  * Retorno: void
  *************************************************************** */
  @Override
  public void run(){
    controlador.alterarImgPrimeiroNeto("/recursos/imagens/pessoa1.png");
    
    try {
      //passaram 38 anos ao total
      aumentarIdadePara(6);//passaram 44 anos ao total, idade do primeiro neto = 15
      controlador.alterarImgPrimeiroNeto("/recursos/imagens/pessoa2.png");

      aumentarIdadePara(15);//passaram 53 anos ao total, idade do primeiro neto = 15
      controlador.alterarImgPrimeiroNeto("/recursos/imagens/pessoa3.png");

      aumentarIdadePara(30);//passaram 68 anos ao total, idade do primeiro neto = 30
      controlador.alterarImgPrimeiroNeto("/recursos/imagens/pessoa4.png");
      //nasce o bisneto
      controlador.iniciarBisneto();

      aumentarIdadePara(35);//passaram 73 anos ao total, idade do primeiro neto = 35

      //morre o primeiro neto
      controlador.alterarImgPrimeiroNeto("/recursos/imagens/lapidePrincipal1.png");
      controlador.alterarImgLapidePrimeiroNeto("/recursos/imagens/lapideSemVegetacao.png");

      //todas as linhas a seguir servem para alterar as imagens na lapide, com o crescimento da vegetacao 
    
      int tempoAnos = 0;

      Thread.sleep(2000);
      controlador.alterarImgLapidePrimeiroNeto("/recursos/imagens/lapideComVegetacao.png");
      //passaram 75 anos ao total
      while(tempoAnos != 5){
        controlador.alterarImgPrimeiroNeto("/recursos/imagens/lapidePrincipal" + (tempoAnos + 2) + ".png");
        tempoAnos++;
        Thread.sleep(1000);
      }//fim while, passaram 80 anos
    }//fim try 
    catch (InterruptedException e) { }
  }//fim metodo run, morre o primeiro neto

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
      Platform.runLater( () -> controlador.atualizaIdadePrimeiroNeto(idade));
    } 
  }
}
