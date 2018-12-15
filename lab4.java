import java.security.SecureRandom;
import java.math.BigInteger;
import java.util.Random;

public class lab4 {

  public static void main (String[] args) {
     BigInteger p = safePrime();

     BigInteger q = p.add(new BigInteger("-1",10)).divide(new BigInteger("2", 10));

     BigInteger g = generator(p, q);

    System.out.println(p.toString());
    System.out.println(q.toString());
    System.out.println(g.toString());

  }

  public static BigInteger safePrime() {
    SecureRandom rand = new SecureRandom();
    BigInteger x = BigInteger.probablePrime(29, rand);

    // y = ( x - 1 ) / 2
    BigInteger y = x.add(new BigInteger("-1",10)).divide(new BigInteger("2", 10));

    if (y.isProbablePrime(20)) {
      return x;
    } else {
      return safePrime();
    }
  }

  public static BigInteger generator(BigInteger p, BigInteger q) {
      SecureRandom rand = new SecureRandom();

      BigInteger g2 = new BigInteger("1",10);
      BigInteger gq = new BigInteger("1",10);

      while (g2.equals(new BigInteger("1",10)) || gq.equals(new BigInteger("1",10))) {
         BigInteger g = new BigInteger(29, rand).mod(p);

         g2 = g.modPow(new BigInteger("2",10),p);
         gq = g.modPow(q,p);

         if (!g2.equals(new BigInteger("1",10)) && !gq.equals(new BigInteger("1",10))) {
           return g;
         }
      }

      //this should not happen
      return p;
  }
}
