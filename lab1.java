// Group 5 - Lab 1: Programming Skills Assessment
// Group Members: Andres Iglesias, Diana Islava Rauda, Pamela Herrera
// Lab Description: 
// Change Log History:

// Imports as needed
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

// Chesspiece Class: Represents a chess piece with its attributes
class Chesspiece{
    public String pieceName;
    public String color;
    public char posX;
    public int posY;

}

// Main Method: Reads files, takes user input, verifies chess piece moves, and prints results
public class lab1 {
    public static void main(String[] args){
        // Declare the array to hold the chesspieces and a counter to keep track of how many pieces are read and stored from the file
        Chesspiece[] pieces = new Chesspiece[6];
        int counter = 0;
        
        // Try and Cactch block to handle exceptions
        try{
                // Scanner to read the file with the chesspieces to test
            Scanner reader = new Scanner(new File("chesspieces.txt"));

            // 1. Read values from the file
            while(reader.hasNextLine()){
                String line = reader.nextLine();
                // Separate by the commas and get the name, color, position X and Position Y
                // Use the split() to separate the string by commas
                String[] parts = line.split(",");

                // Create an object / structure for each piece to hold all the attributes of the chesspiece
                // Use the trim() to remove any whitespace from the string
                Chesspiece piece = new Chesspiece();
                piece.pieceName = parts[0];
                piece.color = parts[1].trim();
                piece.posX = parts[2].trim().toUpperCase().charAt(0);
                piece.posY = Integer.parseInt(parts[3].trim());

                // 3. Allocate all the object / structure holding chess pieces in an array
                pieces[counter] = piece;
                counter++;
            }
            reader.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found!");
            return;
        } catch(Exception e){
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }
        

        // 4. Prompt the user only once to enter a new position (X, Y)
        // Scanner to read user input to get the target position
        Scanner readerUser = new Scanner(System.in);
        System.out.println("Enter the new position (e.g., E, 1)");
        String targetPosition = readerUser.nextLine();

        // Separate user input by the comma by using the trim() to remove whitespace from the String, as we did with the file input
        String[] targetPositionParts = targetPosition.split(",");
        char targetPosX = targetPositionParts[0].trim().toUpperCase().charAt(0);
        int targetPosY = Integer.parseInt(targetPositionParts[1].trim());
        readerUser.close();

        // Verify that the position is within chessboard limits
        // If position is not on chessboard, end the program
        if(targetPosX < 'A' || targetPosX > 'H' || targetPosY < 1 || targetPosY > 8){
            System.out.println("Invalid position. Out of chessboard limits. Please enter a position between A-H and 1-8.");
            return;
        }

        // 5. Traverse the array, verifying a move for each piece from its current position to the new target position entered by the user. Again, you will use the same target position entered to verify all pieces
        for(int i = 0; i < counter; i++){
            Chesspiece piece = pieces[i];
            boolean validMove = false;
        
                // Check the difference from both positions
                int differenceX = targetPosX - piece.posX;
                if(differenceX < 0) {
                    differenceX = -differenceX;
                }

                int differenceY = targetPosY - piece.posY;
                if(differenceY < 0) {
                    differenceY = -differenceY;
                }

                // If it stays in the same position, it's not a valid move
                if(differenceX == 0 && differenceY == 0){
                    validMove = false; 
                } else{
                    String pieceName = piece.pieceName.toUpperCase();
                    // Check the conditionals to match the piece name and verify if the move is valid
                    if(pieceName.equals("KING")){
                        if(differenceX <= 1 && differenceY <= 1){
                            validMove = true;
                        }
                    } else if(pieceName.equals("ROOK")){
                        if(differenceX == 0 || differenceY == 0){
                            validMove = true;
                        }
                    } else if(pieceName.equals("QUEEN")){
                        if(differenceX == 0 || differenceY == 0 || differenceX == differenceY){
                            validMove = true;
                        }
                    } else if(pieceName.equals("BISHOP")){
                        if(differenceX == differenceY){
                            validMove = true;
                        }
                    } else if(pieceName.equals("KNIGHT")){
                        if(differenceX == 1 && differenceY == 2 || differenceX == 2 && differenceY == 1){
                            validMove = true;
                        }
                    } else if(pieceName.equals("PAWN")){
                        if(differenceX == 0 && (targetPosY - piece.posY == 1)){
                            validMove = true;
                        }
                    }
                }
                // Step 6. Print the verification result for each piece
                if(validMove){
                System.out.println(piece.pieceName + " at " + piece.posX + ", " + piece.posY + " can move to " + targetPosX + ", " + targetPosY);
            } else{
                System.out.println(piece.pieceName + " at " + piece.posX + ", " + piece.posY + " cannot move to " + targetPosX + ", " + targetPosY);    
            }
        }       
    }
}
