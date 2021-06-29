package second_amusement;

public class ConstantValue_Class {
	public final static String[] DAYNIGHT = {"주간권", "야간권"};
	public final static int[][] CHARGE = 						  
		{{44000, 56000, 47000, 44000, 0}, 
				{37000, 46000, 40000, 37000, 0}};
	
	public final static int[] AGE = {65, 19, 13, 3, 0};			
	public final static String[] String_AGE = 					
		{"경로", "대인", "청소년", "소인", "유아"};
	
	public final static String[] TREAT =						  
		{"우대사항 없음.", "장애인", "국가 유공자", "다자녀", "임산부"};
	public final static double[] DISCOUNT_TREAT = 				
		{1.0, 0.6, 0.5, 0.8, 0.85};
	
	public final static String DATA_FILE_LOCATION = "data_amuse.txt";
	public final static String REPORT1_FILE_LOCATION = "report1_amuse.txt";
	public final static String REPORT2_FILE_LOCATION = "report2_amuse.txt";
}