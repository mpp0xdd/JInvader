package jinvader.common;

import java.awt.Point;
import java.awt.Rectangle;

public abstract class AbstractLaserCannon implements InvaderComponent, Speedy {

  private final AbstractSpace space;
  private final Point point;
  private final AbstractLaser laser;

  public AbstractLaserCannon(AbstractSpace space, Point point) {
    this.space = space;
    this.point = new Point(point);
    this.laser = newLaser();
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

  public void moveLeft() {
    point.translate(-speed(), 0);
    if (!space.asRectangle().contains(this.asRectangle())) {
      point.x = space.x();
    }
  }

  public void moveRight() {
    point.translate(speed(), 0);
    if (!space.asRectangle().contains(this.asRectangle())) {
      point.x = space.width() - this.width();
    }
  }

  public void fire() {
    laser.fire();
  }

  protected abstract AbstractLaser newLaser();

  protected AbstractLaser getLaser() {
    return laser;
  }

  AbstractSpace getSpace() {
    return space;
  }
}
