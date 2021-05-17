@mkdir bin\view bin\resources\images
@Copy view\*.fxml bin\view
@Copy resources\images\*.png bin\resources\images
javac -d bin Principal.java
@cd bin
java Principal