import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigInteger;
/**
*
* @author Casidiablo
*/
public class VentanaRSA extends JFrame implements ActionListener{

 private JTextField cajaTamPrimo;
 private JButton btnGenenerarClaves;
 private ButtonGroup grupoBotonesOpcion;
 private JRadioButton btnEncriptar, btnDesEncriptar;
 private JTextArea areaOrigen, areaDestino;
 private Container cntAreas, cntGenClaves;
 private RSA rsa;
 private BigInteger[] textoCifrado;
 /** Constructor de la clase VentanaRSA */
 public VentanaRSA() {
 super("Interfaz Grafica - RSA");

 getContentPane().setLayout(new BorderLayout(3,3));

 cajaTamPrimo = new JTextField();
 cajaTamPrimo.addActionListener(
 new ActionListener() {
 public void actionPerformed(ActionEvent evt) {
 generarClaves();
 }
 });

 btnGenenerarClaves = new JButton("Generar Claves");
 btnGenenerarClaves.addActionListener(this);

 btnEncriptar = new JRadioButton("Encriptar", false);
 btnEncriptar.addActionListener( new ManejadorBotonOpcion() );
 btnEncriptar.setEnabled(false);
 btnDesEncriptar = new JRadioButton("Desencriptar", false);
 btnDesEncriptar.addActionListener( new ManejadorBotonOpcion() );
 btnDesEncriptar.setEnabled(false);

 grupoBotonesOpcion = new ButtonGroup();
 grupoBotonesOpcion.add(btnEncriptar);
 grupoBotonesOpcion.add(btnDesEncriptar);

 areaOrigen = new JTextArea();
 areaOrigen.setWrapStyleWord(true);
 areaOrigen.setLineWrap(true);
 areaDestino = new JTextArea();
 areaDestino.setLineWrap(true);
 areaDestino.setEditable(false);

 cntAreas = new Container();
 cntGenClaves = new Container();

 cntGenClaves.setLayout(new GridLayout(2,3));
 cntGenClaves.add(new JLabel("Digita el tamaño del número primo: "));
 cntGenClaves.add(cajaTamPrimo);
 cntGenClaves.add(btnGenenerarClaves);
 cntGenClaves.add(new JLabel("Digita el texto a cifar/descifrar:"));
 cntGenClaves.add(btnEncriptar);
 cntGenClaves.add(btnDesEncriptar);

 cntAreas.setLayout(new GridLayout(1,2,5,5));
 cntAreas.add(new JScrollPane(areaOrigen));
 cntAreas.add(new JScrollPane(areaDestino));

 getContentPane().add(cntGenClaves, BorderLayout.NORTH);
 getContentPane().add(cntAreas, BorderLayout.CENTER);

 setSize(650,300);
 setVisible(true);
 }

 // manejar eventos de botón
 public void actionPerformed( ActionEvent evento ) {
 if(evento.getSource().equals(btnGenenerarClaves))
 generarClaves();
 }

 //Generar claves
 private void generarClaves() {
 if(cajaTamPrimo.getText().equals(""))
 JOptionPane.showMessageDialog(null,"No haz introducido el tamaño del primo",
"Tenemos problemas", JOptionPane.ERROR_MESSAGE);
 else {
 rsa = new RSA(Integer.parseInt(cajaTamPrimo.getText()));
 rsa.generaPrimos();
 rsa.generaClaves();
 JTextArea area = new JTextArea(20,50);
 area.setEditable(false);
 area.setLineWrap(true);
 area.append("Tam clave: "+cajaTamPrimo.getText()+"\n\n");
 area.append("p:["+rsa.damep()+"]\n\nq:["+rsa.dameq()+"]\n\n");
 area.append("Clave publica(n,e):\n\nn:["+rsa.damen()+"]\n\ne:["+rsa.damee()+"]\n\n");
 area.append("Clave publica(n,d):\n\nn:["+rsa.damen()+"]\n\nd:["+rsa.damed()+"]");
 JOptionPane.showMessageDialog(null, new JScrollPane(area),"Primos generados",
JOptionPane.INFORMATION_MESSAGE);
 btnEncriptar.setEnabled(true);
 btnDesEncriptar.setEnabled(true);
 }
 }

 // clase interna privada para manejar eventos de botón de opción
 private class ManejadorBotonOpcion implements ActionListener {

 // manejar eventos de botón de opción
 public void actionPerformed( ActionEvent evento ) {
 if(evento.getSource().equals(btnEncriptar)) {
 if(areaOrigen.getText().equals(""))
 JOptionPane.showMessageDialog(null,"No haz introducido datos paracifrar", "Tenemos problemas", JOptionPane.ERROR_MESSAGE);
 else {
 textoCifrado = rsa.encripta(areaOrigen.getText());
 areaDestino.setText("");
 for(int i=0; i<textoCifrado.length; i++)
 areaDestino.append(textoCifrado[i].toString());
 }
 } else if(evento.getSource().equals(btnDesEncriptar)) {
 if(areaOrigen.getText().equals(""))
 JOptionPane.showMessageDialog(null,"No haz introducido datos para decifrar", "Tenemos problemas", JOptionPane.ERROR_MESSAGE);
 else {
 areaDestino.setText("");
 String recuperarTextoPlano = rsa.desencripta(textoCifrado);
 areaDestino.setText(recuperarTextoPlano);
 }
 }
 }

 } // fin de la clase interna privada ManejadorBotonOpcion*/

 public static void main(String args[]) {

 VentanaRSA ventana = new VentanaRSA();
 ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 }
}