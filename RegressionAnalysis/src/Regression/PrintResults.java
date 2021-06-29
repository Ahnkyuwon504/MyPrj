package Regression;

import java.text.DecimalFormat;

public class PrintResults {
	DecimalFormat df = new DecimalFormat("0.######");

	public static String nameNew(String name, int length) {
		String temp = name + "                                ";
		String nameNew = new String(temp.getBytes(), 0, length);

		return nameNew;
	}

	public void printRSquare(double[] rsq) {
		System.out.println("========================================================");
		System.out.printf("%38s\n", "[ Regression Analysis ]");
		System.out.println("========================================================");
		System.out.println("R square: " + df.format(rsq[0]));
		System.out.println("Adjusted R square: " + df.format(rsq[1]));
		System.out.println("========================================================");
	}

	public void printIndependentsResult(double[] pValue, double[] coefficient, double[] vif, String[] xName) {
		System.out.printf("%22s%18s%11s", "P-value", "Coefficient", "VIF");
		System.out.println("\n--------------------------------------------------------");
		for (int i = 0; i < xName.length; i++) {
			System.out.printf("%s%s%s%s\n", nameNew(xName[i], 15), nameNew(df.format(pValue[i]), 15),
					nameNew(df.format(coefficient[i]), 15), nameNew(df.format(vif[i]), 15));
		}
		
		
		/*
		 * System.out.println("========================================================"
		 * ); System.out.println("종속변수 : 서울시 실제 감기진료건수");
		 * System.out.println("독립변수 : 서울시 월별 평균기온, 코로나 여부(더미변수)");
		 * System.out.println("샘플 셋팅연도 : 2019년 1월~9월 / 2020년 동기간");
		 * System.out.println("========================================================"
		 * );
		 */ 
		 

	}
}
