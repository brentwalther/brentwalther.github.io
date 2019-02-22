/**
 * @(#)RSS.java
 *
 *
 * @author 
 * @version 1.00 2010/9/29
 */
import java.net.Socket;
import java.io.*;
import java.util.Scanner;

public class RSS {

    public RSS() {
    	try {
    		//System.out.println(getFeed("www.engadget.com/rss.xml").replace("|","\n"));
    		//System.out.println(getFeed("www.wfaa.com/community/blogs/job-of-the-day/index.rss2").replace("|","\n").replace(",",": "));
    		System.out.println(getFeed("feeds.foxnews.com/foxnews/latest").replace("|","\n").replace(",",": "));
    	}
    	catch (Exception e) {
    		System.out.println("Could not get feed"+e);
    	}
    }
    public String getFeed(String url) throws IOException {
    	String host = url.substring(0,url.indexOf("/"));
    	String file = url.substring(url.indexOf("/"));
    	
    	Socket sock = new Socket(host, 80);
    	BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
		
		out.println("GET " + file + " HTTP/1.1");
		out.println("Host: " + host + "\n");
		String buffer = "";
		while(true) {
			String line = in.readLine();
			buffer += line;
			if(line.contains("</rss>"))
				break;
		}
		String formatted = "";
		Scanner parser = new Scanner(buffer);
		while(parser.findInLine("<item>") != null) {
			String tag;
			while((tag = parser.findInLine("<[^<>]+>")) != null) {
				if(tag.equalsIgnoreCase("</item>"))
					break;
				if(tag.contains("</") || tag.contains("/>"))
					continue;
				tag = tag.substring(1,tag.length()-1);
				String content = parser.findInLine("[^<>]+");
				parser.findInLine("</[^<>]+>");
				formatted += tag + "," + content + "|";
			}
			
		}
		return formatted;
    }
    public static void main(String args[]) {
    	RSS feeder = new RSS();
    }
    
    
}