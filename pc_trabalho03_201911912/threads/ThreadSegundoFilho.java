/* ***************************************************************
* Autor: Alan Bonfim Santos
* Matricula: 201911912
* Inicio: 24/03/2021 21:34
* Ultima alteracao: 27/03/2021 18:00
* Nome: ThreadSegundoFilho.java
* Funcao: executa os comandos relacionados ao segundo filho
*************************************************************** */

package threads;

import controles.PrincipalControlador;
import javafx.application.Platform;

public class ThreadSegundoFilho extends Thread{
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
  * Funcao: inicia a thread do segundo neto de acordo com a idade do
  *         segundo filho, tambem responsavel por alterar as imagens 
  *         e a idade no fxml que sao relacionadas ao segundo filho
  * Parametros: nenhum
  * Retorno: void
  *************************************************************** */
  @Override
  public void run(){
    controlador.alterarImgSegundoFilho("/recursos/imagens/pessoa1.png");

    try{
      //passaram 25 anos ao total

      aumentarIdadePara(6);//passaram 31 anos ao total, idade do segundo filho = 20
      controlador.alterarImgSegundoFilho("/recursos/imagens/pessoa2.png");

      aumentarIdadePara(15);//passaram 40 anos ao total, idade do segundo filho = 20
      controlador.alterarImgSegundoFilho("/recursos/imagens/pessoa3.png");

      aumentarIdadePara(20);//passaram 45 anos ao total, idade do segundo filho = 20
      //nasce o segundo neto
      controlador.iniciarSegundoNeto();

      aumentarIdadePara(30);//passaram 55 anos ao total, idade do segundo filho = 30
      controlador.alterarImgSegundoFilho("/recursos/imagens/pessoa4.png");      

      aumentarIdadePara(55);//passaram 80 anos ao total, idade do segundo filho = 55
    }//fim try
    catch (InterruptedException e) {
      //
    }


    //morre o segundo filho
    controlador.alterarImgSegundoFilho("/recursos/imagens/lapidePrincipal1.png");
    controlador.alterarImgLapideSegundoFilho("/recursos/imagens/lapideSemVegetacao.png");

    //todas as linhas a seguir servem para alterar as imagens na lapide, com o crescimento da vegetacao 
    try {
      int tempoAnos = 0;

      Thread.sleep(2000);
      controlador.alterarImgLapideSegundoFilho("/recursos/imagens/lapideComVegetacao.png");
      //passaram 82 anos ao total
      while(tempoAnos != 5){
        controlador.alterarImgSegundoFilho("/recursos/imagens/lapidePrincipal" + (tempoAnos + 2) + ".png");
        tempoAnos++;
        Thread.sleep(1000);
      }//fim while, passaram 87 anos
    } catch (InterruptedException e) { }
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
      Platform.runLater(() -> controlador.atualizaIdadeSegundoFilho(idade));
    }
  }
}
