mkdir bin\view bin\recursos\imagens
Copy view\*.fxml bin\view
Copy recursos\imagens\*.png bin\recursos\imagens
javac -d bin Principal.java
cd bin
java Principal