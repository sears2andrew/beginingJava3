//this program was written by andrew sears
import java.io.FileWriter;		
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList; 

public class prog3main {											//open class
   public static void main(String[] args) throws IOException {		//open main methoid
      PrintWriter outputFile = new PrintWriter(new FileWriter("gradeData.txt"));	//open grade outputfile
      PrintWriter errorFile = new PrintWriter(new FileWriter("errors.txt"));		//open error outputfile
      ArrayList<String> finishedData = test.scores();				//create string arraylist with calling test class
      ArrayList<String> idData = student.ids();					//creat string arraylist with calling id calss
      outputFile.printf("%-12s %-12s %-4s %-5s\n", "First Name" , "Last Name" , "Avg" , "Grade");		//create heading for outputfile
      outputFile.println("====================================");
      int i = 0;													//set variable i
      int[] gradeData = new int[10];								//create array to hold count for grade
      String[] gradeChar = {"A","A-","B+","B","B-","C+","C","C-","D","F"};	//create strings for display
      while(finishedData.get(i) != "end"){						//while finished data does not = string
         int j = 0;												//set variable j
         int printed = 0;										//set variable printed
         while(idData.get(j) != "finished"){						//while id data does not = string
            int count = 0;										//set variable count
            if(idData.get(j).equals(finishedData.get(i))) {		//if the string are equal
               for( int t = 0; t < 4 ; t++) {					//loop 4 times holding a base number
                  for( int y = 0; y < 4; y++) {				//loop 4 times compairing to base
                     if(String.valueOf(idData.get(j).charAt(y+2)).equals(String.valueOf(idData.get(j).charAt(t+2))) && t != y) {	//if numbers equal and not at same spot in string
                        errorFile.println(idData.get(j) + " is invalid - Duplicate digits detected.");	//print to error file
                        count++;						//add 1 to count
                        y=4;							//set y to 4 to end loop
                        t=4;							//set t to 4 to end loop
                     }										//end if
                  }											//end for loop
               }												//end for loop
               boolean charError = false;						//set char error to false
               boolean numError = false;						//set numerror to false
               int holder = 0;									//set variable holder
               for(int o = 0; o < 2; o++) {					//loop 2 time for first 2 char is string
                  if((int)idData.get(j).charAt(o) < 58) 		//check that they are not letters
                     charError = true;						//set error to true
               }												//end for loop
               for(int l = 0; l < 4; l++) {					//loop 4 times for nubers in string
                  if((int)idData.get(j).charAt(l+2) > 58) {	//if they are determined to be letters
                     numError = true;						//set error to true
                     holder = l+2;							//determine where the error occured
                  }											//close if statment
               }												//close for statment
               if (numError == true && charError == false) {	//if both errors occured
                  errorFile.println(idData.get(j) + " is invalid - characters in the wrong order");	//print to error file
                  count++;									//add one to count
               }												//close if statment
               else if(numError == true) {						//if only a number error occured
                  errorFile.println(idData.get(j) + " is invalid - character '" + idData.get(j).charAt(holder) + "' should be a digit");	//print to error file
                  count++;									//add one to count
               }												//close if statement
               if(idData.get(j).length() > 6) {				//if length of id string ins too long
                  errorFile.println(idData.get(j) + " is invalid - too many characters.");	//print to error file
                  count++;									//add one to count
               }												//close if statemnt
               if((int)idData.get(j).charAt(0) == (int)idData.get(j).charAt(1)) {		//if the letters are the same
                  errorFile.println(idData.get(j)+ " is invalid - First and second characters are the same.");	//print to errorfile
                  count++;									//add one to count
               }												//close if stament
               if(count == 0) {								//if no errors had occured
                  outputFile.printf("%-12s %-12s %.2f %-5s\n", idData.get(j+1), idData.get(j+2) , Double.valueOf(finishedData.get(i+1)) , finishedData.get(i+2));	//errorfile print
                  double d = 0;								//set variable d
                  for(int y = 0; y < 10;y++){					//loop 10 times
                     if(y == 9)								//if last time looping
                        d = 46.01;							//set d to 46.01
                     if(Double.valueOf(finishedData.get(i+1)) >= 46.01-d){	//if grade is greater then 46.01-d
                        gradeData[y]++;						//add 1 to which grade occured
                        y = 10;								//set y to 10 and end loop
                     }										//end if statment
                     if( y == 0)								//if first time looping
                        d += .01;							//add to d to make whole numbers
                     d +=2;									//add 2 to d to check for next grade
                  }											//end for loop
               }												//end if statment
               printed = 1;									//set variable printed to 1 if id was used
            }													//end if statment
            j += 3;												//add 3 to j for arraylist location
         }														//end while loop
         if(printed == 0)										//if id was not used
            errorFile.println("ID: " + finishedData.get(i) + " - no name found " + finishedData.get(i+1) + " " + finishedData.get(i+2));	//print to error file
         i += 3;													//add 3 to i for arraylsit location
      }															//end while loop
      outputFile.printf("\n%-5s %-5s %-4s\n", "Grade" , "Count" , "Pct.");	//print heading for statistics table
      outputFile.println("===================");
      double total = 0;											//set variable total
      for(int w = 0; w < 10; w++) {								//loop for each possible grade
         total += gradeData[w];									//add amount grade occured for each grade
      }															//end for loop
      for(int s = 0; s < 10; s++) {								//loop for each grade
         outputFile.printf("  %-5s%2s %6.2f%1s \n", gradeChar[s] , gradeData[s] , gradeData[s]/total*100 , "%");		//pritn to output file stats table
      }															//end for loop
      outputFile.close();											//close outputfile
      errorFile.close();											//close error file
   }																//close main methoid
}																	//close class