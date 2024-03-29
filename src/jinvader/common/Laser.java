package jinvader.common;

import java.awt.Point;
import java.util.Objects;

abstract class Laser<C extends InvaderComponent> implements InvaderComponent, Speedy {

  private C battery;
  protected final Point point;

  public Laser(C battery) {
    this.point = new Point();
    setBattery(battery);
    enableToFire();
  }

  @Override
  public Space getSpace() {
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
    return !getSpace().intersects(this);
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
