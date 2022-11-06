import java.io.*;
import java.math.BigInteger;

public class Main {

 public static void main(String[] args) throws IOException {
 if(args.length != 1) {
 System.out.println("Sintaxis: java RSA [tamaño de los primos]");
 System.out.println("por ejemplo: java RSA 512");
 args = new String[1];
 args[0]="1024";
 }
 int tamPrimo = Integer.parseInt(args[0]);
 RSA rsa = new RSA(tamPrimo);
 System.out.println("Tam Clave: ["+ tamPrimo + "]\n");
 System.out.println("p: [" + rsa.damep().toString(16).toUpperCase() + "]");
 System.out.println("q: [" + rsa.dameq().toString(16).toUpperCase() + "]\n");
 System.out.println("Clave publica (n,e)");
 System.out.println("n: [" + rsa.damen().toString(16).toUpperCase() + "]");
 System.out.println("e: [" + rsa.damee().toString(16).toUpperCase() + "]\n");
 System.out.println("Clave publica (n,d)");
 System.out.println("n: [" + rsa.damen().toString(16).toUpperCase() + "]");
 System.out.println("d: [" + rsa.damed().toString(16).toUpperCase() + "]\n");
 System.out.println("Texto a encriptar: ");
 String textoPlano = ( new BufferedReader(new
 InputStreamReader(System.in))).readLine();

 BigInteger[] textoCifrado = rsa.encripta(textoPlano);

 System.out.println("\nTexto encriptado: [");
 for(int i=0; i<textoCifrado.length; i++) {
 System.out.print(textoCifrado[i].toString(16).toUpperCase());
 if(i != textoCifrado.length-1)
 System.out.println("");
 }
 System.out.println("]\n");

 String recuperarTextoPlano = rsa.desencripta(textoCifrado);
 System.out.println("Texto desencritado: ["+ recuperarTextoPlano +"]");
 }

}