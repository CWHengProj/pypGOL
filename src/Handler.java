package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Handler {
    private int[][] grid =null;
    private int[][] nextGrid=null;
    
    public void createGrid(String pathDir) throws FileNotFoundException {
        File file = new File(pathDir);
        Scanner scanner = new Scanner(file);
        int startRow = 0;
        int startColumn = 0;
        int j=0;
        while (scanner.hasNext()){
            String currLine =scanner.nextLine();
            if (currLine.startsWith("GRID")){
                String[] tempList = currLine.split(" ");
                int rowSize = Integer.parseInt(tempList[1]);
                int columnSize = Integer.parseInt(tempList[2]);
                grid = new int[rowSize][columnSize];
                nextGrid = new int[rowSize][columnSize];

            }
            else if (currLine.startsWith("START")){
                String[] tempList = currLine.split(" ");
                startRow = Integer.parseInt(tempList[2]);
                startColumn = Integer.parseInt(tempList[1]);
            }
            else if (currLine.contains("*")){
                char[] tempChar = currLine.toCharArray();
                for (int i=0; i<tempChar.length; i++){
                    int x = 0;
                    if (tempChar[i]=='*'){
                        x = 1;
                    }
                    else{
                        x =0;
                    }
                    grid[startRow+j][startColumn+i]= x;
                }
                j++;
            }
        }
        scanner.close();
        System.out.println("Iteration 1");
        for (int l=0; l<grid.length;l++){
            for(int k=0; k<grid[0].length;k++){
                System.out.print(grid[l][k]);
            }
            System.out.println();
        }
        }

    public void iterate(int numberIterations) {
        for (int iterNo =1; iterNo<numberIterations; iterNo++){
            //evaluate and create next version of grid
            System.out.printf("Iteration %d\n", iterNo+1);
            for (int a=0; a<grid.length; a++){
                for (int b=0; b<grid[a].length; b++){
                    //nextgrid overwrites the original grid
                    nextGrid[a][b] = evaluate(a,b);
                }
            }
            for (int[] line : nextGrid){
                for (int value: line){
                    
                    System.out.print(value);
                }
                System.out.println();
            }
            int[][] temp = grid;
            grid = nextGrid;
            nextGrid = temp;
        }
    }
    
    public int evaluate(int a, int b){
        //rules to determine survival of cell, update onto newGrid
        int sum=0;
        //summation function to return currpossum
        for (int tempA = a-1; tempA<=a+1; tempA++){
            for (int tempB = b-1; tempB<=b+1; tempB++ ){
                if (!(tempA<0 || tempB<0 || tempA>=grid.length || tempB>=grid[0].length)){
                    if (!(tempA==a && tempB==b)){
                        sum+=grid[tempA][tempB];
                    }
                }
            }
        }
        if (sum==3 && grid[a][b]==0){
            return 1;
        }
        if ((sum==2 || sum ==3) && grid[a][b]==1){
            return 1;
        }
        else{
            return 0;
        }
    }
    
}
