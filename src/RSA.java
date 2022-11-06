import java.math.BigInteger;
import java.util.*;
import java.io.*;

public class RSA {
 int tamPrimo;
 BigInteger n, q, p;
 BigInteger fi;
 BigInteger e, d;
 /** Constructor de la clase RSA */
 public RSA(int tamPrimo) {
 this.tamPrimo = tamPrimo;
 generaPrimos(); //Genera p y q
 generaClaves(); //Genera e y d
 }

 public void generaPrimos()
 {
 p = new BigInteger(tamPrimo, 10, new Random());
 do q = new BigInteger(tamPrimo, 10, new Random());
 while(q.compareTo(p)==0);
 }

 public void generaClaves()
 {
 // n = p * q
 n = p.multiply(q);
 // toltient = (p-1)*(q-1)
 fi = p.subtract(BigInteger.valueOf(1));
 fi = fi.multiply(q.subtract(BigInteger.valueOf(1)));
 // Elegimos un e coprimo de y menor que n
 do e = new BigInteger(2 * tamPrimo, new Random());
 while((e.compareTo(fi) != -1) ||
 (e.gcd(fi).compareTo(BigInteger.valueOf(1)) != 0));
 // d = e^1 mod fi
 d = e.modInverse(fi);
 }

 /**
 * Encripta el texto usando la clave pública
 *
 * @param mensaje Ristra que contiene el mensaje a encriptar
 * @return El mensaje cifrado como un vector de BigIntegers
 */
 public BigInteger[] encripta(String mensaje)
 {
 int i;
 byte[] temp = new byte[1];
 byte[] digitos = mensaje.getBytes();
 BigInteger[] bigdigitos = new BigInteger[digitos.length];

 for(i=0; i<bigdigitos.length;i++){
 temp[0] = digitos[i];
 bigdigitos[i] = new BigInteger(temp);
 }

 BigInteger[] encriptado = new BigInteger[bigdigitos.length];

 for(i=0; i<bigdigitos.length; i++)
 encriptado[i] = bigdigitos[i].modPow(e,n);

 return(encriptado);
 }

 public String desencripta(BigInteger[] encriptado) {
 BigInteger[] desencriptado = new BigInteger[encriptado.length];

 for(int i=0; i<desencriptado.length; i++)
 desencriptado[i] = encriptado[i].modPow(d,n);

 char[] charArray = new char[desencriptado.length];

 for(int i=0; i<charArray.length; i++)
 charArray[i] = (char) (desencriptado[i].intValue());

 return(new String(charArray));
 }

 public BigInteger damep() {return(p);}
 public BigInteger dameq() {return(q);}
 public BigInteger damefi() {return(fi);}
 public BigInteger damen() {return(n);}
 public BigInteger damee() {return(e);}
 public BigInteger damed() {return(d);
}}
