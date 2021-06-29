package second_amusement;

import java.util.ArrayList;

public class Ticket_Info_Class {
	private String dayOrNight; // 자료구조, 주야간 데이터
	private String kindOfAge;  // 자료구조, 연령 데이터
	private int amount; 	   // 자료구조, 수량 데이터
	private String treat;	   // 자료구조, 우대사항 데이터
	private int price;	   	   // 자료구조, 가격 데이터

	public String getDayOrNight() {
		return dayOrNight;
	}

	public void setDayOrNight(int N) {
		if (N == 1) {
			this.dayOrNight = ConstantValue_Class.DAYNIGHT[0];
		} else if (N == 2) {
			this.dayOrNight = ConstantValue_Class.DAYNIGHT[1];
		}
	}
	public String getKindOfAge() {
		return kindOfAge;
	}

	public void setKindOfAge(String SocialNumber) {
		int real_Age = get_Real_Age(SocialNumber);

		for (int i = 0; i < ConstantValue_Class.AGE.length; i++) {
			if (real_Age >= ConstantValue_Class.AGE[i]) {
				this.kindOfAge = ConstantValue_Class.String_AGE[i];
				break;
			}
		}
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getTreat() {
		return treat;
	}

	public void setTreat(int N) {
		for (int i = 1; i <= ConstantValue_Class.TREAT.length; i++) {
			if (N == i) {
				this.treat = ConstantValue_Class.TREAT[i - 1];
				break;
			}
		}
	}
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int dayNight, String SocialNumber, int amount, int treat) {
		int setPrice = 0;
		int real_Age = get_Real_Age(SocialNumber);

		for (int i = 1; i <= ConstantValue_Class.CHARGE.length; i++) { // 주간권,야간권만큼 for문 실시
			if (dayNight == i) {
				for (int j = 0; j < ConstantValue_Class.CHARGE[i - 1].length; j++) { // 주야간 결정되면 나이별로 setPrice 부여
					if (real_Age >= ConstantValue_Class.AGE[j]) {
						setPrice = ConstantValue_Class.CHARGE[i - 1][j];
						break;
					}
				}
			} 
		}
		setPrice *= amount; // 최초요금에 수량 곱

		for (int i = 1; i <= ConstantValue_Class.TREAT.length; i++) { // 우대사항 갯수만큼 for문 실시, 우대사항 결정시 할인율 곱
			if (treat == i) {
				setPrice *= ConstantValue_Class.DISCOUNT_TREAT[i - 1];
				break;
			}
		}

		this.price = setPrice;
	}

	public static int get_Real_Age(String SocialNumber) {
		int year = Integer.parseInt(SocialNumber.substring(0, 2));
		int real_Age;

		if (year < 22) {
			real_Age = 21 - year;
		} else {
			real_Age = 121 - year;
		}
		return real_Age;
	}
}