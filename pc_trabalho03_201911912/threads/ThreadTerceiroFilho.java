/* ***************************************************************
* Autor: Alan Bonfim Santos
* Matricula: 201911912
* Inicio: 24/03/2021 21:34
* Ultima alteracao: 27/03/2021 18:06
* Nome: ThreadTerceiroFilho.java
* Funcao: executa os comandos relacionados ao terceiro filho
*************************************************************** */

package threads;

import controles.PrincipalControlador;
import javafx.application.Platform;

public class ThreadTerceiroFilho extends Thread{
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
  * Funcao: responsavel por alterar as imagens e idade no fxml que
  *         sao relacionadas ao terceiro filho
  * Parametros: nenhum
  * Retorno: void
  *************************************************************** */
  @Override
  public void run(){
    controlador.alterarImgTerceiroFilho("/recursos/imagens/pessoa1.png");

    //passaram 32 anos ao total
    try {

      aumentarIdadePara(6);//passaram 38 anos ao total, idade do segundo filho = 6
      controlador.alterarImgTerceiroFilho("/recursos/imagens/pessoa2.png");

      aumentarIdadePara(15);//passaram 47 anos ao total, idade do segundo filho = 15
      controlador.alterarImgTerceiroFilho("/recursos/imagens/pessoa3.png");

      aumentarIdadePara(30);//passaram 62 anos ao total, idade do segundo filho = 30
      controlador.alterarImgTerceiroFilho("/recursos/imagens/pessoa4.png");

      aumentarIdadePara(55);//passaram 87 anos ao total, idade do segundo filho = 55

      //morre o terceiro filho
      controlador.alterarImgTerceiroFilho("/recursos/imagens/lapidePrincipal1.png");
      controlador.alterarImgLapideTerceiroFilho("/recursos/imagens/lapideSemVegetacao.png");

      //todas as linhas a seguir servem para alterar as imagens na lapide, com o crescimento da vegetacao    
      Thread.sleep(2000);
      controlador.alterarImgLapideTerceiroFilho("/recursos/imagens/lapideComVegetacao.png");
      controlador.alterarImgTerceiroFilho("/recursos/imagens/lapidePrincipal2.png");

      //passaram 89 anos ao total
      Thread.sleep(1000);
      controlador.alterarImgTerceiroFilho("/recursos/imagens/lapidePrincipal3.png");
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
      Platform.runLater( () -> controlador.atualizaIdadeTerceiroFilho(idade));
    }
  }//fim metodo aumentarIdadePara
}
