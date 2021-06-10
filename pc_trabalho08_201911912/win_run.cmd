mkdir bin\views bin\resources\images
javac -d bin Principal.java
Copy views\*.fxml bin\views
Copy resources\images\*.png bin\resources\images
cd bin
java Principal