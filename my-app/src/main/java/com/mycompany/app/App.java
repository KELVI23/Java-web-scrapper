package com.mycompany.app;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Scanner;



/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws UnsupportedEncodingException
    {
    	//System.out.println("Enter seach query");
        @SuppressWarnings("resource")
		//Scanner scanner = new Scanner(System.in);
        //String searchQuery = scanner.next();
        String searchQuery = "iphone 13";
        Scrap scrapper = new Scrap();
        String searchUrl = "https://newyork.craigslist.org/search/moa?query=" + URLEncoder.encode(searchQuery,"UTF-8");
        scrapper.getPage(searchUrl);
    }
}
