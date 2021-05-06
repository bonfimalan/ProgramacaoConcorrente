/* ***************************************************************
* Autor: Alan Bonfim Santos
* Matricula: 201911912
* Inicio: 04/04/2021 17:51
* Ultima alteracao: 05/04/2021 23:00
* Nome: ControladorPrincipal.java
* Funcao: Controla as modificacoes no fxml e algumas acoes nas 
          threads
*************************************************************** */
package controle;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;


import novas_threads.*;
import threads.ThreadOnibusAzul;
import threads.ThreadOnibusVermelho;
import view.MenuVelocidadeAdicao;

public class ControladorPrincipal implements Initializable{
  //imagens
  @FXML ImageView onibusAzul;
  @FXML ImageView onibusVermelho;
  @FXML ImageView bandeiraEntradaAzul1;
  @FXML ImageView bandeiraEntradaAzul2;
  @FXML ImageView bandeiraEntradaVermelho1;
  @FXML ImageView bandeiraEntradaVermelho2;

  //Text com as velocidades de cada onibus
  @FXML Text velocidadeOnibusAzul;
  @FXML Text velocidadeOnibusVermelho;

  @FXML AnchorPane painel;

  @FXML HBox hBoxVelocidadeAzul;
  @FXML HBox hBoxVelocidadeVermelho;

  private ThreadOnibusAzul threadOnibusAzul;
  private ThreadOnibusVermelho threadOnibusVermelho;

  //adiciona 10 possiveis onibus
  private OnibusSuperThread[] threads = new OnibusSuperThread[10];

  private boolean tunelLiberado1 = true;
  private boolean tunelLiberado2 = true;

  //menus para as trheads
  private MenuVelocidadeAdicao menuAzul;
  private MenuVelocidadeAdicao menuVermelho;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    bandeiraEntradaAzul1.setVisible(false);
    bandeiraEntradaAzul2.setVisible(false);
    bandeiraEntradaVermelho1.setVisible(false);
    bandeiraEntradaVermelho2.setVisible(false);

    //posicionando os onibus
    onibusAzul.setVisible(false);
    onibusVermelho.setVisible(false);

    threadOnibusAzul = new ThreadOnibusAzul(this);
    threadOnibusVermelho = new ThreadOnibusVermelho(this);

