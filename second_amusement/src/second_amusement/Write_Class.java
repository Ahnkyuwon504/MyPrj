package second_amusement;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Write_Class {
	public void write_Data (ArrayList<Ticket_Info_Class> ticketarray) throws IOException {
		BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ConstantValue_Class.DATA_FILE_LOCATION, true), "utf-8"));
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdt = new SimpleDateFormat("YYYYMMdd");



		Ticket_Info_Class ticketinfo = null;
		String data = "";
		
		for (int i = 0; i < ticketarray.size(); i++) {
			ticketinfo = ticketarray.get(i);
			
			data += sdt.format(cal.getTime()) + ",";
			data += ticketinfo.getDayOrNight() + ",";
			data += ticketinfo.getKindOfAge() + ",";
			data += ticketinfo.getAmount() + ",";
			data += ticketinfo.getPrice() + ",";
			data += ticketinfo.getTreat() + "\n";
			
		}
		fw.write(data);
		fw.close();

	}
	
	public void write_Sale_day(List<List<String>> all_Data) throws IOException {
		BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ConstantValue_Class.REPORT1_FILE_LOCATION, false), "utf-8"));
		String data = "";
		ArrayList<String> date = new ArrayList<String>(); // 파일에 존재하는 날짜 담는 ArrayList
		int[] sale_Of_date; // 그 ArrayList와 같은 size로 만들어지고, 가격이 담겨질 Array
		// 모든 ArrayList 읽으며 날짜저장
		for (int i = 1; i < all_Data.size(); i++) {
			if (i == 1) { // 첫번째 날짜는 무조건저장
				date.add(all_Data.get(1).get(0));
			} else { // 그 전날짜와 다르면 저장
				if (all_Data.get(i).get(0).equals(all_Data.get(i - 1).get(0))) {
				} else {
					date.add(all_Data.get(i).get(0));
				}
			}
		} // date를 담은 arrayList 완성
		sale_Of_date = new int[date.size()]; // date의 size와 같은 크기로 array 생성

		for (int i = 0; i < date.size(); i++) {
			for (int j = 0; j < all_Data.size(); j++) {
				if (date.get(i).equals(all_Data.get(j).get(0))) {
					sale_Of_date[i] += Integer.parseInt(all_Data.get(j).get(4)); // 날짜별 합계가 더해짐
				}
			}
		} // 날짜별 총액을 담은 array 완성
		data += "일자, 총 매출\n";
		for (int i = 0; i < date.size(); i++) {
			data += date.get(i) + "," + sale_Of_date[i] + "\n";
		}
		fw.write(data);
		fw.close();
	}

	public void write_Age_DayNight(List<List<String>> all_Data) throws IOException {
		BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ConstantValue_Class.REPORT2_FILE_LOCATION, false), "utf-8"));
		String data = "";
		// 주간권
		int sales_Baby_Day = 0; // 주간권 유아
		int sales_Child_Day = 0; // 주간권 소인
		int sales_Teen_Day = 0; // 주간권 청소년
		int sales_Adult_Day = 0; // 주간권 대인
		int sales_Old_Day = 0; // 주간권 경로
		int sales_Day = 0; // 주간권 총합
		// 야간권
		int sales_Baby_Night = 0; // 야간권 유아
		int sales_Child_Night = 0; // 야간권 소인
		int sales_Teen_Night = 0; // 야간권 청소년
		int sales_Adult_Night = 0; // 야간권 대인
		int sales_Old_Night = 0; // 야간권 경로
		int sales_Night = 0; // 야간권 총합
		// 총매출
		int price_Day = 0; // 주간권 총매출
		int price_Night = 0; // 야간권 총매출

		for (int i = 0; i < all_Data.size(); i++) {
			if (all_Data.get(i).get(1).equals("주간권")) { // 주간권 count
				if (all_Data.get(i).get(2).equals("경로")) {
					sales_Old_Day += Integer.parseInt(all_Data.get(i).get(3));
				} else if (all_Data.get(i).get(2).equals("대인")) {
					sales_Adult_Day += Integer.parseInt(all_Data.get(i).get(3));
				} else if (all_Data.get(i).get(2).equals("청소년")) {
					sales_Teen_Day += Integer.parseInt(all_Data.get(i).get(3));
				} else if (all_Data.get(i).get(2).equals("소인")) {
					sales_Child_Day += Integer.parseInt(all_Data.get(i).get(3));
				} else if (all_Data.get(i).get(2).equals("유아")) {
					sales_Baby_Day += Integer.parseInt(all_Data.get(i).get(3));
				}
				// 주간권 총매출 count
				price_Day += Integer.parseInt(all_Data.get(i).get(4));

			} else if (all_Data.get(i).get(1).equals("야간권")) { // 야간권 count
				if (all_Data.get(i).get(2).equals("경로")) {
					sales_Old_Night += Integer.parseInt(all_Data.get(i).get(3));
				} else if (all_Data.get(i).get(2).equals("대인")) {
					sales_Adult_Night += Integer.parseInt(all_Data.get(i).get(3));
				} else if (all_Data.get(i).get(2).equals("청소년")) {
					sales_Teen_Night += Integer.parseInt(all_Data.get(i).get(3));
				} else if (all_Data.get(i).get(2).equals("소인")) {
					sales_Child_Night += Integer.parseInt(all_Data.get(i).get(3));
				} else if (all_Data.get(i).get(2).equals("유아")) {
					sales_Baby_Night += Integer.parseInt(all_Data.get(i).get(3));
				}
				// 야간권 총매출
				price_Night += Integer.parseInt(all_Data.get(i).get(4));
			}
		}
		// 주야간 총합
		sales_Day = sales_Old_Day + sales_Adult_Day + sales_Teen_Day + sales_Child_Day + sales_Baby_Day;
		sales_Night = sales_Old_Night + sales_Adult_Night + sales_Teen_Night + sales_Child_Night + sales_Baby_Night;
		
		data += "구분,주간권,야간권\n";
		data += "유아," + sales_Baby_Day + "," + sales_Baby_Night + "\n";
		data += "어린이," + sales_Child_Day + "," + sales_Child_Night + "\n";
		data += "청소년," + sales_Teen_Day + "," + sales_Teen_Night + "\n";
		data += "어른," + sales_Adult_Day + "," + sales_Adult_Night + "\n";
		data += "노인," + sales_Old_Day + "," + sales_Old_Night + "\n";
		data += "합계," + sales_Day + "," + sales_Night + "\n";
		data += "매출," + price_Day + "," + price_Night + "\n";

		fw.write(data);
		fw.close();
	}
}