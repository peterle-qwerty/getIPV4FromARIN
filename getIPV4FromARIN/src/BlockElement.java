import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BlockElement {
	  public String oid = "";
	  public String product = "";
	  public IpBlock block;
	  public String date = "";
	  
	  public BlockElement(String inOid, String inProduct, IpBlock b, String sHandle) {
		  oid = inOid;
		  product = inProduct;
		  block = b;
//		  date = getDate(sHandle);
		  
	  }

//	  public BlockElement(String inOid, String inProduct) {
//		  oid = inOid;
//		  product = inProduct;
//		  block = b;
//	  }
	  
	  private String getDatex(String sHandle) {
//        URL oracle = new URL("http://whois.arin.net/rest/net/NET-130-13-0-0-1.html");
	    try {
          URL link = new URL("http://whois.arin.net/rest/net/"+sHandle+".html");
        
          BufferedReader in = new BufferedReader(
	      new InputStreamReader(link.openStream()));

	      String inputLine;
	      while ((inputLine = in.readLine()) != null)
//    		Pattern p = Pattern.compile("(\\d*\\.){3}\\d*/\\d*"); // match a.b.c.d/cidr
//			Matcher m = p.matcher(inputLine);
	
//			if (m.find()) {
				
//			}

	        System.out.println(inputLine);

	      in.close();
		} 
        catch (IOException e){
		  e.printStackTrace();
		}		
       
	    return "aa";
	  }

}