    hBoxVelocidadeAzul.setVisible(false);
    hBoxVelocidadeVermelho.setVisible(false);
  }//fim metodo initialize

  public void iniciar(String opcao){
    switch (opcao){
      case "Protocolo das bandeira":
        desativarVetorThreads();
        if(!threadOnibusAzul.isAlive() && !threadOnibusVermelho.isAlive()){
          threadOnibusAzul = new ThreadOnibusAzul(this);
          threadOnibusVermelho = new ThreadOnibusVermelho(this);

          onibusAzul.setVisible(true);
          onibusVermelho.setVisible(true);

          onibusAzul.setX(-54);
          onibusVermelho.setX(600);

          velocidadeOnibusAzul.setText("40km/h");
          velocidadeOnibusVermelho.setText("40km/h");

          threadOnibusAzul.start();
          threadOnibusVermelho.start();

          hBoxVelocidadeAzul.setVisible(true);
          hBoxVelocidadeVermelho.setVisible(true);
        }
        break;
      case "Variavel de Travamento":
        desativaBandeiras();

        desativarVetorThreads();

        construirMenus(5,5, 25);

        for(int i=0; i<5; i++)
          threads[i] = new OnibusVariavelTravemento(1, painel);
        for(int i=5; i<10; i++)
          threads[i] = new OnibusVariavelTravemento(-1, painel);
        break;
      case "Estrita Alternancia":
        desativaBandeiras();

        desativarVetorThreads();

        construirMenus(5,5, 25);

        for(int i=0; i<5; i++)
          threads[i] = new OnibusEstritaAlternacia(1, painel);
        for(int i=5; i<10; i++)
          threads[i] = new OnibusEstritaAlternacia(-1, painel);
        break;
      case "Peterson":
        desativaBandeiras();

        desativarVetorThreads();

        construirMenus(1,1, 100);
        
        //criando as duas threads (como informado em aula, foi feito com 2 onibus)
        threads[0] = new OnibusPeterson(1, painel);
        threads[1] = new OnibusPeterson(-1, painel);
        break;
    }//fim swith
  }//fim metodo inciar

  public void desativarVetorThreads(){
    //verifica se as threads possuem algum valor, como eu inicio todas de um vez nao ha necessidade 
    //de verificar todas
    if(threads[0] != null ){
      //caso seja escolhido a solucao de peterson somente as 2 primeira posicoes serao inicializadas
      //ent esse trecho garante que nao ocorram erros
      int tamanhoInicializado;
      if(threads[2] == null)
        tamanhoInicializado = 2;
      else
        tamanhoInicializado = 10;
      //remove os menus de velociade
      painel.getChildren().removeAll(menuAzul, menuVermelho);
      for (int i=0; i<tamanhoInicializado; i++)
        threads[i].desligar(painel);
    }
  }//fim metodo desativarVetorThreads

  //metodo que configura e adiciona os menus de velocidade
  public void construirMenus(int numeroOnibusAzul, int numeroOnibusVermelho, int tamanhoSlider){
    //configurando o menu de controle para os onibus azuis
    menuAzul = new MenuVelocidadeAdicao("azul", 0, this, numeroOnibusAzul, tamanhoSlider);
    menuAzul.setLayoutX(14);
    menuAzul.setLayoutY(150);

    //configurando o menu de controle para os onibus vermelhos
    menuVermelho = new MenuVelocidadeAdicao("Vermelho", numeroOnibusAzul, this, numeroOnibusVermelho, tamanhoSlider);
    menuVermelho.setLayoutX(14);
    menuVermelho.setLayoutY(300);

    //adicionando os menus no anchor pane
    painel.getChildren().addAll(menuAzul, menuVermelho);
  }//fim metodo construirMenus
  
  public void setThreadVelocidade(int indice, int velocidade){
    threads[indice].setVelocidade(velocidade);
  }

  public void ligarThread(int indice){
    threads[indice].start();
  }

  //metodo para destivar e resetar o protocolo das bandeiras
  public void desativaBandeiras(){
    if(threadOnibusAzul.isAlive() && threadOnibusVermelho.isAlive()){
      threadOnibusAzul.interrupt();
      threadOnibusVermelho.interrupt();

      velocidadeOnibusAzul.setText("0");
      velocidadeOnibusVermelho.setText("0");

      tunelLiberado1 = true;
      tunelLiberado2 = true;

      onibusAzul.setY(0);
      onibusVermelho.setY(0);
      onibusAzul.setRotate(0);
      onibusVermelho.setRotate(0);

      onibusAzul.setVisible(false);
      onibusVermelho.setVisible(false);

      bandeiraEntradaAzul1.setVisible(false);
      bandeiraEntradaAzul2.setVisible(false);
      bandeiraEntradaVermelho1.setVisible(false);
      bandeiraEntradaVermelho2.setVisible(false);

      hBoxVelocidadeAzul.setVisible(false);
      hBoxVelocidadeVermelho.setVisible(false);
    }//fim if
  }//fim metodo desativaBandeiras

  //metodo que eh executado ao usuario fechar o programa
  public void fechar(){
    threadOnibusAzul.interrupt();
    threadOnibusVermelho.interrupt();
    System.exit(0);
  }//fim metodo fechar

  //============================================================================================

  //metodos que mudam a velocidade dos onibus
  @FXML
  public void aumentarVelocidadeAzul(ActionEvent evento){
    int velocidade = threadOnibusAzul.getVelocidade();
    velocidade -= 5;

    if(velocidade >= 5){
      threadOnibusAzul.setVelocidade(velocidade);
      velocidadeOnibusAzul.setText(Integer.toString(1000/velocidade) + "km/h");
    }
  }//fim metodo aumentarVelocidadeAzul

  @FXML
  public void diminuirVelocidadeAzul(ActionEvent evento){
    int velocidade = threadOnibusAzul.getVelocidade();
    velocidade += 5;

    if(velocidade <= 25){
      threadOnibusAzul.setVelocidade(velocidade);
      velocidadeOnibusAzul.setText(Integer.toString(1000/velocidade) + "km/h");
    }
  }//fim metodo aumentarVelocidadeAzul

  @FXML
  public void aumentarVelocidadeVermelho(ActionEvent evento){
    int velocidade = threadOnibusVermelho.getVelocidade();
    velocidade -= 5;

    if(velocidade >= 5){
      threadOnibusVermelho.setVelocidade(velocidade);
      velocidadeOnibusVermelho.setText(Integer.toString(1000/velocidade) + "km/h");
    }
  }//fim metodo aumentarVelocidadeVermelho

  @FXML
  public void diminuirVelocidadeVermelho(ActionEvent evento){
    int velocidade = threadOnibusVermelho.getVelocidade();
    velocidade += 5;

    if(velocidade <= 25){
      threadOnibusVermelho.setVelocidade(velocidade);
      velocidadeOnibusVermelho.setText(Integer.toString(1000/velocidade) + "km/h");
    }
  }//fim metodo diminuirVelocidadeVermelho

  //============================================================================================

  //get e set
  public void setVisivelBandeiraEntradaAzul1(boolean estado){
    bandeiraEntradaAzul1.setVisible(estado);
  }

  public void setVisivelBandeiraEntradaAzul2(boolean estado){
    bandeiraEntradaAzul2.setVisible(estado);
  }

  public void setVisivelBandeiraEntradaVermelho1(boolean estado){
    bandeiraEntradaVermelho1.setVisible(estado);
  }

  public void setVisivelBandeiraEntradaVermelho2(boolean estado){
    bandeiraEntradaVermelho2.setVisible(estado);
  }

  public ImageView getOnibusAzul(){
    return this.onibusAzul;
  }

  public ImageView getOnibusVermelho(){
    return this.onibusVermelho;
  }

  public boolean getTunelLiberado1(){
    return this.tunelLiberado1;
  }

  public void setTunelLiberado1(boolean valor){
    this.tunelLiberado1 = valor;
  }

  public boolean getTunelLiberado2(){
    return this.tunelLiberado2;
  }

  public void setTunelLiberado2(boolean valor){
    this.tunelLiberado2 = valor;
  }
}
