package second_amusement;

import java.util.Scanner;

public class Input_Class {
	Scanner sc = null;
	 public Input_Class() {
		 sc = new Scanner(System.in);
	 }
	 
	 public int dayOrNight_InputFromConsole() {
		 int N;
		 System.out.printf("권종을 선택하세요.\n1. 주간권\n2. 야간권\n");
		 N = sc.nextInt();
		 sc.nextLine();
		 return N;
	 }
	 
	 public String SocialNumber_InputFromConsole() {
		 String socialNumber;
		 System.out.printf("주민번호를 입력해봐요.\n");
		 socialNumber = sc.nextLine();
		 return socialNumber;
	 }
	 
	 public int Amount_InputFromConsole() {
		 int N;
		 System.out.printf("몇 개를 주문하시겠습니까? (최대 10개)\n");
		 N = sc.nextInt();
		 sc.nextLine();
		 if (N > 10) {
			 System.out.printf("10장을 초과하여 10장으로 처리합니다.\n");
			 return 10;
		 }

		 return N;
	 }
	 
	 public int Treat_InputFromConsole() {
		 int N;
		 System.out.printf("우대사항을 선택하세요.\n1. 없음 (나이 우대는 자동처리)"
		 		+ "\n2. 장애인\n3. 국가유공자\n4. 다자녀\n5. 임산부\n");
		 N = sc.nextInt();
		 sc.nextLine();
		 return N;
	 }
	 
	 public int ContinueOrNot_InputFromConsole() {
		 int N;
		 System.out.printf("계속 발권하시겠습니까?\n1. 티켓 발권\n2. 종료\n");
		 N = sc.nextInt();
		 sc.nextLine();
		 return N;
	 }
	 
	 public int ContinueOrExit_InputFromConsole() {
		 int N;
		 System.out.printf("계속 진행하시겠습니까?\n"
		 		+ "1 : 새로운 주문, 2 : 프로그램 종료\n");
		 N = sc.nextInt();
		 sc.nextLine();
		 return N;
	 }
}