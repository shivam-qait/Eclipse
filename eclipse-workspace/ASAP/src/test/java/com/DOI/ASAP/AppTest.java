package com.DOI.ASAP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;




public class AppTest 
 
{
	
	@Test
	public void file() throws IOException {
		System.setProperty("webdriver.chrome.driver", "/home/qainfotech/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		File file = new File("/home/qainfotech/test.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		//BufferedWriter writer = new BufferedWriter(new FileWriter("/home/qainfotech/test2.txt"));
		 FileOutputStream fout=new FileOutputStream("/home/qainfotech/test2.txt");  
		 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fout));
		 FileOutputStream efout=new FileOutputStream("/home/qainfotech/error.txt");  
		 BufferedWriter ebw = new BufferedWriter(new OutputStreamWriter(efout));
		 String readline;
		ArrayList<String> line = new ArrayList<String>();
		while ((readline = br.readLine()) != null)   {
			 
			line.add(readline);
			
		}
		
		System.out.println(line.size());
		for(int i = 0;i<line.size();i++)
		{
			
			driver.get("https://achs-stag.literatumonline.com/doi/"+line.get(i));
			if(driver.getTitle().equals("Error (ACS Publications)")) {
				
				ebw.write(line.get(i));
				ebw.newLine();
				ebw.flush();
				continue;
				
			}
				
			String a = driver.findElement(By.cssSelector(".content-navigation__btn--return span")).getText();
			if(a.equals("RETURN TO ARTICLES ASAP")) {
				//byte b[]=line.get(i).getBytes();
				bw.write(line.get(i));
				bw.newLine();
				bw.flush();
			}
			
				
		}
		driver.close();
		
	}
	
	
	
	
}
