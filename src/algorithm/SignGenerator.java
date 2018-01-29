package algorithm;

import java.util.Random;
import uk.ac.ic.doc.jpair.api.Pairing;
import uk.ac.ic.doc.jpair.pairing.Point;
import uk.ac.ic.doc.jpair.pairing.Predefined;

public class SignGenerator {
//int a=extract(1);
	public int sg1;
	public int sg2;
	
	private static int k;

	public static int[] setup()
	{
		int[] params=new int[3];
		Random r = new Random();
		int s = r.nextInt(10) + 1;
		int mk=s;//master key
		params[0]=3;//for g0 //3 taken as prime number
		params[1]=(int) Math.pow(params[0],s);//for u
		System.out.println("u="+params[1]);
		return params;		
	}
	public static int extract(String ID) {

		//String ID = Integer.toString(id);
		Random r = new Random();
		int s = r.nextInt(10) + 1;
		int Private_key = (int) Math.pow(ID.hashCode(), s);
		return Private_key;
	}

	public static int[] sign(String ID, String message) {
		
		int Private_key=k=extract(ID);
		
		int g1=ID.hashCode();
		int gm=message.hashCode();
		Random rand = new Random();
		int r = rand.nextInt(10) + 1;
		System.out.println("r"+r);
		int s1=(int) (k*(Math.pow(gm, r)));
		int g0=6;
		int s2=(int) Math.pow(g0, r);
		int[] sign=new int[2];
		sign[0]=s1;
		sign[1]=s2;

		return sign;

	}

	public static Boolean verify(String id, int[] sign, String message) {
		Boolean flag=false;
		int g0,s1,u,g1,s2,gm;
	//	int[] params=setup();
//		
//		g0=params[0];
//		s1=sign[0];
//		
//		u=params[1];	
		g1=id.hashCode();
		System.out.println("g1="+g1);
		System.out.println("gm="+message.hashCode());
//		
//		s2=sign[1];
//		gm=message.hashCode();
//			

		return flag;

	}

	public static void main(String args[]) {
		String message="123";
		String id="123";
		
		int[] params=setup();
		
		int[] sign=sign(id,message);  
		
		System.out.println("Sign :");
		System.out.println(":s1:"+sign[0]);
		System.out.println(":s2:"+sign[1]);
		
		Boolean flag=verify(id,sign,message);
		System.out.println("sign status : "+flag);

	}
}
