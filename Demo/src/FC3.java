
import java.net.*;
import java.io.*;
import net.wimpi.modbus.*;
import net.wimpi.modbus.msg.*;
import net.wimpi.modbus.io.*;
import net.wimpi.modbus.net.*;
import net.wimpi.modbus.util.*;
 
public class FC3 {

  public static void main(String[] args) {
    try {
    	System.out.println("success,happy");
    	/* The important instances of the classes mentioned before */
    	SerialConnection con = null; //the connection
    	ModbusSerialTransaction trans = null; //the transaction
    	ReadCoilsRequest req = null; //the request
    	ReadCoilsResponse res = null; //the response

    	/* Variables for storing the parameters */
    	String portname= "COM3"; //the name of the serial port to be used
    	int unitid = 2; //the unit identifier we will be talking to
    	int ref = 0; //the reference number of the register to read from.  �Ĵ�����ַ
    	int count = 10; //the count of IR's to read
    	//int repeat = 5; //a loop for repeating the transaction
    	
    	//1. Setup the parameters         
    	
    	
    	//2. Set master identifier
    	//ModbusCoupler.createModbusCoupler(null);
    
    	ModbusCoupler.getReference().setUnitID(1);      

    	//3. Setup serial parameters
    	SerialParameters params = new SerialParameters();
    	params.setPortName(portname);
    	params.setBaudRate(9600);
    	params.setDatabits(8);
    	params.setParity("None");
    	params.setStopbits(1);
    	params.setEncoding("ascii");
    	params.setEcho(false);
    	
    	
    	//4. Open the connection
    	con = new SerialConnection(params);
    	con.open();
    	
    	//5. Prepare a request
    	//req = new ReadInputRegistersRequest(ref, count);
    	req=new ReadCoilsRequest(ref, count);
    	req.setUnitID(unitid);
    	
    	
    	
    	
    	
    	req.setHeadless();

    	//6. Prepare a transaction
    	trans = new ModbusSerialTransaction(con);
    	trans.setRequest(req);
    	//7. Execute the transaction repeat times
    	int k = 0;
    	
    	  trans.execute();

    	  res = (ReadCoilsResponse) trans.getResponse();
    	 
    	  
    	

    	//8. Close the connection
    	con.close();  
    	
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }//main
  
}//class SerialAITest