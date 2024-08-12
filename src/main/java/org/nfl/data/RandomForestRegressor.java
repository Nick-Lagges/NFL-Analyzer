package org.nfl.data;

import com.opencsv.CSVReader;
import smile.data.DataFrame;
import smile.data.Tuple;
import smile.data.formula.Formula;
import smile.data.type.DataType;
import smile.data.type.StructField;
import smile.data.type.StructType;
import smile.data.vector.DoubleVector;
import smile.regression.RandomForest;

import org.slf4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RandomForestRegressor {
    private static RandomForest model;
    private static StructType structType;

    public RandomForestRegressor(int version) throws IOException, ClassNotFoundException {
        FileReader fileReader = new FileReader(Utils.RANDOM_FOREST_DATA);
        CSVReader csvReader = new CSVReader(fileReader);
        String[] nextRecord;
        // we are going to read data line by line
        ArrayList<String[]> lines = new ArrayList<>();
        while ( (nextRecord = csvReader.readNext()) != null ) {
            if ( ! nextRecord[0].contains("PG") ) lines.add(nextRecord);
        }
        String[][] array = new String[lines.size()][0];
        String[][] vars = lines.toArray(array);
        double[][] variables = new double[vars.length][vars[0].length];
        for ( int i = 0; i < vars.length; i++ ){
            for (int j = 0; j < vars[0].length; j++) {
                variables[i][j] = Double.valueOf(vars[i][j]);
            }
        }
        double[] target = {
                0, 8, 2, 2, 2, 5, 8, 3, 2, 4, 5, 1, 2, 6, 3, 8, 8, 5, 8, 4, 3, 5, 7, 11, 6, 4, 3, 9, 4, 7, 4, 9, 9, 6, 7, 10, 8, 7, 1, 5, 8, 9, 5, 6, 4, 6, 8, 18, 3, 7, 4, 8, 8, 11, 10, 14, 5, 6, 8, 3, 6, 4, 4, 5, 5, 3, 5, 2, 5, 6, 3, 6, 7, 11, 5, 9, 3, 8, 6, 11, 8, 8, 10, 9, 5, 4, 9, 6, 7, 4, 11, 4, 4, 4, 7, 12, 11, 11, 6, 4, 12, 6, 7, 6, 13, 13, 8, 8, 9, 1, 5, 9, 2, 8, 8, 8, 10, 11, 8, 4, 5, 5, 10, 7, 8, 6, 8, 10, 6, 9, 6, 3, 4, 6, 4, 4, 5, 4, 7, 3, 5, 8, 7, 1, 2, 4, 4, 3, 4, 2, 4, 4, 6, 6, 13, 8, 4, 2, 7, 1, 4, 6, 7, 5, 7, 8, 1, 13, 5, 2, 7, 11, 7, 11, 11, 8, 5, 6, 8, 1, 3, 5, 4, 6, 6, 2, 2, 8, 3, 4, 6, 4, 8, 3, 8, 5, 3, 5, 6, 3, 8, 1, 2, 1, 11, 4, 8, 6, 3, 6, 4, 6, 3, 4, 1, 5, 5, 3, 8, 6, 6, 5, 2, 4, 6, 3, 0, 5, 10, 6, 3, 6, 6, 6, 5, 3, 4, 6, 3, 4, 6, 5, 6, 7, 1, 4, 7, 3, 3, 7, 4, 4, 7, 1, 5, 4, 7, 3, 6, 7, 9, 5, 5, 4, 3, 3, 7, 1, 4, 2, 6, 5, 6, 4, 2, 3, 7, 4, 11, 7, 4, 3, 4, 8, 1, 4, 4, 3, 4, 3, 5, 7, 2, 2, 7, 7, 9, 11, 7, 6, 3, 2, 7, 6, 5, 12, 6, 6, 9, 5, 12, 13, 6, 8, 8, 9, 2, 3, 7, 12, 6, 8, 7, 2, 4, 2, 1, 3, 6, 8, 8, 6, 4, 2, 5, 6, 8, 4, 6, 6, 5, 5, 4, 5, 4, 0, 6, 3, 4, 6, 3, 6, 6, 3, 4, 5, 1, 7, 5, 3, 6, 2, 5, 4, 5, 1, 5, 6, 6, 0, 3, 0, 4, 3, 7, 4, 7, 4, 4, 5
        };
        // Create a DataFrame object from the arrays
        DataFrame data = DataFrame.of(variables).merge(DoubleVector.of("target", target));

        // Define the formula with 'target' as the response variable
        Formula formula = Formula.lhs("target");

        // Create and train the Random Forest model
        model = RandomForest.fit(formula, data);
        createStructure1();
    }
    private void createStructure1() throws ClassNotFoundException {
        DataType dType = DataType.of("double");
        DataType iType = DataType.of("int");
        StructField a = new StructField("V1", dType);
        StructField b = new StructField("V2", dType);
        StructField c = new StructField("V3", dType);
        StructField d = new StructField("V4", dType);
        StructField e = new StructField("V5", dType);
        StructField f = new StructField("V6", dType);
        StructField g = new StructField("V7", dType);
        StructField h = new StructField("V8", dType);
        StructField i = new StructField("V9", dType);
        StructField j = new StructField("V10", dType);
        StructField k = new StructField("V11", dType);
        StructField l = new StructField("V12", dType);
        StructField m = new StructField("V13", dType);
        StructField n = new StructField("V14", dType);
        StructField o = new StructField("V15", dType);
        StructField p = new StructField("V16", dType);
        StructField q = new StructField("V17", dType);
        StructField r = new StructField("V18", dType);
        StructField s = new StructField("V19", dType);
        StructField t = new StructField("V20", dType);
        StructField u = new StructField("V21", dType);
        StructField v = new StructField("V22", dType);
        StructField w = new StructField("V23", iType);
        StructField x = new StructField("V24", dType);
        StructField y = new StructField("V25", dType);
        List<StructField> structFields = new ArrayList<>();
        structFields.add(a);
        structFields.add(b);
        structFields.add(c);
        structFields.add(d);
        structFields.add(e);
        structFields.add(f);
        structFields.add(g);
        structFields.add(h);
        structFields.add(i);
        structFields.add(j);
        structFields.add(k);
        structFields.add(l);
        structFields.add(m);
        structFields.add(n);
        structFields.add(o);
        structFields.add(p);
        structFields.add(q);
        structFields.add(r);
        structFields.add(s);
        structFields.add(t);
        structFields.add(u);
        structFields.add(v);
        structFields.add(w);
        structFields.add(x);
        structFields.add(y);
        structType = new StructType(structFields);
    }

    public double predictDataPoint(double[] dataPoint) {
        Tuple newDataPoint = Tuple.of(dataPoint, structType); // Example new data point
        return model.predict(newDataPoint);
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        // Assuming you have a DataFrame 'data' with your variables and a target column named 'target'
        // The DataFrame can be created from a CSV file, database, or manually as shown below


        // Now you can use the model to predict new data


        Tuple newDataPoint = Tuple.of(new double[]{12.40, 63.20, 19.20, -4.81, 26.40, 5.50, 1.18, 0.53, 21.40, 24.40, 34.90, 1.71, 0.65, 12.80, 5.53, 1.24, 41.60, 348.90, 70.00, 0.00, 0.00, 5.50, 18, 5.29, 8.29}, structType); // Example new data point
        double prediction = model.predict(newDataPoint);
        System.out.println("Predicted value: " + prediction);

        System.out.println(model.metrics());
    }
}
