/* ***************************************************************
* Autor: Alan Bonfim Santos
* Matricula: 201911912
* Inicio: 24/03/2021 21:05
* Ultima alteracao: 28/03/2021 15:04
* Nome: PrincipalControlador.java
* Funcao: responsavel por alterar e gerenciar o fxml
*************************************************************** */
package controles;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;

import threads.*;

public class PrincipalControlador implements Initializable{
  //ImageView presentes na arvore
  @FXML ImageView imageViewPai;
  @FXML ImageView imageViewPrimeiroFilho;
  @FXML ImageView imageViewSegundoFilho;
  @FXML ImageView imageViewTerceiroFilho;
  @FXML ImageView imageViewPrimeiroNeto;
  @FXML ImageView imageViewSegundoNeto;
  @FXML ImageView imageViewBisneto;

  //ImageView presentes ao lado da idade
  @FXML ImageView imgLapidePai;
  @FXML ImageView imgLapidePrimeiroFilho;
  @FXML ImageView imgLapideSegundoFilho;
  @FXML ImageView imgLapideTerceiroFilho;
  @FXML ImageView imgLapidePrimeiroNeto;
  @FXML ImageView imgLapideSegundoNeto;
  @FXML ImageView imgLapideBisneto;

  //Text que estao em baixo das imagens
  @FXML Text txtPai;
  @FXML Text txtPrimeiroFilho;
  @FXML Text txtSegundoFilho;
  @FXML Text txtTerceiroFilho;
  @FXML Text txtPrimeiroNeto;
  @FXML Text txtSegundoNeto;
  @FXML Text txtBisneto;

  //Text que mostram as idades
  @FXML Text txtIdadePai;
  @FXML Text txtIdadePrimeiroFilho;
  @FXML Text txtIdadeSegundoFilho;
  @FXML Text txtIdadeTerceiroFilho;
  @FXML Text txtIdadePrimeiroNeto;
  @FXML Text txtIdadeSegundoNeto;
  @FXML Text txtIdadeBisneto;

  //relacionados com o controle do volume
  @FXML HBox hBoxVolume;
  @FXML Text txtVolume;
  private int volume;

  //instanciando as threads
  private ThreadPai pai;
  private ThreadPrimeiroFilho primeiroFilho;
  private ThreadSegundoFilho segundoFilho;
  private ThreadTerceiroFilho terceiroFilho;
  private ThreadPrimeiroNeto primeiroNeto;
  private ThreadSegundoNeto segundoNeto;
  private ThreadBisneto bisneto;

  private MediaPlayer musica;

  public PrincipalControlador(){
    //criando as threads
    this.pai = new ThreadPai();
    this.primeiroFilho = new ThreadPrimeiroFilho();
    this.segundoFilho = new ThreadSegundoFilho();
    this.terceiroFilho = new ThreadTerceiroFilho();
    this.primeiroNeto = new ThreadPrimeiroNeto();
    this.segundoNeto = new ThreadSegundoNeto();
    this.bisneto = new ThreadBisneto();
  }

  /* ***************************************************************
  * Metodo: initialize
  * Funcao: inicia o controlador e nesse caso passa a instancia do
  *         controlador para as threads
  * Parametros: 
  * Retorno: void
  *************************************************************** */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    volume = 2;
    txtVolume.setText(String.valueOf(volume));
    hBoxVolume.setDisable(true);

