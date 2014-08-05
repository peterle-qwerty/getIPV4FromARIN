import java.lang.Math;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@SuppressWarnings("unused")
public class IpBlock {
  int cidr;
  long network;
  long broadcast;
  
  // ipA, ipB are the ranges (network & broadcast addresses)
  public IpBlock(String ipA, String ipB) {
    long ipAVal = ipToVal(ipA);
    long ipBVal = ipToVal(ipB);
    
    long comp = ipAVal ^ ipBVal;
    
    String hostBinary = Long.toBinaryString(comp);
    int x = hostBinary.indexOf('0',1);
//    System.out.println("Found 0 at possition " + x+ " [" + hostBinary+"]");
//    if (x > -1) {
//    	System.out.println("Incorrect conversion");
//    }

    if (x <= -1) {    
      cidr = 32 - hostBinary.length();
      network = ipAVal;

      double sizeD = Math.pow(2,32-cidr);
      long size = new Double(sizeD).longValue();
    
      broadcast = network + size -1;
    } else {
      System.out.println("Incorrect conversion");
    }
  }

  // 192.168/16
  public IpBlock(String block) {
	String[] element = block.split("/");

	int cidr = Integer.valueOf(element[1]);
	doit(element[0], cidr);
  }
  
  // ip and cidr.  This will normalize the Ip into network ip
  public IpBlock(String x, int inCidr) {
    doit(x,inCidr);
  }
  
  public IpBlock (IpBlock b) {
	  this.cidr = b.cidr;
	  this.network = b.network;
	  this.broadcast = b.broadcast;
	  System.out.println("Hello");
  }
  
  private void doit(String x, int inCidr) {
	    cidr = inCidr;
	    long val  = ipToVal(x);
	    double maskD = Math.pow(2,32-cidr)-1; 
	    long mask = ~(new Double(maskD)).longValue();

	    network = (val & mask);

	    double sizeD = Math.pow(2,32-cidr);
	    long size = new Double(sizeD).longValue();
	    
	    broadcast = network + size -1;
  }
  
  private static long ipToVal(String addr) {

	  String[] addrArray = addr.split("\\.");
	  long num = 0;
	  for (int i=0;i<addrArray.length;i++) {
		  int power = 3-i;
		  double tmpD = ((Integer.parseInt(addrArray[i])%256 * Math.pow(256,power)));
		  num += ((Integer.parseInt(addrArray[i])%256 * Math.pow(256,power)));
	  }
	  return num;
  }

  public Boolean isChild(String ip) {
	long ipVal = ipToVal(ip);  

	if ((ipVal >= network) && (ipVal <= broadcast))  {
      return true;
	} else {
	  return false;
	}
  }

  public String getBroadcastIp() {
	String ip = valToIp(broadcast);
    return ip;
  }
  
  public String getNetworkIp() {
		String ip = valToIp(network);
	    return ip;
	  }
  
  public String getBlockLong() {
	    String ret = "";
	    String ret2 = "";
	    ret = valToIp(network) + "/" + cidr;

	    return ret;
  }
  public String getBlock() {
    String ret = "";
    String ret2 = "";
    ret = valToIp(network) + "/" + cidr;
    String ip = valToIp(network);
    
    String[] temp = ip.split("\\.");
//    System.out.println(ip+ temp[0] + " " + temp[1] + " " + temp[2] + " " + temp[3]);
//  System.out.println(ip + ": " + temp.length);
//  for (int i=0; i< temp.length; i++) {
//	  System.out.println(temp[i]);
//  }
    
    if (cidr <= 8) {
    	ret2 = temp[0] + "/" + cidr;
    } else {
      if (cidr <= 16) {
      	ret2 = temp[0] + "."+temp[1]+"/" + cidr;
      } else {
    	  if (cidr <= 24) {
    		  ret2 = temp[0] + "." + temp[1] + "." + temp[2] + "/" + cidr;
    	  } else {
    		  ret2 = temp[0] + "." + temp[1] + "." + temp[2] + "."+temp[3]+"/" + cidr;    		  
    	  }
      }
    }
    
//    return ret+" "+ret2;
//    System.out.println("PEAR: " + ret + " " + ret2);
    return ret2;
  }

  public void doPrint() {
    System.out.println("Block: " + valToIp(network) + "/" + cidr);
    System.out.println("Network: " + valToIp(network));
    System.out.println("Broadcast: " + valToIp(broadcast));
  }
  private static String valToIp(long i) {
 
	return ((i >> 24 ) & 0xFF) + "." + ((i >> 16 ) & 0xFF) + "." +
	((i >> 8 ) & 0xFF) + "." +( i & 0xFF);
  }
}