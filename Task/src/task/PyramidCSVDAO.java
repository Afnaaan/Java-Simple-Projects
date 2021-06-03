/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Afnaan
 */
public class PyramidCSVDAO 
{
    Scanner myReader ;
    
    private void read_csv(String fileName) throws FileNotFoundException
    {
        File myObj = new File(fileName); // Specify the filename
        myReader = new Scanner(myObj);
    }
    
    private void close_csv()
    {
        myReader.close();
    }
    
    public ArrayList<Pyramid> readPyramidsFromCSV (String fileName) throws FileNotFoundException
    {
        ArrayList<Pyramid> pyramids = new ArrayList<>();
        read_csv(fileName);
        String data;
        boolean isFirstLine = true;
        while (myReader.hasNextLine()) 
        {
            data = myReader.nextLine();
            if(isFirstLine)
                isFirstLine = false;
            else
                pyramids.add(createPyramid(data));
        }
        close_csv();
        return pyramids;
    }
    
    private Pyramid createPyramid(String input_str) throws NumberFormatException
    {
        String[] inputs = input_str.split(",", -1);
        Pyramid pyramid = new Pyramid();

        pyramid.setPharaoh(inputs[0]) ;
        pyramid.setModern_name(inputs[2]);
        pyramid.setSite(inputs[4]);
        if (inputs[7].length() != 0)
            pyramid.setHeight_in_m(Double.parseDouble(inputs[7]));
        
        return pyramid;
    }
}