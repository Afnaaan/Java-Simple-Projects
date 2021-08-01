package jupai.dataprep;

import joinery.DataFrame;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.NumberColumn;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.selection.Selection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Task {
    static String dataPath = "src/main/resources/data/titanic.csv";
    static Table titanicDataTableSaw;
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

            System.out.println("---------------TableSaw join-----------------");

            StringColumn  col = titanicDataTableSaw.column("name").unique().asStringColumn();
            Selection names = col.isIn(col.asList());

            Table table1 = titanicDataTableSaw.copy().retainColumns("name", "survived")
                    .dropRowsWithMissingValues().where(names);
            Table table2 = titanicDataTableSaw.copy().retainColumns("name", "sex", "pclass")
                    .dropRowsWithMissingValues().where(names);

            System.out.println(table1.first(7));
            System.out.println(table2.first(7));

            Table table3 = table1.joinOn("name").inner(table2);

            System.out.println(table3.first(7));

            System.out.println("---------------Joinery join-----------------");

            DataFrame<Object> df1 = titanicDataJoinery.retain("name", "survived").groupBy("survived").unique("name");
            DataFrame<Object> df2 = titanicDataJoinery.retain("name", "sex", "pclass").groupBy("sex", "pclass").unique("name");
            //join
            DataFrame<Object> joinedDf = df1.joinOn(df2, DataFrame.JoinType.INNER, "name");
            //System.out.println(joinedDf.describe());
            System.out.println(joinedDf.head(10));


            int [] result = new int[3];
            int index;
            List<Object> Pclass = titanicDataJoinery.col(0);
            for (Object c : Pclass){
                index = Integer.parseInt(c.toString());
                result[index-1]++;
            }

            System.out.println(result[0]);

        }
        catch (IOException e) {
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
