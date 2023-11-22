package jinvader.common;

import java.awt.Rectangle;

public abstract class AbstractSpace implements Drawable, Rectangular {

  private final AbstractLaserCannon laserCannon;

  public AbstractSpace() {
    this.laserCannon = newLaserCannon();
  }

  @Override
  public final Rectangle asRectangle() {
    return new Rectangle(x(), y(), width(), height());
  }

  @Override
  public final int x() {
    return 0;
  }

  @Override
  public final int y() {
    return 0;
  }

  public final void moveLaserCannonToLeft() {
    laserCannon.moveLeft();
  }

  public final void moveLaserCannonToRight() {
    laserCannon.moveRight();
  }

  public final void fireLaserCannon() {
    laserCannon.fire();
  }

  public final void moveLaser() {
    laserCannon.getLaser().move();
  }

  protected final AbstractLaserCannon getLaserCannon() {
    return laserCannon;
  }

  protected abstract AbstractLaserCannon newLaserCannon();
}
