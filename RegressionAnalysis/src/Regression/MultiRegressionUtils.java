package Regression;

import java.text.DecimalFormat;

import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

public class MultiRegressionUtils {
	
	DecimalFormat df = new DecimalFormat("0.######");
	
	public double[] RSquare(OLSMultipleLinearRegression regression) {
		double[] rsq = {regression.calculateRSquared(), regression.calculateAdjustedRSquared()}; // R square and Adjusted R square

		return rsq;
	}

	/*
	 * calculate p-value get p-value from cumulative distribution function need
	 * degree of freedom as a parameter of method - T distribution
	 */
	public double[] getPValue(OLSMultipleLinearRegression regression) {
		TDistribution td;
		double[] Beta = regression.estimateRegressionParameters(); 
		int freedom = regression.estimateResiduals().length - Beta.length; // freedom
		td = new TDistribution(freedom); 
		double[] pValue = new double[Beta.length - 1]; // number of independent variables

		for (int i = 0; i < pValue.length; i++) {
			double tstat = Beta[i + 1] / regression.estimateRegressionParametersStandardErrors()[i + 1];
			// two-tailed probability need to multiply with 2
			pValue[i] = td.cumulativeProbability(-Math.abs(tstat)) * 2;
		}
		
		return pValue;
	}

	public double[] coefficient(OLSMultipleLinearRegression regression) {
		// Array 1~3: coefficient of independent variables | 0: ~of dependent
		double[] coefficient = new double[regression.estimateRegressionParameters().length - 1]; // number of independent variables

		for (int i = 0; i < coefficient.length; i++) {
			coefficient[i] = regression.estimateRegressionParameters()[i + 1];
		}
		
		return coefficient;
	}

	// VIF = 1 / (1 - R^2)
	public double[] getVIF(OLSMultipleLinearRegression regression, double[][] x) {		
		
		int sizeOfVariables = x[0].length; 							// number of variables
		int sizeOfIndependent = x[0].length - 1;					// freedom
		double[] independentVIF = new double[x[0].length];			
		
		for (int m = 0; m < sizeOfVariables; m++) {
			int dependentIndex = m;
			double[][] independentVariables = new double[x.length][sizeOfIndependent];
			double[] dependentVariables = new double[x.length];
			
			for (int i = 0; i < x.length; i++) {
				double[] independent = new double[sizeOfIndependent];
				int cnt = 0;
				for (int j = 0; j < x[0].length; j++) {
					if (j != dependentIndex) {
						independent[cnt++] = x[i][j];
						dependentVariables[i] = x[i][dependentIndex]; 
					}
				}
				independentVariables[i] = independent;
			}
			regression.newSampleData(dependentVariables, independentVariables);
			independentVIF[m] = 1 / (1 - regression.calculateRSquared());
		}
		
		return independentVIF;

		
	}
}
