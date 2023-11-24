package jinvader.common;

import java.awt.Point;
import java.awt.Rectangle;

public abstract class AbstractLaser implements InvaderComponent, Speedy {

  private final InvaderComponent battery;
  protected final Point point;

  public AbstractLaser(InvaderComponent battery) {
    this.battery = battery;
    this.point = new Point();
    enableToFire();
  }

  @Override
  public AbstractSpace getSpace() {
    return battery.getSpace();
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

  public boolean isFireable() {
    Rectangle spaceRect = getSpace().asRectangle();
    return !spaceRect.intersects(this.asRectangle());
  }

  public boolean isFiring() {
    return !isFireable();
  }

  public void enableToFire() {
    point.move(Integer.MIN_VALUE, Integer.MIN_VALUE);
  }

  public void fire() {
    if (isFireable()) {
      point.move(battery.x() + battery.width() / 2, battery.y());
    }
  }

  public abstract void move();
}
