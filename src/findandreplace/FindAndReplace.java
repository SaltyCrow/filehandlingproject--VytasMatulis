/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package findandreplace;

//imports
import java.io.*;
import java.util.*;

/**
 *
 * @author vymat7603
 * Purpose: User can create and edit text files
 */
public class FindAndReplace {

    /**
     * @param args the command line arguments
     */
    
    //to do: split the larger methods into smaller methods, improve efficiency
    
    //global objects
    static Scanner sc = new Scanner(System.in);
    
    static FileReader in;
    static BufferedReader readFile;
        
    static FileWriter out;
    static BufferedWriter writeFile;
    
    //creates a menu for the user 
    public static int menu()//no parameters
    {
        //variables
        String input;

        int output = 0;
        
        //print menu 
        System.out.println("Menu");
        System.out.println("1: Create new file");
        System.out.println("2: Edit a file");
        System.out.println("3: Find and replace words in a file");
        System.out.println("4: Exit the program");
       
        try {
            
            //get input and turn it into integer
            input = sc.nextLine();
            output = Integer.parseInt(input);
            
            //if integer is not one of the four options
            if (output!= 4 && output != 3&& output != 2 && output!= 1)
            {
                
                //output
                System.out.println("Please enter one of the options");
                
                //restart the method
                menu();   
            }
        }
        
        //if the input is not an integer
        catch(NumberFormatException e)
        {
            
            //output 
            System.out.println("Please enter one of the options");
            
            //recursive
            menu(); 
        }
        
        //returns the option
        return output;
    }
    
    
    
    //creates a file with the user input as the name
    public static void createFile()//no parameters
    {
        //variables  
        String input;
        
        File file;
        
        //output
        System.out.println("Please enter a name for your file. Do not include the file type");

        //get input and assign input as the name of the file
        input = sc.nextLine();
        file = new File (input + ".txt"); 
        
        //check if a file with name input already exists
        if ((file.exists())!= true)
        {
            
            //creates the file
            try 
            {
                
                file.createNewFile();
                System.out.println("The file was created."); //output           
            }  
            
            //catches any exceptions with creating the file
            catch (IOException e) 
            {
                
                //output
                System.out.println("The file could not be created.");
                System.err.println("IOException: " + e.getMessage());
            }
        }
        
        //if the file already exists
        else
        {
            
            //output
            System.out.println("The file already exists");
        }
        
    }
    
    //creates a file called the input. Overwrites the file if the file already exists
    public static void createFile2(String input) //parameters: name of the file
    {
        
        //variables
        File file = new File (input + ".txt"); 

        try 
        {
            
            //creates a new file called input in the project's folder
            file.createNewFile();
        }  
        
        //catch any exceptions when creating the file and displays them to user
        catch (IOException e)
        {
            
            //output
            System.out.println("The file could not be created.");
            System.err.println("IOException: " + e.getMessage());
        } 
    }
    
    
    //edits the file 
    public static void editFile()//no parameters
    {
       
        //variables
        String input;
        String edit = "";   

        //output
        System.out.println("Please enter the name of the file you wish to edit");
        
        //user input
        input = sc.nextLine();
        
        //declare and assign file
        File file = new File(input + ".txt");
        
        //if the file named input already exists
        if (file.exists() == true)
        {
            
            //calls createfile method which will overwrite the file with the name input (will not overwrite if file does not exist)
            createFile2(input);
            
            //output
            System.out.println("Enter the new text of the file. All previous text will be overwritten. enter !stop when you are done editing"); 
       
            try
            {
                
                //create new file writer and pass it into a buffered writer
                out = new FileWriter(file);
                writeFile = new BufferedWriter(out);  

                do 
                {
                    //get user input
                    edit = sc.nextLine();
                    
                    //if user keeps wanting to enter (this if statement keeps !stop from being written to the file)
                    if (!"!stop".equals(edit))
                    {
                        
                        //write to file
                        writeFile.write(edit);
                        writeFile.newLine();
                    }
                }
                while (!"!stop".equals(edit)); //when the user wants to stop writing to the file


                //output
                System.out.println("Data Written to File");    

                //close stream
                writeFile.close();
            }

            //catch any exceptions when writing to the file and displays them to user
            catch (IOException f)
            {

                //output
                System.out.println("Problem writing to file.");
                System.err.println("IOException: " + f.getMessage());
            }  
        }
       
        else
        {
            
            //output
            System.out.println("This file doesn't exists");
            
            //restart method if the file doesnt exist
            editFile();
            
        }
    }
    
    //finds and replaces words from a file
    public static void findAndReplace()//no parameters
    {
        
        //variables
        String input;
        String find;
        String replace;
        String fileInput;
        
        ArrayList <String> arr1 = new ArrayList();
        

        //prompt user for name of file and get it from scanner
        System.out.println("Please enter the name of the file you wish to edit"); 
        input = sc.nextLine();
        
        //prompt user for word that will be replaced and get it from scanner
        System.out.println("Please enter the word you wish to replace");
        find = sc.nextLine();
        
        //prompt user for word that will be replaced and get it from scanner
        System.out.println("Please enter the word you wish to replace with");
        replace = sc.nextLine();
        
        //declare and assign file with input as the name
        File file = new File (input + ".txt");
 
        try{
                
            //asigns new file reader and pass it into a buffered writer
            in = new FileReader(file);
            readFile = new BufferedReader(in);
            
            //run while there are characters on the line
            while ((fileInput = readFile.readLine())!=null)
            {
                
                //add line to the arraylist
                arr1.add(fileInput);
                System.out.println(fileInput);
                
            }
 
            //iterates loop for each line
            for (int i = 0; i < arr1.size(); i++) {
  
                //replaces  the word in each element of the arraylist
                arr1.set(i, arr1.get(i).replace(find, replace));
            }
        }
        
        //catch any exceptions when finding the file and displays them to user
        catch(FileNotFoundException f){
                
            //output
            System.out.println("File does not exist or could not be found.");
            System.err.println("FileNotFoundException: " + f.getMessage()); 
        }
        
        //catch any exceptions when reading the file and displays them to user
        catch(IOException f){
            
            //output
            System.out.println("Problem reading file");
            System.err.println("IOException: " + f.getMessage());
        }
        
        //calls createfile method which will overwrite the file with the name input (will not overwrite if file does not exist)
        createFile2(input);
        
        try
        {
            
            //create new file writer and pass it into a buffered writer
            out = new FileWriter(file);
            writeFile = new BufferedWriter(out);
            
            //iterates loop for each line
            for (String arr11 : arr1) {
                
                //writes from corresponding element in the arraylist
                writeFile.write(arr11);
                writeFile.newLine();
            }
            
            //close stream
            out.close();
            writeFile.close();
            
            System.out.println("Data Written to File");
        
        }
        
        //catch any exceptions when writing to the file and displays them to user
        catch (IOException f)
        {
            
            System.out.println("Problem writing to file.");
            System.err.println("IOException: " + f.getMessage());
              
        } 
    }
    
    public static void main(String[] args) {
        
        //variables
        int option = 0;
        
        //if user enters 4 program will stop
        while (option!=4){
            
            //user options
            if (option == 1)
            {

                createFile();

            }
            else if(option == 2)
            {
                
                editFile();
                
            }
            else if(option == 3)
            {
                
                findAndReplace();
                
            }
            
            //after the program performs a task menu will come up again
            option = menu();
        }
        
        //close scanner
        sc.close();
        
        
    }
    
}
