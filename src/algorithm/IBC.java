package algorithm;

import java.util.Random;

public class IBC {
	
	
	public static int[] setup()
	{
		int[] params=new int[3];
		Random r = new Random();
		int s = r.nextInt(10) + 1;
		int mk=s;//master key
		params[0]=3;//for g, 3 taken as prime number
		params[1]=(int) Math.pow(params[0],s);//for u
		return params;		
	}
	
	public static int extract(String ID) {

		//String ID = Integer.toString(id);
		Random r = new Random();
		int s = r.nextInt(10) + 1;
		int Private_key = (int) Math.pow(ID.hashCode(), s);
		return Private_key;
	}
	
	public static String encrypt(int[] params, String id, String message)
	{
		int hid=id.hashCode();
		Random rand = new Random();
		int r = rand.nextInt(10) + 1;
		int c1=(int) Math.pow(params[0],r);
		
	
		return null;
		
	}
	public static String decrypt()
	{
		return null;
		
	}
	public static int[] sign(String msg)
	{
		String tid="10";
		int n=7;
		int[] sign=new int[2];
		Random r = new Random();
		int s = r.nextInt(2) + 1;
		int g1=tid.hashCode();
		int k=(int) Math.pow(g1, s);
		int gm=msg.hashCode();
		sign[0]=(int) (k*Math.pow(gm, s));
		System.out.println("s0"+sign[0]);
		
		int g=5;
		sign[1]=(int) Math.pow(g, s);
		System.out.println(sign[1]);
		tid="9";
		g1=tid.hashCode();
		int e0=(int) Math.pow(g, sign[0])% n; System.out.println("e0"+e0);
		int u=(int) Math.pow(g, s);
		int e1=(int) Math.pow(u, g1)% n; System.out.println("e1"+e1);
		int e2=(int) Math.pow(sign[0],gm )% n;System.out.println("e2"+e2);
		System.out.println("e1+e2"+(e1+e2));
		if(e0==(e1*e2))
		{
			System.out.println("verified");
		}
		else
		{
			System.out.println("not");
		}
		return sign;		
		
	}
	public static String verify()
	{
		return null;
		
	}
	public static void main(String args[])
	{
		String message="usage 25";
		String id="dipali@gmail.com";
		
		int[] params=setup();
		sign(message);
//		String msg=encrypt(params,id,message);  
//		
//		System.out.println("Original Message");
//		System.out.println("Encrypted message :"+ msg);
//		
//		msg=decrypt();
//		System.out.println("Decrypted message :"+msg);
//		
		
	}

}