    //passando o contralador para as threads
    pai.setControlador(this);
    primeiroFilho.setControlador(this);
    segundoFilho.setControlador(this);
    terceiroFilho.setControlador(this);
    primeiroNeto.setControlador(this);
    segundoNeto.setControlador(this);
    bisneto.setControlador(this);
  }//fim metodo initialize
  
  /* ***************************************************************
  * Metodo: iniciar
  * Funcao: iniciar a musica e a thread pai, que ira iniciar as outras
  * Parametros: recebe um evento que eh o clique do usuario no botao
  * Retorno: void
  *************************************************************** */
  @FXML
  public void iniciar(ActionEvent evento){
    Button botao = (Button) evento.getSource();
    botao.setDisable(true);

    tocarMusica();
    hBoxVolume.setDisable(false);

    txtPai.setVisible(true);
    pai.start();//iniciando a thread pai
  }//fim metodo iniciar

  /* ***************************************************************
  * Metodo: tocarMusica
  * Funcao: configura o objeto musica da classe MediaPlayer que eh
  *         responsavel por tocar a musica
  * Parametros: nenhum
  * Retorno: void
  *************************************************************** */
  public void tocarMusica(){
    Media midia = new Media(Paths.get("recursos/sons/Hall_of_the_Mountain_King_cortado.mp3").toUri().toString());
    musica = new MediaPlayer(midia);
    musica.setVolume(0.2);
    musica.play();
  }//fim metodo tocarMusica

  /* ***************************************************************
  * Metodo: aumentarVolume
  * Funcao: aumenta o volume da musica
  * Parametros: recebe um evento que eh o clique do usuario no botao
  * Retorno: void
  *************************************************************** */
  @FXML
  public void aumentarVolume(ActionEvent evento){
    if(volume<10){
      volume++;
      txtVolume.setText(String.valueOf(volume));

      musica.setVolume((double) volume/10);
    }
  }

  /* ***************************************************************
  * Metodo: diminuirVolume
  * Funcao: diminue o volume da musica
  * Parametros: recebe um evento que eh o clique do usuario no botao
  * Retorno: void
  *************************************************************** */
  @FXML
  public void diminuirVolume(ActionEvent evento){
    if(volume>0){
      volume--;
      txtVolume.setText(String.valueOf(volume));
      if(volume !=0)
        musica.setVolume((double) volume/10);
      else
        musica.setVolume(0);
    }
  }

  //metodos para mudar as imagens na view
  public void alterarImgPai(String local){
    imageViewPai.setImage(new Image(local));
  }

  public void alterarImgPrimeiroFilho(String local){
    imageViewPrimeiroFilho.setImage(new Image(local));
  }
  
  public void alterarImgSegundoFilho(String local){
    imageViewSegundoFilho.setImage(new Image(local));
  }

  public void alterarImgTerceiroFilho(String local){
    imageViewTerceiroFilho.setImage(new Image(local));
  }
  
  public void alterarImgPrimeiroNeto(String local){
    imageViewPrimeiroNeto.setImage(new Image(local));
  }
  
  public void alterarImgSegundoNeto(String local){
    imageViewSegundoNeto.setImage(new Image(local));
  }
  
  public void alterarImgBisneto(String local){
    imageViewBisneto.setImage(new Image(local));
  }

  //metodos para alterar as imagens dos ImageView que ficam ao lado da idade
  public void alterarLapideImgPai(String local){
    imgLapidePai.setImage(new Image(local));
  }

  public void alterarImgLapidePrimeiroFilho(String local){
    imgLapidePrimeiroFilho.setImage(new Image(local));
  }
  
  public void alterarImgLapideSegundoFilho(String local){
    imgLapideSegundoFilho.setImage(new Image(local));
  }

  public void alterarImgLapideTerceiroFilho(String local){
    imgLapideTerceiroFilho.setImage(new Image(local));
  }
  
  public void alterarImgLapidePrimeiroNeto(String local){
    imgLapidePrimeiroNeto.setImage(new Image(local));
  }
  
  public void alterarImgLapideSegundoNeto(String local){
    imgLapideSegundoNeto.setImage(new Image(local));
  }
  
  public void alterarImgLapideBisneto(String local){
    imgLapideBisneto.setImage(new Image(local));
  }

  //todos os metodos para atualizar a idade na view
  public void atualizaIdadePai(int idade){
    txtIdadePai.setText(String.valueOf(idade));
  }

  public void atualizaIdadePrimeiroFilho(int idade){
    txtIdadePrimeiroFilho.setText(String.valueOf(idade));
  }

  public void atualizaIdadeSegundoFilho(int idade){
    txtIdadeSegundoFilho.setText(String.valueOf(idade));
  }

  public void atualizaIdadeTerceiroFilho(int idade){
    txtIdadeTerceiroFilho.setText(String.valueOf(idade));
  }

  public void atualizaIdadePrimeiroNeto(int idade){
    txtIdadePrimeiroNeto.setText(String.valueOf(idade));
  }

  public void atualizaIdadeSegundoNeto(int idade){
    txtIdadeSegundoNeto.setText(String.valueOf(idade));
  }

  public void atualizaIdadeBisneto(int idade){
    txtIdadeBisneto.setText(String.valueOf(idade));
  }
  

  //todos os metodos ate iniciarBisneto sao metodos para iniciar as threads e tonar os nomes visiveis
  public void iniciarPrimeiroFilho(){
    txtPrimeiroFilho.setVisible(true);
    primeiroFilho.start();
  }

  public void iniciarSegundoFilho(){
    txtSegundoFilho.setVisible(true);
    segundoFilho.start();
  }

  public void iniciarTerceiroFilho(){
    txtTerceiroFilho.setVisible(true);
    terceiroFilho.start();
  }

  public void iniciarPrimeiroNeto(){
    txtPrimeiroNeto.setVisible(true);
    primeiroNeto.start();
  }

  public void iniciarSegundoNeto(){
    txtSegundoNeto.setVisible(true);
    segundoNeto.start();
  }

  public void iniciarBisneto(){
    txtBisneto.setVisible(true);
    bisneto.start();
  }
}
