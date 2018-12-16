/**
  baby-step giant step

  cyclic group p
  order n: n = p -1
  generator a
  group element b

  find integer x: a^x = b

  x = im + j
  m = ceiing(sqrt(n))

  0 <= i < m
  0 <= j < m

  a^j mod p = b (a^ -m) ^i
**/

import java.util.Scanner;
import java.math.*;
import java.security.SecureRandom;
import java.util.*;

public class lab4part2 {

  static final BigInteger ZERO = new BigInteger("0");
  static final BigInteger ONE = new BigInteger("1");

  public static void main (String[] args) {
    Scanner sc = new Scanner(System.in);
    String str;
    BigInteger a;
    BigInteger b;
    BigInteger p;
    BigInteger x;

    System.out.print("Enter prime:");
    str = sc.nextLine();
    p = new BigInteger(str, 10);

    System.out.print("Enter generator:");
    str = sc.nextLine();
    a = new BigInteger(str, 10);

    System.out.print("Enter B or A:");
    str = sc.nextLine();
    b = new BigInteger(str, 10);


    x = babyStepGiantStep(a,b,p);
    System.out.println(x.toString(10));
  }

  /**
    inputs
      a (generator)
      b
      p

    output
      x such that a ^ x = b mod p

  **/
  public static BigInteger babyStepGiantStep(BigInteger a, BigInteger b, BigInteger p) throws NoSuchElementException {
    //m = ceiling (sqrt(p))
    BigInteger m = p.sqrt().add(ONE);

    Hashtable<BigInteger, BigInteger> hash = new Hashtable<BigInteger, BigInteger>();
    //Hashtable hash = new Hashtable();

    //a^j mod p
    BigInteger aj =BigInteger.valueOf(1);

    //add values of a^j to hashtable from j = 0 to j = m
    for (BigInteger j = BigInteger.valueOf(0); j.compareTo(m) < 0; j = j.add(ONE)) {
      hash.put(aj,j);
      aj = aj.multiply(a).mod(p);
    }

    //a^-m mod p
    BigInteger am = a.modPow(m,p).modInverse(p);

    //set y to b
    BigInteger y = new BigInteger(b.toByteArray());

    BigInteger target;

    ///search hashtable for some a^j s.t. a^j = b
    for (BigInteger i = BigInteger.valueOf(0); i.compareTo(m) < 0; i = i.add(ONE)) {
      target = (BigInteger) hash.get(y);
      //return im + j
      if (target != null) {
        return i.multiply(m).add(target).mod(p);
      }
      //else y = y * a^-m
      y = y.multiply(am).mod(p);
    }
    throw new NoSuchElementException("No solution");
  }
}
