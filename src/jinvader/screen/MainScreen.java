package jinvader.screen;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Objects;
import jglib.component.GameScreen;
import jglib.util.model.Key;
import jglib.util.model.Keyboard;
import jinvader.common.Space;

public class MainScreen extends GameScreen {

  private enum OperationKey implements Key {
    LEFT(KeyEvent.VK_LEFT),
    RIGHT(KeyEvent.VK_RIGHT),
    SPACE(KeyEvent.VK_SPACE);

    private final int code;

    private OperationKey(int code) {
      this.code = code;
    }

    @Override
    public int code() {
      return code;
    }
  }

  private final Space space;
  private final Keyboard<OperationKey> keyboard;

  public MainScreen(Space space) {
    this.space = Objects.requireNonNull(space);
    this.keyboard = Keyboard.create(OperationKey.values());

    setScreenSize(space.width(), space.height());
    setFocusable(true);
    addKeyListener(keyboard.asKeyListener());
  }

  @Override
  protected void paintGameComponent(Graphics g) {
    space.draw(g);
  }

  @Override
  protected void runGameLoop() {
    if (keyboard.isPressed(OperationKey.LEFT)) space.moveLaserCannonToLeft();
    if (keyboard.isPressed(OperationKey.RIGHT)) space.moveLaserCannonToRight();
    if (keyboard.isPressed(OperationKey.SPACE)) space.fireLaserCannon();
    space.moveAliens();
    space.moveLasers();
    space.defeatAliens();
    space.fireAliensLasers();
    space.defeatLaserCannon();
  }
}
