/********************************************************************
* Autor: Alan Bonfim Santos
* Inicio: 25/02/2021 15:20:20
* Ultima alteracao: 03/03/2021 23:47:25
* Nome: Jogo da Memoria
* Funcao: Um jogo da memoria simples com um total de 10 combinacoes
********************************************************************/

package controles;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

public class PrincipalController implements Initializable {
  //atributos relacionadas ao fxml
  @FXML 
  private Button botao1;
  @FXML 
  private Button botao2;  
  @FXML 
  private Button botao3;
  @FXML 
  private Button botao4;
  @FXML 
  private Button botao5;
  @FXML 
  private Button botao6;
  @FXML 
  private Button botao7;
  @FXML
  private Button botao8;
  @FXML 
  private Button botao9;
  @FXML
  private Button botao10;
  @FXML
  private Button botao11;
  @FXML
  private Button botao12;
  @FXML
  private Button botao13;
  @FXML
  private Button botao14;
  @FXML
  private Button botao15;
  @FXML
  private Button botao16;
  @FXML
  private Button botao17;
  @FXML
  private Button botao18;
  @FXML
  private Button botao19;
  @FXML
  private Button botao20;

  @FXML
  private Label labelPontuacao;
  @FXML
  private Label labelErros;
  @FXML
  private Label labelRestantes;
  @FXML
  private Label labelErroOcorrido;
  @FXML
  private Label labelInicio;
  @FXML
  private VBox vBoxPrincipal;

  
  private Button botaoSelecionado1;
  private Button botaoSelecionado2;   
  private Button[] botoes;

  //atributos numericas
  private int pontuacao;
  private int numeroErros;  
  private byte contadorSelecionados;
  private byte contadorAcertos;
  private static final byte TAMANHO_VETORES = 20;

  private byte[] numeros = new byte[TAMANHO_VETORES];

  //atributos de cores
  private Paint cinza = Paint.valueOf("#d1d1d1");
  private Paint preto = Paint.valueOf("black");

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    //garante que a VBox fique desativada ate o metodo de inicio ser chamado pelo usuario
    vBoxPrincipal.setDisable(true);
    
    //inicializacao dos vetor numerico e do de botoes
    carregarVetorBotoes();
    carregarVetorNumerico();

