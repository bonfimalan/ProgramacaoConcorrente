mkdir bin\view bin\recursos\imagens
javac -d bin Principal.java
Copy recursos\imagens\*.png bin\recursos\imagens
Copy view\*.fxml bin\view
cd bin
java Principal