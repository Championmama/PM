# Pacman

## Verzeichnis  

* [conventions](#Convention)

* src
  * [Animator.java](#animator)
  * [ButtonListener.java](#buttonlistener)
  * [Ghost.java](#Ghost)
  * [Labyrinth.java](#Labyrinth)
  * [Main.java](#Main)
  * [Player.java](#player)
  * [PM.java](#pm-pacman)
  * [PMKeyListener.java](#pmkeylistenerinput)
  * [Setting.java](#setting)
  * [Ticker.java](#ticker)
  
* [Util](#Utilities)

---

## Todo

* eigene Musik
* GUI, HUB
  * Einstellungen
  * besserer Score

---

## Conventions

* Präfix
  * m_ ist privat. (private member)
  * Ohne ist public.
  * p_ ist protected.

---

## Animator

Klasse ist dazu da, das Labyrinth zu malen und die `draw()`-Methode der Klassen `Ghost` und `Player` aufzurufen

### `zeichne()`

loopt durch alle Felder im _20 * 30_ Feld und sucht nach dem Wert nach der [Tabelle](#labyrinth) und malt das jeweilige Objekt.

sowie es  

```java
for (Ghost _G : m_ghosts) {
    _G.draw(m_g);
}
m_player.draw(m_g);
```

aufruft, um allen Geistern und dem Spieler zu zeichnen.

Zum Schluss wird der __Score__ angezeigt, der unter _PM_ als __PM.Point_Text__ gespeichert wird:

```java
m_g.setColor(Color.black);
m_g.drawString(PM.Point_Text, 650, 50); 
```

---

## ButtonListener

---

## Ghost

---

## Labyrinth

Aufgabe der Datei ist es auf das Labyrinth und die Felder aufzupassen.

 |Zahl | Objekt|
 |---|---|  
 |0|Leer|  
 |1|Wand|  
 |2|Kirsche|  
 |3|normaler-Punkt|  
 |4|Unbesiegbarkeits-Punkt|  
 |5|Tür für Geister|  
---

## Main

Alleinige Aufgabe der Datei ist es das Programm zu starten,  
dass kann mit Java Editor oder VSCode funktionieren.

---

## Player

---

## PM (PacMan)

---

## PMKeyListener(Input)

---

## Setting

---

## Ticker

---

## Utilities
