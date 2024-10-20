package src;

import java.io.FileNotFoundException;

public class gol{
    public static void main(String[] args) throws FileNotFoundException {
        //file IO to get grid size, starting position and pattern
        String pathDir = "C:\\Users\\Admin\\Desktop\\self practice\\gameoflife\\src\\glider.gol";
        Handler handler = new Handler();
        handler.createGrid(pathDir);
        //print 5 iterations
        handler.iterate(5);
    }
}
