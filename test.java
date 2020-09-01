//this program was written by andrew sears
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class test {														//open class
   public static ArrayList<String> scores() throws FileNotFoundException{		//open main methoid
      Scanner inputFile = new Scanner(new File("input data files/rawTests.txt"));	//open inputfile
      String line = inputFile.nextLine();								//read in next line of file
      ArrayList<Character> keyData = new <Character>ArrayList();		//create keydata arraylist
      ArrayList<String> finishedData = new <String>ArrayList();		//create finishedData arraylist
      String[] data = line.split(";");								//split read in data at ;
      int length = data[1].length();									//take length of data[1] to determin loop length
      if(Integer.valueOf(data[0]) == 0){								//if the id is 0 for student
         for(int i = 0; i < length; i++)								//for length of data[1]
            keyData.add(data[1].charAt(i));							//add key data to keyData
      }																//end if statment
      while(inputFile.hasNextLine()) {								//while inputfile has more data to read
         String studentline = inputFile.nextLine();					//take next line of file
         ArrayList<Character> studentData = new <Character>ArrayList();	//create student data arraylist
         String[] studentTest = studentline.split(";");				//split at ;
         int studentLength = studentTest[1].length();				//find length of studenTest[1]
         double Fscore = 0;											//set variable Fscore
         for(int i = 0; i < studentLength; i++){						//loop for length fo studnetTest[1]
            studentData.add(studentTest[1].charAt(i));				//add char data to arraylist
            if(keyData.get(i) == studentData.get(i))				//if data is same as key
               Fscore += 1;										//add 1 to score
            else if(studentData.get(i) == ' ');						//if data was blank do nothing
            else 													
               Fscore -= .25;										//if wrong data the subtract from score
         }															//end for loop					
         finishedData.add(studentTest[0]);							//add id to arraylist
         finishedData.add(String.valueOf(Fscore));					//add score to arraylist
         String[] gradeChar = {"A","A-","B+","B","B-","C+","C","C-","D","F"};	//create array for grade strings
         double d = 0;												//set variable d
         for(int y = 0; y < 10;y++){									//loop 10 times
            if(y == 9)												//if last loop
               d = 46.01;											//set d to 46.01
            if(Double.valueOf(String.valueOf(Fscore)) >= 46.01-d){	//if score is grater then 46.01-d
               finishedData.add(gradeChar[y]);						//add coresponding grade string to arraylist
               y = 10;												//set y to 10 and end loop
            }														//end if statment
            if( y == 0)												//if first loop
               d += .01;											//add .01 to d
            d +=2;													//add 2 to d
         }															//end for loop
      }																//end while loop
      inputFile.close();												//close inputfile
      finishedData.add("end");										//add end to finished data arraylist
      return finishedData;											//return finished data arraylist
   }
}