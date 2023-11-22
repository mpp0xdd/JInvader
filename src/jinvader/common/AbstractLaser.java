package jinvader.common;

import java.awt.Point;
import java.awt.Rectangle;

public abstract class AbstractLaser implements Drawable, Rectangular {

  private final AbstractLaserCannon laserCannon;
  private final Point point;

  public AbstractLaser(AbstractLaserCannon laserCannon) {
    this.laserCannon = laserCannon;
    this.point = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);
  }

  @Override
  public final Rectangle asRectangle() {
    return new Rectangle(x(), y(), width(), height());
  }

  @Override
  public final int x() {
    return point.x;
  }

  @Override
  public final int y() {
    return point.y;
  }

  public abstract int speed();

  public boolean isFireable() {
    Rectangle spaceRect = laserCannon.getSpace().asRectangle();
    return !spaceRect.intersects(this.asRectangle());
  }

  public void fire() {
    if (isFireable()) {
      point.move(laserCannon.x() + laserCannon.width() / 2, laserCannon.y());
    }
  }

  public void move() {
    if (!isFireable()) {
      point.translate(0, -speed());
    }
  }
}
