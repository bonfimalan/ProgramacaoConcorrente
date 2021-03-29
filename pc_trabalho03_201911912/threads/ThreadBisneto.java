/* ***************************************************************
* Autor: Alan Bonfim Santos
* Matricula: 201911912
* Inicio: 24/03/2021 21:34
* Ultima alteracao: 27/03/2021 18:19
* Nome: ThreadBisneto.java
* Funcao: executa os comandos relacionados ao bisneto
*************************************************************** */

package threads;

import controles.PrincipalControlador;
import javafx.application.Platform;

public class ThreadBisneto extends Thread{
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
  * Funcao: responsavel por alterar as imagens e a idade no fxml
  *         que sao relacionadas ao bisneto
  * Parametros: nenhum
  * Retorno: void
  *************************************************************** */
  @Override
  public void run(){
    controlador.alterarImgBisneto("/recursos/imagens/pessoa1.png");

    try {
    //passaram 68 anos ao total

    aumentarIdadePara(6);//passaram 74 anos ao total, idade do segundo neto = 6
    controlador.alterarImgBisneto("/recursos/imagens/pessoa2.png");

    aumentarIdadePara(12);//passaram 80 anos ao total, idade do segundo neto = 12

    //morre o bisneto
    controlador.alterarImgBisneto("/recursos/imagens/lapidePrincipal1.png");
    controlador.alterarImgLapideBisneto("/recursos/imagens/lapideSemVegetacao.png");

    //todas as linhas a seguir servem para alterar as imagens na lapide, com o crescimento da vegetacao 
      int tempoAnos = 0;

      Thread.sleep(2000);
      controlador.alterarImgLapideBisneto("/recursos/imagens/lapideComVegetacao.png");
      //passaram 82 anos ao total
      while(tempoAnos != 5){
        controlador.alterarImgBisneto("/recursos/imagens/lapidePrincipal" + (tempoAnos + 2) + ".png");
        tempoAnos++;
        Thread.sleep(1000);
      }//fim while, passaram 87 anos ao total

    }//fim try 
    catch (InterruptedException e) { }
  }//fim metodo run, morre o bisneto

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
      Platform.runLater( () -> controlador.atualizaIdadeBisneto(idade));
    } 
  }//fim metodo aumentarIdadePara
}
