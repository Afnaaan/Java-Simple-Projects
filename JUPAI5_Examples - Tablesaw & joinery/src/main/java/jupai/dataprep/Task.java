package jupai.dataprep;

import joinery.DataFrame;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.NumberColumn;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Task {
    static String dataPath = "src/main/resources/data/titanic.csv";
    static String testDataPath = "src/main/resources/data/titanic_test.csv";
    static Table titanicDataTableSaw;
    static Table testTitanicDataTableSaw;
    static DataFrame<Object> titanicDataJoinery;

    public static void main(String[] args) {
        try {
            System.out.println("---------------TableSaw-----------------");
            titanicDataTableSaw = Table.read().csv(dataPath);
            System.out.println(titanicDataTableSaw.summary().toString());

            System.out.println("---------------Joinery-----------------");
            titanicDataJoinery = DataFrame.readCsv(dataPath);
            System.out.println(titanicDataJoinery.describe());

            System.out.println("---------------add column TableSaw-----------------");
            titanicDataTableSaw = mapTextColumnToNumber(titanicDataTableSaw);

        }catch (IOException e) {
            e.printStackTrace ();
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // mapping text data to numeric values on the sex field female=1,male=0 and adding a column named gender  with TableSaw
    public static Table mapTextColumnToNumber(Table data){
        NumberColumn mappedGenderColumn=null;
        StringColumn gender= (StringColumn) data.column ("Sex");
        List<Number> mappedGenderValues=new ArrayList<Number>();
        for(String v:gender)
        {
            if ((v != null) && (v.equals ("female")))
            {
                mappedGenderValues.add (new Double (1));
            }
            else
            {
                mappedGenderValues.add (new Double (0));
            }
        }
        mappedGenderColumn= DoubleColumn.create("gender",mappedGenderValues);
        data.addColumns (mappedGenderColumn);
        return data;
    }

}
