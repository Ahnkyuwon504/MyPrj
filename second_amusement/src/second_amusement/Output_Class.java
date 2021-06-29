package second_amusement;

import java.util.ArrayList;
import java.util.List;

public class Output_Class {
	public void print_OneTry_Ticket(int Price) {
		System.out.printf("가격은 %d원 입니다.\n감사합니다.\n", Price);
	}
	public void print_TotalTry_Ticket(ArrayList<Ticket_Info_Class> ticketarraylist) {
		Ticket_Info_Class ticketinfo = null;
		int sum = 0;
		System.out.printf("----------------폴리랜드----------------\n");
		for (int i = 0; i < ticketarraylist.size(); i++) {
			ticketinfo = ticketarraylist.get(i);
			System.out.printf("%s %-5s X %-2d %-8d %-10s\n\n", ticketinfo.getDayOrNight(), ticketinfo.getKindOfAge(),
					ticketinfo.getAmount(), ticketinfo.getPrice(), ticketinfo.getTreat());
			sum += ticketinfo.getPrice();
		}
		System.out.printf("입장료 총액은 %d원입니다.\n", sum);
		System.out.printf("---------------------------------------\n");

	}

	public void print_Total_Sales_Day(List<List<String>> all_Data) {
		System.out.printf("\n====================report.csv=======================\n");
		System.out.printf("날짜      권종    연령   수량  가격      우대사항\n");
		for (int i = 0; i < all_Data.size(); i++) {
			System.out.printf("%-10s%-5s%-5s%-6s%-10s%-10s\n", all_Data.get(i).get(0), all_Data.get(i).get(1),
					all_Data.get(i).get(2), all_Data.get(i).get(3), all_Data.get(i).get(4), all_Data.get(i).get(5));
		}
		System.out.printf("=====================================================\n");
	}

	public void print_Total_Sales_DayNight(List<List<String>> all_Data) {
		int sales_Baby_Day = 0; // 주간권 유아
		int sales_Child_Day = 0; // 주간권 소인
		int sales_Teen_Day = 0; // 주간권 청소년
		int sales_Adult_Day = 0; // 주간권 대인
		int sales_Old_Day = 0; // 주간권 경로
		int sales_Day = 0; // 주간권 총합
		int sales_Baby_Night = 0; // 야간권 유아
		int sales_Child_Night = 0; // 야간권 소인
		int sales_Teen_Night = 0; // 야간권 청소년
		int sales_Adult_Night = 0; // 야간권 대인
		int sales_Old_Night = 0; // 야간권 경로
		int sales_Night = 0; // 야간권 총합
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
				price_Night += Integer.parseInt(all_Data.get(i).get(4));
			}
		}
		sales_Day = sales_Old_Day + sales_Adult_Day + sales_Teen_Day + sales_Child_Day + sales_Baby_Day;
		sales_Night = sales_Old_Night + sales_Adult_Night + sales_Teen_Night + sales_Child_Night + sales_Baby_Night;

		System.out.printf("\n===================권종 별 판매현황===================\n");
		System.out.printf("주간권 총 %d매\n", sales_Day);
		System.out.printf("유아 %d매, 소인 %d매, 청소년 %d매, 대인 %d매, 경로 %d매\n", sales_Baby_Day, sales_Child_Day, sales_Teen_Day,
				sales_Adult_Day, sales_Old_Day);
		System.out.printf("주간권 총 매출 : %d원\n", price_Day);
		System.out.printf("야간권 총 %d매\n", sales_Night);
		System.out.printf("유아 %d매, 소인 %d매, 청소년 %d매, 대인 %d매, 경로 %d매\n", sales_Baby_Night, sales_Child_Night,
				sales_Teen_Night, sales_Adult_Night, sales_Old_Night);
		System.out.printf("야간권 총 매출 : %d원\n", price_Night);
		System.out.printf("=====================================================\n");
	}

	public void print_Total_Sales_OneDay(List<List<String>> all_Data) {
		ArrayList<String> date = new ArrayList<String>(); // 파일에 존재하는 날짜 담는 ArrayList
		int[] sale_Of_date; // 그 ArrayList와 같은 size로 만들어지고, 가격이 담겨질 Array
		for (int i = 0; i < all_Data.size(); i++) {
			if (i == 0) { // 첫번째 날짜는 무조건저장
				date.add(all_Data.get(0).get(0));
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

		// date별 출력
		System.out.printf("\n==================일자별 매출 현황====================\n");
		for (int i = 0; i < date.size(); i++) {
			System.out.printf("%s년 %s월 %s일 : 총 매출   %d원\n", date.get(i).substring(0, 4), date.get(i).substring(4, 6),
					date.get(i).substring(6, 8), sale_Of_date[i]);
		}
		System.out.printf("=====================================================\n");
	}

	public void print_Total_Treatment(List<List<String>> all_Data) {
		int howMany_Nothing = 0;
		int howMany_Dis = 0;
		int howMany_Nat = 0;
		int howMany_Mul = 0;
		int howMany_Pre = 0;
		int howMany_Total = 0;

		for (int i = 0; i < all_Data.size(); i++) {
			if (all_Data.get(i).get(5).equals("우대사항 없음.")) {
				howMany_Nothing += Integer.parseInt(all_Data.get(i).get(3));
			} else if (all_Data.get(i).get(5).equals("장애인")) {
				howMany_Dis += Integer.parseInt(all_Data.get(i).get(3));
			} else if (all_Data.get(i).get(5).equals("국가 유공자")) {
				howMany_Nat += Integer.parseInt(all_Data.get(i).get(3));
			} else if (all_Data.get(i).get(5).equals("다자녀")) {
				howMany_Mul += Integer.parseInt(all_Data.get(i).get(3));
			} else if (all_Data.get(i).get(5).equals("임산부")) {
				howMany_Pre += Integer.parseInt(all_Data.get(i).get(3));
			}
		}

		howMany_Total = howMany_Nothing + howMany_Dis + howMany_Nat + howMany_Mul + howMany_Pre;

		System.out.printf("\n==================우대권 판매 현황====================\n");
		System.out.printf("총 판매 티켓수      : %2d매\n", howMany_Total);
		System.out.printf("우대없음            : %2d매\n", howMany_Nothing);
		System.out.printf("장애인              : %2d매\n", howMany_Dis);
		System.out.printf("국가유공자          : %2d매\n", howMany_Nat);
		System.out.printf("다자녀              : %2d매\n", howMany_Mul);
		System.out.printf("임산부              : %2d매\n", howMany_Pre);
		System.out.printf("=====================================================\n");
	}

}