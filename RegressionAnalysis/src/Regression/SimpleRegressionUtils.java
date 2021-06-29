package Regression;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;
import org.apache.commons.math3.stat.regression.SimpleRegression;

/**
 * A {@code PrintStream} adds functionality to another output stream,
 * namely the ability to print representations of various data values
 * conveniently.  Two other features are provided as well.  Unlike other output
 * streams, a {@code PrintStream} never throws an
 * {@code IOException}; instead, exceptional situations merely set an
 * internal flag that can be tested via the {@code checkError} method.
 * Optionally, a {@code PrintStream} can be created so as to flush
 * automatically; this means that the {@code flush} method is
 * automatically invoked after a byte array is written, one of the
 * {@code println} methods is invoked, or a newline character or byte
 * ({@code '\n'}) is written.
 *
 * <p> All characters printed by a {@code PrintStream} are converted into
 * bytes using the given encoding or charset, or the platform's default
 * character encoding if not specified.
 * The {@link PrintWriter} class should be used in situations that require
 * writing characters rather than bytes.
 *
 * <p> This class always replaces malformed and unmappable character sequences with
 * the charset's default replacement string.
 * The {@linkplain java.nio.charset.CharsetEncoder} class should be used when more
 * control over the encoding process is required.
 *
 * @author     Frank Yellin
 * @author     Mark Reinhold
 * @since      1.0
 */


public class SimpleRegressionUtils {
	
    /**
     * Exam simple regression and print the result
     * results: Coefficient, Standard Error, R square, P-value
     *      
     * @return N/A
     * @param x : independent variables, y : dependent variables
     */
	
	
    public void simpleRegression(double[] independent, double[] dependent) {
		 SimpleRegression simple = new SimpleRegression(); 
		 // Scanner sc = new Scanner(System.in);

		  
		 for (int i = 0; i < independent.length; i++) { 
			 simple.addData(independent[i], dependent[i]); 
		 } 
		   
		 DecimalFormat df = new DecimalFormat("0.#####"); 
		   
		 System.out.println("Intercept " + df.format(simple.getIntercept())); 
		 System.out.println("Satisfaction " + df.format(simple.getSlope())); 
		 System.out.println("Standard Error " + df.format(simple.getSlopeStdErr())); 
		 System.out.println("RSquare " + df.format(simple.getRSquare())); 
		 System.out.println("P-value" + df.format(simple.getSignificance())); 
	 
	}
	
}

