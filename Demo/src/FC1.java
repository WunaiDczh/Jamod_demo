
import java.net.*;
import java.io.*;
import net.wimpi.modbus.*;
import net.wimpi.modbus.msg.*;
import net.wimpi.modbus.io.*;
import net.wimpi.modbus.net.*;
import net.wimpi.modbus.procimg.Register;
import net.wimpi.modbus.util.*;
 
public class FC1 {

  public static void main(String[] args) {
    try {
    	
    	/* The important instances of the classes mentioned before */
    	SerialConnection con = null; //the connection
    	ModbusSerialTransaction trans = null; //the transaction
    	ReadMultipleRegistersRequest req = null; //the request
    	ReadMultipleRegistersResponse res = null; //the response
    	
    	/* Variables for storing the parameters */
    	String portname= "COM3"; //the name of the serial port to be used   端口名
    	
    	int unitid = 2; //the unit identifier we will be talking to    Slave的设备号
    	int ref = 0; //the reference number of the register to read from.  寄存器地址
    	int count = 10; //the count of IR's to read   
    	//int repeat = 5; //a loop for repeating the transaction
    	
    	//1. Setup the parameters         
    	
    	
    	//2. Set master identifier
    	//ModbusCoupler.createModbusCoupler(null);
        
    	ModbusCoupler.getReference().setUnitID(1);        //master的ID

    	//3. Setup serial parameters   设置串口参数 ，在Jamod里面使用SerialParameters
    	SerialParameters params = new SerialParameters();
    	params.setPortName(portname);
    	params.setBaudRate(9600);
    	params.setDatabits(8);
    	params.setParity("None");
    	params.setStopbits(1);
    	params.setEncoding("ascii");
    	params.setEcho(false);
    	
    	
    	//4. Open the connection    打开串口 
    	//implements a serial connection which can be used for master and slave implementations.
    	//SerialConnection 实现了一个串口的链接，能够在master和slave的实例上使用
        
    	con = new SerialConnection(params);   //SerialParameters的实例 用来 初始化SerialConnection
    	con.open();
    	
    	
    	//5. Prepare a request   准备一个请求
    	//req = new ReadInputRegistersRequest(ref, count);
    	req=new ReadMultipleRegistersRequest(ref, count);
    	req.setUnitID(unitid);
    	
    	
    	
    	
    	
    	req.setHeadless(); //没有头，我的理解是只有TCP时候才需要头的

    	//6. Prepare a transaction
    	// Constructs a new ModbusSerialTransaction instance with a given ModbusRequest to be send 
    	// when the transaction is executed.
    	trans = new ModbusSerialTransaction(con);
    	trans.setRequest(req);
    	//7. Execute the transaction repeat times
    	
    		
    	  trans.execute();
    	 // res = (ReadInputRegistersResponse) trans.getResponse();//一直等回应了
    	  res = (ReadMultipleRegistersResponse) trans.getResponse();//一直等回应了
    	Register[] get=res.getRegisters();
    	for(int m=0;m<get.length;m++) {
    		System.out.println(get[m].getValue());
    	}
    	 
    	  
    	//8. Close the connection
    	con.close();  
    	
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }//main
  
}//class SerialAITest
