package jinvader.common;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Objects;

abstract class AbstractLaser<C extends InvaderComponent> implements InvaderComponent, Speedy {

  private C battery;
  protected final Point point;

  public AbstractLaser(C battery) {
    this.point = new Point();
    setBattery(battery);
    enableToFire();
  }

  @Override
  public AbstractSpace getSpace() {
    return battery.getSpace();
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

  public abstract void fire();

  public abstract void move();

  public C getBattery() {
    return battery;
  }

  protected void setBattery(C battery) {
    this.battery = Objects.requireNonNull(battery);
  }
}
