/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
Autor....: Alan Bonfim Santos
Matrícula: 201911912
Inicio...: 17/03/2021
Alteracao: 18/03/2021
Nome.....: Principal.cpp
Funcao...: Arvore genealogica com comando fork em cpp
=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
#include <unistd.h>
#include <iostream>
#include <time.h>

using namespace std;

int main (void) {
  pid_t idProcesso;
  cout << "Inicio do processo\n\n";
  idProcesso = fork();
  
  //Erro
  if(idProcesso==-1)
    exit(1);
  
  if(idProcesso==0){
    sleep(22);
    cout << "Nasce o primeiro filho, id pai = " << getppid() << " id filho = " << getpid() << "\n";
    idProcesso = fork();
    
    //tempo passado nesse processo ate aq = 22s
    
    //Erro
    if(idProcesso==-1)
      exit(1);
  
    //processo primeiro neto
    if(idProcesso == 0){
      sleep(16);
      cout << "Nasce o primeiro neto, id pai = " << getppid() << " id filho = " << getpid() << "\n";
      idProcesso = fork();
      
      //tempo passado nesse processo ate aq = 38s
      
      //Erro
      if(idProcesso==-1)
        exit(1);
  
      //processo bisneto
      if(idProcesso == 0){
        sleep(30);
        cout << "Nasce o bisneto, id pai = " << getppid() << " id filho = " << getpid() << "\n";
        
        //tempo passado nesse processo ate aq = 68s
        
        sleep(12);
        cout << "Morre o bisneto, id pai = " << getppid() << " id filho = " << getpid() << "\n";
        _exit(0);
        
      }//fim if (processo bisneto)
      else{
        sleep(35);
        cout << "Morre o primeiro neto, id pai = " << getppid() << " id filho = " << getpid() << "\n";
        //este sleep e' somente para que o bisneto tenha tempo de imprimir o id do processo pai
        //caso contrario o getppid() no processo bisneto retornara 1, ja que o processo ja tera sido encerrado
        sleep(10);  
        _exit(0);
      }//fim else (processo primeiro neto)
      
      
    }//fim if (processo primeiro neto)
    
    //processo primeiro filho
    else{
      sleep(61);
      cout << "Morre o primeiro filho, id pai = " << getppid() << " id filho = " << getpid() << "\n";
      _exit(0);
    }//fim else (processo primeiro filho)
  }//fim if (processo do primeiro filho)
  
  //processo do pai
  else{
    cout << "Nasce o pai, id = " << getpid() << "\n";
    sleep(25);
    idProcesso = fork();
    
    //tempo passado nesse processo ate aq = 25s
    
    //Erro
    if(idProcesso==-1)
      exit(1);
    
    //processo do segundo filho
    if(idProcesso == 0){
      cout << "Nasce o segundo filho, id pai = " << getppid() << " id filho = " << getpid() << "\n";
      sleep(20);
      idProcesso = fork();
      
      //tempo passado nesse processo ate aq = 45s
      
      //Erro
      if(idProcesso==-1)
        exit(1);
  
      //processo do segundo neto
      if(idProcesso == 0){
        cout << "Nasce o segundo neto, id pai = " << getppid() << " id filho = " << getpid() << "\n";
        sleep(33);
        cout << "Morre o segundo neto, id pai = " << getppid() << " id filho = " << getpid() << "\n";
        _exit(0);
        
        //tempo passado nesse processo ate aq = 78s
        
      }//fim if(processo do segundo neto)
      
      //processo do segundo filho
      else{
        sleep(35);
        cout << "Morre o segundo filho, id pai = " << getppid() << " id filho = " << getpid() << "\n";
        _exit(0);
        
        //tempo passado nesse processo ate aq = 55s
        
      }//fim else (processo do segundo filho)
    }//fim if (processo segundo filho)
    
    //processo do pai
    else{
      sleep(7);
      idProcesso = fork();
      
      //tempo passado nesse processo ate aq = 32s
      
      //Erro
      if(idProcesso==-1)
        exit(1);
  
      //processo do terceiro filho
      if(idProcesso == 0){
        cout << "Nasce o terceiro filho, id pai = " << getppid() << " id filho = " << getpid() << "\n";
        sleep(55);
        cout << "Morre o terceiro filho, id pai = " << getppid() << " id filho = " << getpid() << "\n";
        _exit(0);      
      }//fim if (processo terceiro filho)
      
      //processo do pai
      else{
        sleep(58);
        cout << "Morre o pai, id = " << getpid() << "\n";
        
        //tempo passado nesse processo ate aq = 90s
        
      }//fim else (processo pai)
    }//fim else (processo pai)
  }//fim else (processo pai)
  return 0;
}//fim do metodo main
