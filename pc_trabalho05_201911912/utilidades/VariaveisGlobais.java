/* ***************************************************************
* Autor: Alan Bonfim Santos
* Matricula: 201911912
* Inicio: 04/05/2021 17:45
* Ultima alteracao: 05/05/2021 22:36
* Nome: VariaveisGlobais.java
* Funcao: classe que armazena as variaveis globais
*************************************************************** */
package utilidades;

public class VariaveisGlobais {
  public static int travaTunel1 = 0;
  public static int travaTunel2 = 0;

  public static int vezTunel1 = 0;
  public static int vezTunel2 = 1;//estou iniciando com 1 pois o processo vermelho (1) passara primeiro no tunel 2

  //variaveis para a solucao de peterson
  public static boolean interessadoTunel1[] = {false, false};
  public static int vezPetersonTunel1;

  public static boolean interessadoTunel2[] = {false, false};
  public static int vezPetersonTunel2;
}
