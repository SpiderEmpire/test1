package controller;
import java.util.Random;

import uk.ac.ic.doc.jpair.api.Field;
import uk.ac.ic.doc.jpair.api.FieldElement;
import uk.ac.ic.doc.jpair.api.Pairing;
import uk.ac.ic.doc.jpair.pairing.BigInt;
import uk.ac.ic.doc.jpair.pairing.EllipticCurve;
import uk.ac.ic.doc.jpair.pairing.Point;
import uk.ac.ic.doc.jpair.pairing.Predefined;

public class PairTest {

public static void main(String args[])
{
//using a predefined pairing
Pairing e = Predefined.nssTate();

//get P, which is a random point in group G1
Point P = e.RandomPointInG1(new Random());

//get Q, which is a random point in group G2
Point Q = e.RandomPointInG2(new Random());

//compute e(P,Q)
FieldElement epq =e.compute(P,Q);

//the curve on which G1 is defined
EllipticCurve g1 = e.getCurve();
//a is a 160-bit random integer
BigInt a = new BigInt(160,new Random());
//Point aP is computed over G1
Point aP = g1.multiply(P, a); 

//The curve on which G2 is defined
EllipticCurve g2 = e.getCurve2();
//b is a 160-bit random integer
BigInt b = new BigInt(160,new Random());
//bQ is computed over G2
Point bQ = g2.multiply(Q, b); 

//compute e(aP,bQ)
FieldElement res = e.compute(aP,bQ);

//compute e(P,Q)^ab, this is done in group Gt
BigInt ab = a.multiply(b);
//get the field on which Gt is defined
Field gt = e.getGt();
FieldElement res2 = gt.pow(epq,ab);

//compare these two
if(res.equals(res2)){
    System.out.println("Correct! e(aP,bQ) = e(P,Q)^ab");
}
else{
    System.out.println("Something is wrong! e(aP,BQ) != e(P,Q)^ab");    
}
}
}
