package second_amusement;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Read_Class {
	public static List<List<String>> get_Array_savedData() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(ConstantValue_Class.DATA_FILE_LOCATION), "utf-8"));
		List<List<String>> AllLines_Data = new ArrayList<List<String>>();
		
		String line = ""; // 데이터 한 줄씩 저장되는 String
		while ((line = reader.readLine()) != null) { // 데이터 없을 때까지 반복
			List<String> OneLines_Data = new ArrayList<String>(); // 데이터를 ,로 찢어서 저장되는 List<>
			OneLines_Data = Arrays.asList(line.split(","));
			AllLines_Data.add(OneLines_Data);
		}
		reader.close();
		return AllLines_Data;
	}

}