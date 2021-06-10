mkdir bin\view bin\recursos\imagens
javac -d bin Principal.java
Copy view\*.fxml bin\view
Copy recursos\imagens\*.png bin\recursos\imagens
cd bin
java Principal