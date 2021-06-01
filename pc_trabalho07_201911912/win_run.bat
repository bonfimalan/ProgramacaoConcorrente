mkdir bin\view bin\resources\images 
javac -d bin Principal.java
Copy view\*.fxml bin\view
Copy resources\images\*.png bin\resources\images
Copy resources\images\*.gif bin\resources\images
cd bin
java Principal