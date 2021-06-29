package Regression;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Calculator {
	// get Monthly
	public double[] get_Monthly(double[] year_Array) {
		double[] month_Array = new double[12];
		double sum = 0;
		for (int i = 0; i < 31; i++) {sum += year_Array[i];} month_Array[0] = sum/31; sum = 0;
		for (int i = 31; i < 59; i++) {sum += year_Array[i];} month_Array[1] = sum/28; sum = 0;
		for (int i = 59; i < 90; i++) {sum += year_Array[i];}
		month_Array[2] = sum/31;
		sum = 0;
		for (int i = 90; i < 120; i++) {
			sum += year_Array[i];
		}
		month_Array[3] = sum/30;
		sum = 0;
		for (int i = 120; i < 151; i++) {
			sum += year_Array[i];
		}
		month_Array[4] = sum/31;
		sum = 0;
		for (int i = 151; i < 181; i++) {
			sum += year_Array[i];
		}
		month_Array[5] = sum/30;
		sum = 0;
		for (int i = 181; i < 212; i++) {
			sum += year_Array[i];
		}
		month_Array[6] = sum/31;
		sum = 0;
		for (int i = 212; i < 243; i++) {
			sum += year_Array[i];
		}
		month_Array[7] = sum/31;
		sum = 0;
		for (int i = 243; i < 273; i++) {
			sum += year_Array[i];
		}
		month_Array[8] = sum/30;
		sum = 0;
		for (int i = 273; i < 304; i++) {
			sum += year_Array[i];
		}
		month_Array[9] = sum/31;
		sum = 0;
		for (int i = 304; i < 334; i++) {
			sum += year_Array[i];
		}
		month_Array[10] = sum/30;
		sum = 0;
		for (int i = 334; i < 365; i++) {
			sum += year_Array[i];
		}
		month_Array[11] = sum/31;
		return month_Array;
	}
	
	public double[] get_9Monthly(double[] year_Array) {
		double[] month_Array = new double[9];
		double sum = 0;
		for (int i = 0; i < 31; i++) {sum += year_Array[i];}
		month_Array[0] = sum/31; sum = 0;
		for (int i = 31; i < 59; i++) {sum += year_Array[i];}
		month_Array[1] = sum/28; sum = 0;
		for (int i = 59; i < 90; i++) {sum += year_Array[i];}
		month_Array[2] = sum/31; sum = 0;
		for (int i = 90; i < 120; i++) {sum += year_Array[i];}
		month_Array[3] = sum/30; sum = 0;
		for (int i = 120; i < 151; i++) {sum += year_Array[i];}
		month_Array[4] = sum/31; sum = 0;
		for (int i = 151; i < 181; i++) {sum += year_Array[i];}
		month_Array[5] = sum/30; sum = 0;
		for (int i = 181; i < 212; i++) {sum += year_Array[i];}
		month_Array[6] = sum/31; sum = 0;
		for (int i = 212; i < 243; i++) {sum += year_Array[i];}
		month_Array[7] = sum/31; sum = 0;
		for (int i = 243; i < 273; i++) {sum += year_Array[i];}
		month_Array[8] = sum/30; sum = 0;
		return month_Array;
	}
	
	public double[] get_9Monthly_29(double[] year_Array) {
		double[] month_Array = new double[9];
		double sum = 0;
		for (int i = 0; i < 31; i++) {sum += year_Array[i];}
		month_Array[0] = sum/31; sum = 0;
		for (int i = 31; i < 60; i++) {sum += year_Array[i];}
		month_Array[1] = sum/29; sum = 0;
		for (int i = 60; i < 91; i++) {sum += year_Array[i];}
		month_Array[2] = sum/31; sum = 0;
		for (int i = 91; i < 121; i++) {sum += year_Array[i];}
		month_Array[3] = sum/30; sum = 0;
		for (int i = 121; i < 152; i++) {sum += year_Array[i];}
		month_Array[4] = sum/31; sum = 0;
		for (int i = 152; i < 182; i++) {sum += year_Array[i];}
		month_Array[5] = sum/30; sum = 0;
		for (int i = 182; i < 213; i++) {sum += year_Array[i];}
		month_Array[6] = sum/31; sum = 0;
		for (int i = 213; i < 244; i++) {sum += year_Array[i];}
		month_Array[7] = sum/31; sum = 0;
		for (int i = 244; i < 273; i++) {sum += year_Array[i];}
		month_Array[8] = sum/30; sum = 0;
		return month_Array;
	}
}
