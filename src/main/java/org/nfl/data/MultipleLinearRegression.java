package org.nfl.data;

import Jama.Matrix;
import Jama.QRDecomposition;


/**
 *  The {@code MultipleLinearRegression} class performs a multiple linear regression
 *  on an set of <em>N</em> data points using the model
 *  <em>y</em> = &beta;<sub>0</sub> + &beta;<sub>1</sub> <em>x</em><sub>1</sub> + ... +
 &beta;<sub><em>p</em></sub> <em>x<sub>p</sub></em>,
 *  where <em>y</em> is the response (or dependent) variable,
 *  and <em>x</em><sub>1</sub>, <em>x</em><sub>2</sub>, ..., <em>x<sub>p</sub></em>
 *  are the <em>p</em> predictor (or independent) variables.
 *  The parameters &beta;<sub><em>i</em></sub> are chosen to minimize
 *  the sum of squared residuals of the multiple linear regression model.
 *  It also computes the coefficient of determination <em>R</em><sup>2</sup>.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class MultipleLinearRegression {
    private final Matrix beta;  // regression coefficients
    private double sse;         // sum of squared
    private double sst;         // sum of squared

    /**
     * Performs a linear regression on the data points {@code (y[i], x[i][j])}.
     * @param  x the values of the predictor variables
     * @param  y the corresponding values of the response variable
     * @throws IllegalArgumentException if the lengths of the two arrays are not equal
     */
    public MultipleLinearRegression(double[][] x, double[] y) {
        if (x.length != y.length) {
            throw new IllegalArgumentException("matrix dimensions don't agree");
        }

        // number of observations
        int n = y.length;

        Matrix matrixX = new Matrix(x);

        // create matrix from vector
        Matrix matrixY = new Matrix(y, n);

        // find least squares solution
        QRDecomposition qr = new QRDecomposition(matrixX);
        beta = qr.solve(matrixY);


        // mean of y[] values
        double sum = 0.0;
        for (int i = 0; i < n; i++)
            sum += y[i];
        double mean = sum / n;

        // total variation to be accounted for
        for (int i = 0; i < n; i++) {
            double dev = y[i] - mean;
            sst += dev*dev;
        }

        // variation not accounted for
        Matrix residuals = matrixX.times(beta).minus(matrixY);
        sse = residuals.norm2() * residuals.norm2();

    }

    /**
     * Returns the least squares estimate of &beta;<sub><em>j</em></sub>.
     *
     * @param  j the index
     * @return the estimate of &beta;<sub><em>j</em></sub>
     */
    public double beta(int j) {
        return beta.get(j, 0);
    }

    /**
     * Returns the coefficient of determination <em>R</em><sup>2</sup>.
     *
     * @return the coefficient of determination <em>R</em><sup>2</sup>,
     *         which is a real number between 0 and 1
     */
    public double R2() {
        return 1.0 - sse/sst;
    }

    /**
     * Unit tests the {@code MultipleLinearRegression} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        double[][] x = {
                { 11.000000, 7.000000, 80.100000, -3.760000, 18.900000, 3.705882 },
                { 12.600000, 11.700000, 102.900000, -2.150000, 18.500000, 2.000000 },
                { 13.100000, 3.700000, 81.800000, -3.860000, 19.800000, 4.000000 },
                { 11.700000, 4.400000, 81.800000, 0.430000, 19.100000, 3.000000 },
                { 11.000000, 4.700000, 67.400000, 0.430000, 16.800000, 4.000000 },
                { 14.700000, 11.700000, 56.200000, -6.120000, 21.100000, 5.000000 },
                { 12.200000, 3.900000, 56.300000, -7.770000, 21.200000, 5.000000 },
                { 10.700000, 0.000000, 70.000000, -4.970000, 18.700000, 5.000000 },
                { 11.400000, 0.000000, 70.000000, -1.800000, 18.800000, 5.000000 },
                { 14.400000, 7.500000, 42.400000, -3.060000, 20.400000, 5.000000 },
                { 13.400000, 0.000000, 70.000000, -9.390000, 22.200000, 5.000000 },
                { 14.700000, 0.000000, 70.000000, -6.120000, 21.100000, 5.000000 },
                { 13.400000, 10.300000, 28.000000, -9.390000, 22.200000, 5.000000 },
                { 9.940000, 19.600000, 38.600000, -3.410000, 19.400000, 5.000000 },
                { 12.400000, 8.900000, 54.900000, -4.810000, 21.400000, 5.000000 },
                { 12.500000, 9.800000, 26.000000, -7.260000, 20.600000, 5.000000 },
                { 11.000000, 5.800000, 27.800000, -3.760000, 18.900000, 5.000000 }
        };
        double[] y = { 2, 6, 3, 8, 8, 5, 8, 4, 3, 5, 7, 11, 6, 4, 3, 9, 4 };
        MultipleLinearRegression regression = new MultipleLinearRegression(x, y);

        System.out.printf("%.4fa + %.4fb + %.4fc + %.4fd + %.4fe + %.4ff (R^2 = %.2f)\n",
                regression.beta(0), regression.beta(1), regression.beta(2), regression.beta(3),
                regression.beta(4), regression.beta(5), regression.R2());

    }

}