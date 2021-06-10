mkdir bin\view bin\recursos\imagens bin\recursos\sons
javac -d bin Principal.java
Copy view\*.fxml bin\view
Copy recursos\imagens\*.png bin\recursos\imagens
Copy recursos\sons\Hall_of_the_Mountain_King_cortado.mp3 bin\recursos\sons
cd bin
java Principal