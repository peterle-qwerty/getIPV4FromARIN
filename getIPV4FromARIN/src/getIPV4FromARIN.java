import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.Writer;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class getIPV4FromARIN {
	static ArrayList<BlockElement> alMasterBlock = new ArrayList<BlockElement>();
	
	public static void main(String[] args) {

		
//		  processWebFile("TEST1","IQ");
//		  processWebFile("TEST2","IQ");
		
		/* Qwest Initial Migration 
	  processWebFile("QBS-12","IQ");
	  processXlsFile("QCC-18","IQ");
	  processWebFile("QCC-21","IQ");
	  processWebFile("QCC-22","IQ");
	  processWebFile("QWEST-25","IT");
*/
	  processWebFile("CENTEL","UNKNOWN");
	  processWebFile("LIGHT-21","UNKNOWN");
	  processWebFile("CIH-12","UNKNOWN");
	  processWebFile("CSL-63","UNKNOWN");
	  processWebFile("EMBAR","UNKNOWN");
	  processWebFile("EMBAR-7","UNKNOWN");
	  processWebFile("LTSF","UNKNOWN");
	  processWebFile("SPER","UNKNOWN");
	  processWebFile("LTDS","UNKNOWN");
	  processWebFile("MEBT","UNKNOWN");
	  processWebFile("SDSL","UNKNOWN");
	  processWebFile("SMAT","UNKNOWN");
	  processWebFile("SNBOC","UNKNOWN");
		
		
	  finalOutput();
	  
	  }
	  private static void finalOutput () {
		  
        try {
		  Writer output = new BufferedWriter(new FileWriter("output/ipv4Import.txt"));		
		  output.write("OID,Product,Block,Date\n");
		  
		  Iterator<BlockElement> itr = alMasterBlock.iterator();
			while (itr.hasNext()) {
				BlockElement bex = (BlockElement) itr.next();
				IpBlock b = bex.block;
				String oid = bex.oid;
				String prod = bex.product;
				String date = bex.date;
				
//				if (isChild(b)) {
				IpBlock parentBlock;
				parentBlock = getParent(b);
				if (parentBlock != null) {
					System.out.println("# This is a reassignment block from " + parentBlock.getBlock());
					output.write("# This is a reassignment block from " + parentBlock.getBlock()+"\n");
					System.out.println("#" + oid+","+prod+","+b.getBlock());
					output.write("#" + oid+","+prod+","+b.getBlock()+","+date+"\n");
				} else {
				  System.out.println(oid+","+prod+","+b.getBlock());
				  output.write(oid+","+prod+","+b.getBlock()+","+date+"\n");
			    }
			}
  	        output.close();

        }  catch (IOException e){
			  e.printStackTrace();
			}		
	  }

		private static IpBlock getParent(IpBlock ipB) {
			  Iterator<BlockElement> i = alMasterBlock.iterator();
			  while (i.hasNext()) {
				 BlockElement be = (BlockElement) i.next();
				 long network = be.block.network;
				 String networkIp = be.block.getNetworkIp();
				 long broadcast = be.block.broadcast;
				 String broadcastIp = be.block.getBroadcastIp();
//				 System.out.println(ipB.network + "["+ ipB.getNetworkIp() + "] " + networkIp + " " + ipB.getBroadcastIp() + " " + broadcastIp);
				 if ((ipB.network >= network) && (ipB.broadcast <= broadcast)) {
					 if ((ipB.network == network) && (ipB.broadcast == broadcast)) {			 
					 } else {
					   return be.block;
					 }
				 }
			  }
			  return null;
			}

	public static void processXlsFile (String oid, String product ){
		String inFn = "input/"+oid.toLowerCase()+".txt";
		String outFn = "output/"+oid.toLowerCase()+".txt";
		
		try {
  		  Writer output = new BufferedWriter(new FileWriter(outFn));		
			
	  	  FileReader input = new FileReader(inFn);
		  BufferedReader bufRead = new BufferedReader(input);	
		  String line = "";    // String that holds current file line
		  int count = 0;  // Line number of count 		

		  line = bufRead.readLine();
		  count++;
			  
		  output.write("OID,Product,Block\n");

		  String sHandle = "aa";
		  while (line != null){
		    System.out.println(count+": "+line);
		    
		    
		    if (line.indexOf("AS")<0) {
		      if (line.indexOf("Direct") >= 0) {
		        if (line.indexOf(":") < 0) {

  			      String blockStr = getBlockFromLine(line);
			  
			      IpBlock ip = new IpBlock(blockStr);
		          String block = ip.getBlock();
		          System.out.println(block);
		          output.write(oid+","+product+","+block + "\n");
    			  alMasterBlock.add(new BlockElement(oid,product,ip,sHandle));
		        } else {
		        	output.write("#IPV6\n");
		        }
		      } else {
			      output.write("#Not Direct\n");
		      }
		    } else {
		      output.write("#AS Number\n");
		    }
            count++;
            line = bufRead.readLine();
		  }
		  bufRead.close();
	      output.close();
		}
		catch (IOException e){
		  e.printStackTrace();
		}		
	
	return;
	}

	// Must pick up any block in any string... block is _a.b.c.d/cidr_ where _ are spaces
	private static String getBlockFromLine(String line) {
		String block = "";

//		Pattern p = Pattern.compile("\\d*\\.\\d*\\.\\d*\\.\\d*/\\d*"); // match a.b.c.d/cidr
		Pattern p = Pattern.compile("(\\d*\\.){3}\\d*/\\d*"); // match a.b.c.d/cidr
		Matcher m = p.matcher(line);

	
		if (m.find()) {
//			System.out.println("Group Count: " + m.groupCount());
//		    System.out.println("Match: " + m.group(0));
		    block = m.group(0);
		}
		
//		System.out.println("HERE: Exiting...");
		
		return block;
	}
	
	public static void processWebFile(String oid, String product) {
		String inFn = "input/"+oid.toLowerCase()+".txt";
		String outFn = "output/"+oid.toLowerCase()+".txt";
		
		try {
  		  Writer output = new BufferedWriter(new FileWriter(outFn));		
			
	  	  FileReader input = new FileReader(inFn);
		  BufferedReader bufRead = new BufferedReader(input);	
		  String line = "";    // String that holds current file line
		  int count = 0;  // Line number of count 		

		  line = bufRead.readLine();
		  count++;
			  
		  output.write("OID,Product,Block,Date\n");

		  while (line != null){
		    System.out.println(count+": "+line);
		    String [] arg = line.split("\\s+");
	    
		    String sHandle = arg[1];
//		    System.out.println(sHandle);
		    sHandle = sHandle.replace("(","").replace(")","");
//		    System.out.println(sHandle);
		    
		    String ipA = arg[2];
		    String ipB = arg[4];
		    	    
		    if (ipA.indexOf(':')< 0) { 
		      IpBlock ip = new IpBlock(ipA,ipB);
		      String block = ip.getBlock();
		      String broadcast = ip.getBroadcastIp();
		      String comp = "";
		      if (broadcast.equals(ipB)) {
		    	  comp = "same broadcast[" + broadcast + " " + ipB + "]";
		      } else {
		    	  comp = "diff broadcast[" + broadcast + " " + ipB + "]";
		      }
		      if (block.equals("0/0")) {  // Range input with multiple blocks
		          IpRange r = new IpRange(ipA,ipB);
		    	  if (r.length >= 0) {
		    		output.write("# The next "+ r.length + " blocks belong to "+ ipA + " - " + ipB + "\n");
		    	    for (int i=0; i<r.length; i++) {
		    		  System.out.println(r.getBlock(i));
                      output.write(oid+","+product+","+r.getBlockLong(i) + "\n");
//                    output.write(oid+","+product+","+" " + r.getBlock(i) + " " + r.getBlockLong(i) + "\n");
        			  alMasterBlock.add(new BlockElement(oid,product,new IpBlock(r.getBlock(i)),sHandle));
		    	    }
		    	  } else {
		    	    System.out.println("Undefined block");	
		    	    throw new IOException("Block undefined");		    	    
		    	  }
		      } else {
                System.out.println(block);
//		        output.write(oid+","+product+","+block + " " + ip.getBlockLong() + " " + "\n");
		        output.write(oid+","+product+","+ip.getBlockLong() + "\n");
				alMasterBlock.add(new BlockElement(oid,product,ip,sHandle));

		      }
		    } else {
		      output.write("#IPV6\n");
		    }
            count++;
            line = bufRead.readLine();
		  }
		  bufRead.close();
	      output.close();
		}
		catch (IOException e){
		  e.printStackTrace();
		}		
	}
}
