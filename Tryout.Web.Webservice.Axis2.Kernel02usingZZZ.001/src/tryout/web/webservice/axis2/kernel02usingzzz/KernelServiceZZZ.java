package tryout.web.webservice.axis2.kernel02usingzzz;

import java.util.Calendar;
import java.util.Date;

import basic.zBasic.ExceptionZZZ;
import basic.zKernel.KernelZZZ;

public class KernelServiceZZZ {
	public String sayHello(String name){
		return "Hello " + name;
	}
	public String getNow(){
		Calendar cal = Calendar.getInstance();
		//Date date = new Date();
		Date date = cal.getTime();
		String sReturn = new Integer(date.getYear()).toString() + new Integer(date.getMonth()).toString() + new Integer(date.getDay()).toString();
		return sReturn;
	}
	
	/** Das ist ein Test auf Werte zuzugreifen, die KernelZZZ liefert. 
	 * TODO GOON 20171123: Das Dto muss noch angeboten werden. Zuerst mal mit einfachem String. */
	public String getKernelDto(){
		String sReturn = null;
		main:{
			try {
				KernelZZZ objKernel = new KernelZZZ();
				sReturn = objKernel.getKernelKey();
			} catch (ExceptionZZZ e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//end main:
		return sReturn;			
	}
}
