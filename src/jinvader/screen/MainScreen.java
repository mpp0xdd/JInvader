package jinvader.screen;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import jglib.component.GameScreen;
import jinvader.common.AbstractSpace;

public class MainScreen extends GameScreen implements KeyListener {

  private final AbstractSpace space;
  private Keystroke leftKey;
  private Keystroke rightKey;
  private Keystroke spaceKey;

  public MainScreen(AbstractSpace space) {
    this.space = space;
    this.leftKey = Keystroke.NOT_PRESSED;
    this.rightKey = Keystroke.NOT_PRESSED;
    this.spaceKey = Keystroke.NOT_PRESSED;
    setScreenSize(space.width(), space.height());
    setFocusable(true);
    addKeyListener(this);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    space.draw(g);
  }

  @Override
  protected void runGameLoop() {
    if (leftKey.isPressed()) space.moveLaserCannonToLeft();
    if (rightKey.isPressed()) space.moveLaserCannonToRight();
    if (spaceKey.isPressed()) space.fireLaserCannon();
    space.moveAliens();
    space.moveLasers();
    space.defeatAliens();
    repaint();
  }

  @Override
  public void keyTyped(KeyEvent e) {}

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_LEFT -> leftKey = Keystroke.PRESSED;
      case KeyEvent.VK_RIGHT -> rightKey = Keystroke.PRESSED;
      case KeyEvent.VK_SPACE -> spaceKey = Keystroke.PRESSED;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_LEFT -> leftKey = Keystroke.NOT_PRESSED;
      case KeyEvent.VK_RIGHT -> rightKey = Keystroke.NOT_PRESSED;
      case KeyEvent.VK_SPACE -> spaceKey = Keystroke.NOT_PRESSED;
    }
  }

  private enum Keystroke {
    PRESSED,
    NOT_PRESSED,
    ;

    boolean isPressed() {
      return this.equals(PRESSED);
    }
  }
}
