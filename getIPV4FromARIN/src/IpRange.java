
public class IpRange {
	private IpBlock[] blockArray;
	int length;
	
	
    public IpRange(String ipA, String ipB) {
    	blockArray = new IpBlock[5];
    	int i=0;
    	length = -1;
    	if (ipA.equals("199.184.69.0") && (ipB.equals("199.184.73.255"))) {
    		blockArray[i++] = new IpBlock("199.184.69.0/24");
    		blockArray[i++] = new IpBlock("199.184.70.0/23");   		
    		blockArray[i++] = new IpBlock("199.184.72.0/23");   		
    		length = i; 		
    	}
    	if (ipA.equals("67.14.0.0") && (ipB.equals("67.14.159.255"))) {
    		blockArray[i++] = new IpBlock("67.14.0.0/17");
    		blockArray[i++] = new IpBlock("67.14.128.0/19");   		
    		length = i; 		
    	}
    	if (ipA.equals("67.144.0.0") && (ipB.equals("67.148.255.255"))) {
    		blockArray[i++] = new IpBlock("67.144.0.0/14");
    		blockArray[i++] = new IpBlock("67.148.0.0/16");   		
    		length = i; 		
    	}
    	if (ipA.equals("67.40.0.0") && (ipB.equals("67.42.255.255"))) {
    		blockArray[i++] = new IpBlock("67.40.0.0/15");
    		blockArray[i++] = new IpBlock("67.42.0.0/16");   		
    		length = i; 		
    	}
    	if (ipA.equals("192.104.175.0") && (ipB.equals("192.104.180.255"))) {
    		blockArray[i++] = new IpBlock("192.104.175.0/24");
    		blockArray[i++] = new IpBlock("192.104.176.0/22");  
    		blockArray[i++] = new IpBlock("192.104.180.0/24");  
    		length = i; 		
    	}
    	if (ipA.equals("198.186.195.0") && (ipB.equals("198.186.198.255"))) {
    		blockArray[i++] = new IpBlock("198.186.195.0/24");
    		blockArray[i++] = new IpBlock("198.186.196.0/23");   		
    		blockArray[i++] = new IpBlock("198.186.198.0/24");   		
    		length = i; 		
    	}
    	if (ipA.equals("199.168.32.0") && (ipB.equals("199.168.40.255"))) {
    		blockArray[i++] = new IpBlock("199.168.32.0/21");
    		blockArray[i++] = new IpBlock("199.168.40.0/24");   		
    		length = i; 		
    	}
    	if (ipA.equals("67.63.128.0") && (ipB.equals("67.63.175.255"))) {
    		blockArray[i++] = new IpBlock("67.63.128.0/19");
    		blockArray[i++] = new IpBlock("67.63.160.0/20");   		
    		length = i; 		
    	}
    	if (ipA.equals("66.79.0.0") && (ipB.equals("66.79.95.255"))) {
    		blockArray[i++] = new IpBlock("66.79.0.0/18");
    		blockArray[i++] = new IpBlock("66.79.64.0/19");   		
    		length = i; 		
    	}
    	if (ipA.equals("207.30.158.0") && (ipB.equals("207.30.160.255"))) {
    		blockArray[i++] = new IpBlock("207.30.158.0/23");
    		blockArray[i++] = new IpBlock("207.30.160.0/24");   		
    		length = i; 		
    	}
    	if (ipA.equals("207.30.163.0") && (ipB.equals("207.30.165.255"))) {
    		blockArray[i++] = new IpBlock("207.30.163.0/24");
    		blockArray[i++] = new IpBlock("207.30.164.0/23");   		
    		length = i; 		
    	}
    	if (ipA.equals("208.33.152.0") && (ipB.equals("208.33.154.255"))) {
    		blockArray[i++] = new IpBlock("208.33.152.0/23");
    		blockArray[i++] = new IpBlock("208.33.154.0/24");   		
    		length = i; 		
    	}
    	if (ipA.equals("209.26.18.0") && (ipB.equals("209.26.22.255"))) {
    		blockArray[i++] = new IpBlock("209.26.18.0/23");
    		blockArray[i++] = new IpBlock("209.26.20.0/23");   		
    		blockArray[i++] = new IpBlock("209.26.22.0/24"); 
    		length = i; 		
    	}
    	if (ipA.equals("209.26.234.0") && (ipB.equals("209.26.239.255"))) {
    		blockArray[i++] = new IpBlock("209.26.234.0/23");
    		blockArray[i++] = new IpBlock("209.26.236.0/22");   		
    		length = i; 		
    	}
    	if (ipA.equals("63.162.197.0") && (ipB.equals("63.162.207.255"))) {
    		blockArray[i++] = new IpBlock("63.162.197.0/24");
    		blockArray[i++] = new IpBlock("63.162.198.0/23");   		
    		blockArray[i++] = new IpBlock("63.162.200.0/21");   		
    		length = i; 		
    	}
    	if (ipA.equals("64.45.224.0") && (ipB.equals("64.45.228.255"))) {
    		blockArray[i++] = new IpBlock("64.45.224.0/22");
    		blockArray[i++] = new IpBlock("64.45.228.0/24");   		
    		length = i; 		
    	}
    	if (ipA.equals("205.244.200.0") && (ipB.equals("205.244.202.255"))) {
    		blockArray[i++] = new IpBlock("205.244.200.0/23");
    		blockArray[i++] = new IpBlock("205.244.202.0/24");   		
    		length = i; 		
    	}

    	
    }
    public String getBlock(int i) {
    	return blockArray[i].getBlock();
    }

    public String getBlockLong(int i) {
    	return blockArray[i].getBlockLong();
    }
}
