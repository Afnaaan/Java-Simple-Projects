/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author Afnaan
 */
public class Task {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String filePath = "E:\\ITI\\7 - Java and UML_Amr Elshafey\\Day2\\pyramids.csv";
        PyramidCSVDAO pyramidCSVDAO = new PyramidCSVDAO();
        
        try
        {
            ArrayList<Pyramid> pyramids = pyramidCSVDAO.readPyramidsFromCSV(filePath);
            for (int i = 0; i < pyramids.size(); i++)
            {
                System.out.println("#" + i+ pyramids.get(i));
            }
        } 
        catch (FileNotFoundException e) 
        {
          System.out.println("An error occurred. FileNotFound");
          e.printStackTrace();
        }
        catch (NumberFormatException e)
        {
            System.out.println("An error occurred. NumberFormat");
            e.printStackTrace();
        }
    }
}