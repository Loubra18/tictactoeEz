#Tic Tac Toe Game
Description
This project is a Tic Tac Toe game implemented in Java using the JavaFX framework for the graphical user interface. The game follows the MVC (Model-View-Controller) architecture to ensure a clean separation of concerns. It allows two players to play against each other, with features such as restarting the game, changing player symbols before the game starts, and displaying the current player's turn.


Features
Play a classic Tic Tac Toe game.
Restart the game at any time.
Change player symbols (X or O) before starting the game.
Display the current player's turn and symbol.
Simple and intuitive graphical interface.
Prerequisites
Before running the project, ensure you have the following installed:


Java Development Kit (JDK) 17 or later
Maven (for dependency management and building the project)
Installation
Clone the repository to your local machine:


git clone https://github.com/your-username/tictactoe.git
cd tictactoe
Build the project using Maven:


mvn clean install
Ensure all dependencies are correctly downloaded and the project is built successfully.


Running the Application
Navigate to the target directory:


cd target
Run the application using the following command:


java -jar tictactoe-1.0-SNAPSHOT.jar
Alternatively, if you are using an IDE like IntelliJ IDEA, you can run the GameController class directly.


Project Structure
src/main/java: Contains the source code.
model: Contains the game logic (e.g., Board, Player, State).
view: Contains the JavaFX view logic (e.g., GameViewController).
controller: Contains the controller logic (e.g., GameController).
src/main/resources: Contains the FXML files and other resources for the UI.
How to Play
Launch the application.
Choose the player symbols (X or O) before starting the game.
Click the Start button to begin the game.
Players take turns clicking on the grid to place their symbols.
The game ends when a player wins or the grid is full (draw).
Use the Restart button to start a new game.
License
This project is licensed under the MIT License. Feel free to use and modify it as needed.
