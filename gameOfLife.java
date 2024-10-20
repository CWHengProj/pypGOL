

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class gameOfLife{  
    public static void main(String[] args) throws FileNotFoundException{
        //write program to read the file
        File file = new File(".\\glider.gol");
        Scanner scanner = new Scanner(file);
        int i=0;    
        int j=0;
        int x=0;
        int y=0;
        int genCount=0;
        List<String> charList =new ArrayList<>();
        while (scanner.hasNext()){
            String content = scanner.nextLine();
            if (content.contains("GRID")){
                //subtract each one by one to create the list
                String[] gridSize = content.split(" ");
                x = Integer.parseInt(gridSize[1]);
                y = Integer.parseInt(gridSize[2]);
            }
            if (content.contains("START")){
                String[] startPosition = content.split(" ");
                i = Integer.parseInt(startPosition[1]);
                j = Integer.parseInt(startPosition[2]);
                
            }
            if (content.contains("*")){
                //split by characters if contains star or not. start assigning values to the map
                charList.add(content);
            }
        }
        int[][] map = new int[x][y];
        //assign values into the map, compensate based on the grid position
        //for each char in the char array, if char is *, add 1 into the position after compensation
        int rw=0;
        int col=0;
        for (String line : charList){
            for (char c: line.toCharArray()){
                if (c=='*'){
                    map[rw+i][col+j]= 1;
                    col++;
                }
            }
            rw++;
        }
        //everything that is empty is ran through again to set to 0
        for (int r=0; r<map.length; r++){
            for (int c=0; c<map[r].length; c++){
                if (map[r][c] != 1){
                    map[r][c]=0;
                }
                System.out.printf("%d",map[r][c]);
            }
            System.out.println();
        }
        //reset for the next gen evaluation 
        
        do{
            System.out.println("This is the generated map");
            for (int row = 0; row<x; row++){
                for (int column=0; column<y; column++){
                    System.out.printf("%d",map[row][column]);
                }
                System.out.println();
            }

            int[][] nextGenMap = map;
            //run through the matrix to check the surrounding values
            for (int row=0; row<x;row++){
                for(int column=0; column<y;column++){
                    int sum =0;
                    //if not within range, do not add to the sum. if same, do not add to the sum.
                    for (int rowmod= row-1; rowmod<=row+1; rowmod++){
                        for(int columnmod=column-1;columnmod<=column+1;columnmod++){
                            if (rowmod < 0 || rowmod >= x || columnmod < 0 || columnmod >= y) {
                                continue;
                            }
                            else if(rowmod==row && columnmod==column){
                                continue;
                            }
                            else{
                                sum+=nextGenMap[rowmod][columnmod];
                            }
                        }
                    }




                    //evaluate sum to generate the seed
                    if (sum<2){
                        nextGenMap[row][column]=0;
                    }
                    else if (sum>2 && sum<4){
                        nextGenMap[row][column]=1;
                    }
                    else{
                        nextGenMap[row][column]=0;
                    }
                    System.out.printf("%d",nextGenMap[row][column]);
                }
                System.out.println();
            }
                    
                
                //print output to represent the results
            genCount++;
            System.err.printf("Generation count: %d\n",genCount);
            Console console = System.console();
            String userInput = console.readLine("Run another generation? Y/N\n");
            //give generation count, and option to exit or run simulation again
            if (!userInput.equalsIgnoreCase("y")){
                break;
            }
        }
        while (true);
        scanner.close();
        System.out.println("Exiting program...");
    }
        }