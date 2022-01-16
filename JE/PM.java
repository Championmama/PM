import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 12.01.2022
 * @author Vincent
 */

public class PM {
  public static Window GameWindow = new Window(800, 450, 20);
  public PM() {
    GameWindow.WindowVisible();
  } // end of public PM
  
  // Anfang Methoden
  
  public static void main(String[] args) {
    new PM();
  } // end of main

  // Ende Methoden
} // end of class PM
