package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ButtonListener implements ActionListener {
    
    private final PM m_pm;
    ButtonListener(PM pm) {
      this.m_pm = pm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      this.m_pm.startGame();
    }
  }