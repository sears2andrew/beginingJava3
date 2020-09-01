//this program was written by andrew sears
import java.io.File;		
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class student {
   public static ArrayList<String> ids() throws FileNotFoundException{
      Scanner inputFile = new Scanner(new File("input data files/students.txt"));	//open student input file
      ArrayList<String> idData = new <String>ArrayList();		//create arraylist to hold data
      while(inputFile.hasNextLine()){							//while inputfile has next line
         String line = inputFile.nextLine();					//read in next line
         String[] data = line.split(" ");					//split data when a space is present
         for(int b = 0; b < 3; b++)							//loop for each field collected per student
            idData.add(data[b]);							//feed array into arraylist
      }
      inputFile.close();										//close input file
      idData.add("finished");									//set and end to the arraylist
      return idData;											//return arraylist
   }
}