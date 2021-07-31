package LabExercise1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;

public class CSVDAO<T extends Create<T>> {
    private Supplier<T> supplier;
    Scanner myReader ;

    public CSVDAO( Supplier<T> supplier)
    {
        this.supplier = supplier;
    }

    private void read_csv(String fileName) throws FileNotFoundException
    {
        File myObj = new File(fileName); // Specify the filename
        myReader = new Scanner(myObj);
    }

    private void close_csv()
    {
        myReader.close();
    }

    public List<T> readAllFromCSV (String fileName)/* throws FileNotFoundException*/
    {
        List<T> objects = new ArrayList<T>();
        try
        {
            read_csv(fileName);
            String data;
            boolean isFirstLine = true;
            while (myReader.hasNextLine())
            {
                data = myReader.nextLine();
                if(isFirstLine)
                    isFirstLine = false;
                else {
                    objects.add(supplier.get().create(data));
                }
            }
            close_csv();
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
        return objects;
    }
}