    //torna o texto de todos os botoes "invisivel," alterando par a mesma cor de fundo do botao
    alterarCorTextoBotoes(cinza);
  }

  /********************************************************************
  * Metodo: comecar
  * Funcao: metodo que da inicio a um novo jogo
  * Parametros: event = ActionEvent ligado ao botao pelo fxml
  * Retorno: void
  ********************************************************************/
  @FXML
  public void comecar(ActionEvent event){
    //embaralhando os numeros antes de adicionar aos botoes
    embaralharVetorNumerico();

    //resgatando a referencia do botao
    Button botaoIniciar = (Button) event.getSource();
    //mudando o texto do botao
    botaoIniciar.setText("Novo Jogo");
    //destivando o botao para evitar uma nova chamada do metodo enquanto o temporizador esta ativo
    botaoIniciar.setDisable(true);
    //aterando labelInicio para invisivel
    labelInicio.setVisible(false);

    //definindo o valor inicial de contadorAcertos, numeroErros e pontuacao
    contadorAcertos=10;
    labelRestantes.setText(Byte.toString(contadorAcertos));
    numeroErros=0;
    labelErros.setText(Integer.toString(numeroErros));
    pontuacao=0;
    labelPontuacao.setText(Integer.toString(pontuacao));
    
    //garantir que todos os botoes estajam sempre desativados
    if(!vBoxPrincipal.isDisable()){
      desativaAtivaVBox();
    }

    //adicionando os numeros aos botoes
    carregaNumeros();
    //torna o texto dos botoes visivel ao jogador
    alterarCorTextoBotoes(preto);
    //temporizador para garantir o tempo de memorizacao das posicoes dos numeros pelo jogador
    Timer temporizador = new Timer();
    int delay = 2000;
    temporizador.schedule(new TimerTask(){
      public void run(){
        Platform.runLater(() -> desativaAtivaVBox());
        Platform.runLater(() -> alterarCorTextoBotoes(cinza));
        Platform.runLater(() -> botaoIniciar.setDisable(false));
        temporizador.cancel();
      }
    }, delay);//fim temporizador
  }//fim metodo comecar

  /********************************************************************
  * Metodo: jogar
  * Funcao: testar se os botoes selecionados possuem o mesmo texto, que
  *         eh a condicao de acerto e verificar se um botao ja foi
  *         selecionado
  * Parametros: event = ActionEvent ligado ao botao pelo fxml
  * Retorno: void
  ********************************************************************/
  @FXML
  public void jogar(ActionEvent event){
    //resgata a instancia do botao que realizou o clique
    Button botaoTeste = (Button) event.getSource();
    //mantem a label de erro invisivel caso ela tenha sido ativada pelo erro
    labelErroOcorrido.setVisible(false);

    //testa se o texto do botao esta cinza, se sim ele ainda nao foi selecionado e se o jogo ja acabou
    if(botaoTeste.getTextFill()==cinza && contadorAcertos!=0){
      //caso o contador de selecionado seja igual a 1, ou seja, dois botoes foram selecionados
      if(contadorSelecionados==1){
        desativaAtivaVBox();
        //inclementa contadorSelecionados
        contadorSelecionados++;

        //resgata o botao e torna o texto "visivel"
        botaoSelecionado2 = (Button) event.getSource();
        botaoSelecionado2.setTextFill(preto);
        //adiciona o texto dos dois botoes a um vetor do tipo String
        String[] conteudoBotao = {botaoSelecionado1.getText(), botaoSelecionado2.getText()};
        
        //testa se sao iguais
        if(conteudoBotao[0].equals(conteudoBotao[1])){
          //faz as modificacoes realacionadas ao acerto e potuacao
          contadorAcertos--;
          desativaAtivaVBox();
          labelRestantes.setText(Byte.toString(contadorAcertos));
          alterarPontuacao();
        }//fim if, ativado quando o jogador seleciona dois cartoes iguais

        //se o jogador errou
        else{
          //inclementa o numero de erros
          numeroErros++;
          labelErros.setText(Integer.toString(numeroErros));

          //temporizador para que o usuario veja os dois botoes que selecionou
          Timer temporizador = new Timer();
          int delay = 500;
          temporizador.schedule(new TimerTask(){            
            public void run(){
              Platform.runLater(() -> botaoSelecionado2.setTextFill(cinza));
              Platform.runLater(() -> botaoSelecionado1.setTextFill(cinza));
              Platform.runLater(() -> botaoSelecionado1=null);
              Platform.runLater(() -> botaoSelecionado2=null);
              Platform.runLater(() -> desativaAtivaVBox());
              temporizador.cancel();
            }
          }, delay);//fim temporizador
        }//fim else
      }//fim if

      //if relacionado ao primeiro botao selecionado
      if(contadorSelecionados==0){
        //incrementa o contadorSelecionados e deixa o texto do botao "visivel"
        botaoSelecionado1 = (Button) event.getSource();
        botaoSelecionado1.setTextFill(preto);
        contadorSelecionados++;
      }//fim if

      if(contadorSelecionados==2)
        contadorSelecionados=0;
      }//fim if
    
    //caso o botao ja tenha sido selecionado
    else{
      labelErroOcorrido.setText("Botao ja escolhido!!!");
      labelErroOcorrido.setVisible(true);
    }//fim else

    //finalizacao do jogo
    if(contadorAcertos==0){
      //altera a labelInicio inicio para mostrar que o jogo finalizou e torna a de erro invisivel
      labelInicio.setText("Voce conseguiu finalizar o jogo!!");
      labelInicio.setVisible(true);
      labelErroOcorrido.setVisible(false);
    }//fim if
      
  }//fim do metodo jogar

  /********************************************************************
  * Metodo: desativaAtivaVBox
  * Funcao: metodo simples para ativar e desativar a VBox, assim
  *         desativando tambem os botoes
  * Parametros: nenhum
  * Retorno: void
  ********************************************************************/
  public void desativaAtivaVBox(){
    if(vBoxPrincipal.isDisable())
      vBoxPrincipal.setDisable(false);
    else
      vBoxPrincipal.setDisable(true);
  }//fim metodo desativaAtivaVBox

  /********************************************************************
  * Metodo: alterarCorTextoBotoes
  * Funcao: muda a cor do texto de todos os botoes para a cor passada
  *         como parametro
  * Parametros: cor = a cor que o texto do botao deve receber
  * Retorno: void
  ********************************************************************/
  public void alterarCorTextoBotoes(Paint cor){
    for(int i=0; i<TAMANHO_VETORES; i++)      
      botoes[i].setTextFill(cor);
  }//fim metodo alterarCorTextoBotoes
  
  /********************************************************************
  * Metodo: carregaNumeros
  * Funcao: adiciona os numeros do vetor numerico aos botoes
  * Parametros: nenhum
  * Retorno: void
  ********************************************************************/
  public void carregaNumeros(){
    for(int i=0; i<TAMANHO_VETORES; i++){
      String s = Byte.toString(numeros[i]);
      botoes[i].setText(s);
    }
  }//fim metodo carregaNumeros

  /********************************************************************
  * Metodo: carregarVetorNumerico
  * Funcao: inicializa o vetor numerico com a sequencia 
  *         1, 1, 2, 2, ..., 9, 9, 10, 10 
  * Parametros: nenhum
  * Retorno: void
  ********************************************************************/
  public void carregarVetorNumerico(){
    byte somador = 1;
    for(byte i=0; i<TAMANHO_VETORES; i+=2){
      numeros[i]=somador;
      numeros[i+1]=somador;
      somador++;
    }
  }//fim metodo carregarVetorNumerico

  /********************************************************************
  * Metodo: embaralharVetorNumerico
  * Funcao: embaralha o vetor numerico para garantir jogos posicaos 
  *         diferentes nos botoes
  * Parametros: nenhum
  * Retorno: void
  ********************************************************************/
  public void embaralharVetorNumerico(){
    Random aleatorio = new Random();
    byte temporario;
    byte numeroAleatorio;

    for(byte i=0; i<TAMANHO_VETORES; i++){
      numeroAleatorio = (byte) aleatorio.nextInt(TAMANHO_VETORES);

      temporario = numeros[i];
      numeros[i]=numeros[numeroAleatorio];
      numeros[numeroAleatorio]=temporario;      
    }
  }//fim metodo embaralharVetorNumerico

  /********************************************************************
  * Metodo: alterarPontuacao
  * Funcao: calcula a pontuacao de acordo com o numero de erros e altera
            o texto da labelPontuacao
  * Parametros:
  * Retorno: void
  ********************************************************************/
  public void alterarPontuacao(){
    /*
    o if garante que a pontuacao minima por acerto seja igual a 50
    caso o numero de acerto seja maior ou igual a 100
    */
    if(numeroErros<=100){
      pontuacao +=100 - (numeroErros/2);
    }
    else{
      pontuacao +=50;
    }    
    labelPontuacao.setText(Integer.toString(pontuacao));
  }//fim metodo alterarPontuacao

  /********************************************************************
  * Metodo: carregarVetorBotoes
  * Funcao: inicializa um vetor de Button com os botoes do fxml
  * Parametros: nenhum
  * Retorno: void
  ********************************************************************/
  public void carregarVetorBotoes(){
    botoes = new Button[TAMANHO_VETORES];
    botoes[0]=botao1; 
    botoes[1]=botao2; 
    botoes[2]=botao3; 
    botoes[3]=botao4;
    botoes[4]=botao5; 
    botoes[5]=botao6;  
    botoes[6]=botao7;
    botoes[7]=botao8; 
    botoes[8]=botao9;
    botoes[9]=botao10; 
    botoes[10]=botao11;
    botoes[11]=botao12; 
    botoes[12]=botao13;  
    botoes[13]=botao14;
    botoes[14]=botao15; 
    botoes[15]=botao16; 
    botoes[16]=botao17;
    botoes[17]=botao18; 
    botoes[18]=botao19; 
    botoes[19]=botao20;
  }//fim metodo carregarVetorBotoes

}//fim classe PrincipalController
