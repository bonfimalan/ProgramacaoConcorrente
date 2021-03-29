/* ***************************************************************
* Autor: Alan Bonfim Santos
* Matricula: 201911912
* Inicio: 24/03/2021 21:34
* Ultima alteracao: 27/03/2021 18:19
* Nome: ThreadSegundoNeto.java
* Funcao: executa os comandos relacionados ao segundo neto
*************************************************************** */

package threads;

import controles.PrincipalControlador;
import javafx.application.Platform;

public class ThreadSegundoNeto extends Thread{
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
  * Funcao: testa os valores para transmissao
  * Parametros: responsavel por alterar as imagens e a idade no fxml
  *             que sao relacionadas ao segundo neto
  * Retorno: void
  *************************************************************** */
  @Override
  public void run(){
    controlador.alterarImgSegundoNeto("/recursos/imagens/pessoa1.png");
    
    try {
    //passaram 45 anos ao total

    aumentarIdadePara(6);//passaram 51 anos ao total, idade do segundo neto = 6
    controlador.alterarImgSegundoNeto("/recursos/imagens/pessoa2.png");

    aumentarIdadePara(15);//passaram 60 anos ao total, idade do segundo neto = 15
    controlador.alterarImgSegundoNeto("/recursos/imagens/pessoa3.png");

    aumentarIdadePara(30);//passaram 75 anos ao total, idade do segundo neto = 30
    controlador.alterarImgSegundoNeto("/recursos/imagens/pessoa4.png");

    aumentarIdadePara(33);//passaram 78 anos ao total, idade do segundo neto = 33

    //morre o segundo neto
    controlador.alterarImgSegundoNeto("/recursos/imagens/lapidePrincipal1.png");
    controlador.alterarImgLapideSegundoNeto("/recursos/imagens/lapideSemVegetacao.png");

    //todas as linhas a seguir servem para alterar as imagens na lapide, com o crescimento da vegetacao 
    
      int tempoAnos = 0;

      Thread.sleep(2000);
      controlador.alterarImgLapideSegundoNeto("/recursos/imagens/lapideComVegetacao.png");
      //passaram 80 anos ao total
      while(tempoAnos != 5){
        controlador.alterarImgSegundoNeto("/recursos/imagens/lapidePrincipal" + (tempoAnos + 2) + ".png");
        tempoAnos++;
        Thread.sleep(1000);
      }//fim while, passaram 80 anos
    }//fim try 
    catch (InterruptedException e) { }
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
      Platform.runLater( () -> controlador.atualizaIdadeSegundoNeto(idade));
    } 
  }//fim metodo aumentarIdadePara
}
