# Othello Game

Welcome to the Othello Game, a two-player strategy game featuring an interactive GUI, multiple gameplay modes, and extendable features. This project uses Java with the MVC architecture and popular design patterns to deliver a clean and maintainable experience.

## Features

- **Game Modes**:
  - Human vs. Human
  - Human vs. Computer (Greedy and Random strategies)
  - Computer vs. Computer (Greedy vs. Random)

- **Interactive GUI**:
  - Playable board with clickable tiles.
  - Restart the game with a single click.
  - Undo/redo functionality for better gameplay control.

- **Flexible Design**:
  - Strategy Pattern for selecting player behavior (Human, Greedy, Random).
  - Command Pattern to easily undo/redo moves.
  - Visitor Pattern for efficient operations on the game board.

## Running the Game

1. **Clone the Repository**:
   - Clone this project to your local machine using Git.

2. **Build the Project**:
   - Open the project in your IDE (preferably Intellij IDEA).
   - Build the dependencies using Maven with the `pom.xml` file.

3. **Run/Debug Configuration**:
   - Go to your IDE's run/debug configurations and add the following VM options:
     ```
     --module-path /library/java/JavaVirtualMachines/javafx-sdk-23.0.1/lib --add-modules javafx.controls,javafx.fxml
     ```

4. **Start the Game**:
   - Run the main class (e.g., `OApplication`) to begin the game.

## Requirements

- **Java Version**: Java 23
- **IDE**: Intellij IDEA (or any IDE that supports Maven)

