package subway.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.opencsv.CSVWriter;

public class AutoTesting {
    public static WebDriver driver;
    public static String base_url = "http://192.168.23.20:8080/subway/subway.jsp";
    public static String base_url2 = "https://map.naver.com/v5/subway/1000/124,1317,1807/140,234/-?spt=duration&c=14150673.2376922,4492616.9908143,15,0,0,0,dh";
    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static final String WEB_DRIVER_PATH = "C:\\Users\\kyuwon\\Desktop\\library\\chromedriver.exe";
    
    static String[][] station = new String[AutoTesting.TESTNUMBER][2];
    static int[][] result = new int[AutoTesting.TESTNUMBER][2];
    static final int TESTNUMBER = 50;
    static final String writeFileName = "C:\\Users\\kyuwon\\Desktop\\stationReport.csv";

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        driver = new ChromeDriver();
        
        String[][] map = getLineMap();
        
        // for문 한 번당 한 번 테스트
        for (int i = 0; i < TESTNUMBER; i++) {
        	System.out.println(i);
            int[] startIndex = getRandom();
            int[] arriveIndex = getRandom();
            
            station[i][0] = map[startIndex[0]][startIndex[1]]; // 출발역
            station[i][1] = map[arriveIndex[0]][arriveIndex[1]]; // 도착역
            
            result[i][0] = myCrawl(station[i][0], station[i][1]); // 내 홈페이지의 소요시간
            result[i][1] = naverCrawl(station[i][0], station[i][1]); // 네이버의 소요시간
        }
        
        for (int i = 0; i < result.length; i++) {
        	System.out.println(station[i][0] + " 에서 " + station[i][1] + " 까지의 소요 시간 비교 / dfs기반 경로 : " + result[i][0] + "분, 네이버의 최소시간 기반 경로 : " + result[i][1] + "분");
        }
        
        driver.close();
        writeCSV();
    }
    
    static int myCrawl(String start, String arrive) {
        try {
            driver.get(base_url);
            //System.out.println(driver.getPageSource());
            Thread.sleep(2000);
            driver.findElement(By.xpath("/html/body/form/input[1]")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("/html/body/form/input[1]")).sendKeys(start);
            Thread.sleep(2000);
            driver.findElement(By.xpath("/html/body/form/input[2]")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("/html/body/form/input[2]")).sendKeys(arrive);
            Thread.sleep(2000);
            driver.findElement(By.xpath("/html/body/form/button")).click();
            Thread.sleep(2000);
        
            String temp = driver.findElement(By.xpath("/html/body/h2[4]")).getText();
            int indexOfNext = temp.indexOf("분");
            
            if (indexOfNext == 9) {
            	return Integer.parseInt(temp.substring(8, 9));
            }
            String strTime = temp.substring(8, 10);
            
            return Integer.parseInt(strTime);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
           // driver.close();
        }
    }
    
    static int naverCrawl(String start, String arrive) {
        try {
        	driver.get(base_url2);
            //System.out.println(driver.getPageSource());
            Thread.sleep(2000);
            driver.findElement(By.xpath("/html/body/app/layout/div[3]/div[2]/shrinkable-layout/div/subway-layout/subway-home-layout/subway-control-panel/div/subway-input-control/div[1]/ul/li[1]/subway-input-control-item/div/div/div[1]/input")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("/html/body/app/layout/div[3]/div[2]/shrinkable-layout/div/subway-layout/subway-home-layout/subway-control-panel/div/subway-input-control/div[1]/ul/li[1]/subway-input-control-item/div/div/div[1]/input")).sendKeys(start);
            Thread.sleep(2000);
            driver.findElement(By.xpath("/html/body/app/layout/div[3]/div[2]/shrinkable-layout/div/subway-layout/subway-home-layout/subway-control-panel/div/subway-input-control/div[1]/ul/li[1]/subway-input-control-item/div/subway-search-list/div/ul/li/a")).click();
            Thread.sleep(2000);
            
            driver.findElement(By.xpath("/html/body/app/layout/div[3]/div[2]/shrinkable-layout/div/subway-layout/subway-home-layout/subway-control-panel/div/subway-input-control/div[1]/ul/li[2]/subway-input-control-item/div/div/div[1]/input")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("/html/body/app/layout/div[3]/div[2]/shrinkable-layout/div/subway-layout/subway-home-layout/subway-control-panel/div/subway-input-control/div[1]/ul/li[2]/subway-input-control-item/div/div/div[1]/input")).sendKeys(arrive);
            Thread.sleep(2000);
            driver.findElement(By.xpath("/html/body/app/layout/div[3]/div[2]/shrinkable-layout/div/subway-layout/subway-home-layout/subway-control-panel/div/subway-input-control/div[1]/ul/li[2]/subway-input-control-item/div/subway-search-list/div/ul/li/a")).click();
            Thread.sleep(4000);
        
            String temp = driver.findElement(By.xpath("/html/body/app/layout/div[3]/div[2]/shrinkable-layout/div/subway-layout/subway-home-layout/perfect-scrollbar/div/div[1]/subway-directions-result/div/div[1]/div/div")).getText();
            
            int indexOfNext = temp.indexOf("분");
            int time = 0;
            
            if (indexOfNext == 1) {
            	time = Integer.parseInt(temp.substring(0, 1));
        	} else if (indexOfNext == 5) {
            	time = Integer.parseInt(temp.substring(4, 5)) + 60;
            } else if (indexOfNext == 6) {
            	time = Integer.parseInt(temp.substring(4, 6)) + 60;
        	} else {
            	time = Integer.parseInt(temp.substring(0, 2));
            }
            
            return time;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            // driver.close();
        }
    }
    
    static int[] getRandom() {
        int ran1 = (int)(Math.random() * 35);
        int ran2 = (int)(Math.random() * 7);
        
        return new int[] {ran1, ran2};
    }
    
    static String[][] getLineMap() {
        // TODO Auto-generated method stub
        String[][] map = new String[1000][1000];
        for (int i = 0; i < map.length; i++) {
            Arrays.fill(map[i], "1");
        }
        
        try {
            File f = new File("C:\\Users\\kyuwon\\Desktop\\subway_0625.csv");
            BufferedReader br = new BufferedReader(new FileReader(f));

            String readtxt;
            if ((readtxt = br.readLine()) == null) {
                System.out.println("빈 파일입니다.");
                br.close();
                return null;
            }
            
            int cnt = 0;
            while ((readtxt = br.readLine()) != null) {
                String[] name = readtxt.split(",");

                for (int i = 0; i < 11; i++) {
                    map[cnt][i] = name[i];
                }
                cnt++;
            }
            br.close();
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    static void writeCSV() {
    	 try {
             CSVWriter cw = new CSVWriter(new FileWriter(writeFileName));
             
             String[] head = {"start", "arrive", "dfs", "naver"};
             cw.writeNext(head);
             
             for (int i = 0; i < station.length; i++) {
            	 String[] data = {station[i][0], station[i][1], Integer.toString(result[i][0]), Integer.toString(result[i][1])};
            	 cw.writeNext(data);
             }
             
             cw.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
    	
    	
    }

}
