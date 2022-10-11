/**
 * @author kmusodza
 *
 */
package com.mycompany.app;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


public class Scrap {
	
	public void getPage(String link) {
		WebClient webClient = new WebClient();
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(false);
		
		try {
			HtmlPage page = webClient.getPage(link);
			webClient.getCurrentWindow().getJobManager().removeAllJobs();
			
			// Retrieve all <li> elements
			List<HtmlElement> items =   page.getByXPath("//li[@class='result-row']");
		
			if(!items.isEmpty()) {
				for(HtmlElement htmlItem: items) {
					 // Get the details from <p class="result-info"><a href=""></a></p>
					HtmlAnchor itemAnchor = ((HtmlAnchor) htmlItem.getFirstByXPath(".//p[@class='result-info']/a"));
					
					// Get the price from <a><span class="result-price"></span></a>
				    HtmlElement spanPrice = ((HtmlElement) htmlItem.getFirstByXPath(".//a/span[@class='result-price']")) ;

				   // String itemName = itemAnchor.asNormalizedText();
				    //String itemUrl =  itemAnchor.getHrefAttribute();
				    
				 // It is possible that an item doesn't have any price
				    String itemPrice = spanPrice == null ? "0.0" : ((Object) spanPrice).toString();
				    
				    Item item = new Item();
				    
				    item.setTitle(itemAnchor.asNormalizedText());
				    item.setUrl(link + itemAnchor.getHrefAttribute());
				    item.setPrice(new BigDecimal(itemPrice.replace("$", "")));
				    
				    ObjectMapper mapper = new ObjectMapper();
				    String jsonStr = mapper.writeValueAsString(item);
				    
				    System.out.println(jsonStr);
				    
				    //System.out.println( String.format("Name : %s Url : %s Price : %s", itemName, itemPrice, itemUrl));

				}
			}
			else {
				  System.out.println("No items found !");
				}
					
		}catch(IOException e) {
			System.out.println("An error occured" + e);
		}
//		finally {
//			webClient.close();
//			
//		}
	}
	
//	public void getData() {
		
//	}
}