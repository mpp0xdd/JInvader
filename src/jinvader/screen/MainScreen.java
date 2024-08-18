package jinvader.screen;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;
import jglib.component.GameScreen;
import jinvader.common.Keystroke;
import jinvader.common.Space;

public class MainScreen extends GameScreen implements KeyListener {

  private final Space space;
  private Keystroke leftKey;
  private Keystroke rightKey;
  private Keystroke spaceKey;

  public MainScreen(Space space) {
    this.space = Objects.requireNonNull(space);
    this.leftKey = Keystroke.NOT_PRESSED;
    this.rightKey = Keystroke.NOT_PRESSED;
    this.spaceKey = Keystroke.NOT_PRESSED;
    setScreenSize(space.width(), space.height());
    setFocusable(true);
    addKeyListener(this);
  }

  @Override
  protected void paintGameComponent(Graphics g) {
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
    space.fireAliensLasers();
    space.defeatLaserCannon();
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
}
