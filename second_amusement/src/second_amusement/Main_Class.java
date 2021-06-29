package second_amusement;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main_Class {

	public static void main(String[] args) throws IOException, SQLException {
		Ticket_Info_Class ticketinfo = null; 
		ArrayList<Ticket_Info_Class> arraylist_ticketinfo = null;
		Input_Class inputclass = new Input_Class(); 
		Output_Class outputclass = new Output_Class(); 
		Write_Class writeclass = new Write_Class(); 
		Read_Class readclass = new Read_Class(); 
		Database_Class databaseclass = new Database_Class();
		int dayOrNight, amount, treat; 
		String socialnumber; 
		List<List<String>> data = null;

		while (true) {
			arraylist_ticketinfo = new ArrayList<Ticket_Info_Class>(); 
			while (true) {
				ticketinfo = new Ticket_Info_Class(); 

				dayOrNight = inputclass.dayOrNight_InputFromConsole(); 
				socialnumber = inputclass.SocialNumber_InputFromConsole(); 
				amount = inputclass.Amount_InputFromConsole(); 
				treat = inputclass.Treat_InputFromConsole(); 

				ticketinfo.setDayOrNight(dayOrNight); 
				ticketinfo.setKindOfAge(socialnumber); 
				ticketinfo.setAmount(amount); 
				ticketinfo.setTreat(treat); 
				ticketinfo.setPrice(dayOrNight, socialnumber, amount, treat);
				
				outputclass.print_OneTry_Ticket(ticketinfo.getPrice()); 
				arraylist_ticketinfo.add(ticketinfo);
				if (inputclass.ContinueOrNot_InputFromConsole() == 2) {
					break;
				}
			}
			outputclass.print_TotalTry_Ticket(arraylist_ticketinfo);
			if (inputclass.ContinueOrExit_InputFromConsole() == 2) {
				break;
			}
		}
		writeclass.write_Data(arraylist_ticketinfo);
		data = readclass.get_Array_savedData();
		
		outputclass.print_Total_Sales_Day(data);
		outputclass.print_Total_Sales_DayNight(data);
		outputclass.print_Total_Sales_OneDay(data);
		outputclass.print_Total_Treatment(data);
		
		writeclass.write_Sale_day(data);
		writeclass.write_Age_DayNight(data);
		
		databaseclass.addData(data); // 데이터베이스에 현재매출현황 저장 메소드
	}
}