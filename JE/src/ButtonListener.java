package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ButtonListener implements ActionListener {
    
    private final PM pm;
    ButtonListener(PM pm) {
      this.pm = pm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      this.pm.startGame();
    }
  }