package jinvader.common;

import java.awt.Point;
import java.util.List;

public abstract class LaserCannon implements InvaderComponent, Speedy {

  private final Space space;
  private final Point point;
  private final List<LaserCannonsLaser> lasers;
  private long lastFireTimeMillis;

  public LaserCannon(Space space, Point point) {
    this.space = space;
    this.point = new Point(point);
    this.lasers = newLasers();
    this.lastFireTimeMillis = 0L;
  }

  @Override
  public Space getSpace() {
    return space;
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
    if (!getSpace().contains(this)) {
      point.x = space.x();
    }
  }

  public void moveRight() {
    point.translate(speed(), 0);
    if (!getSpace().contains(this)) {
      point.x = space.width() - this.width();
    }
  }

  public void fire() {
    if (System.currentTimeMillis() - lastFireTimeMillis < fireInterval()) {
      return;
    }

    lasers.stream()
        .filter(Laser::isFireable)
        .findFirst()
        .ifPresent(
            laser -> {
              laser.fire();
              lastFireTimeMillis = System.currentTimeMillis();
            });
  }

  protected abstract long fireInterval();

  protected abstract List<LaserCannonsLaser> newLasers();

  protected List<LaserCannonsLaser> getLasers() {
    return lasers;
  }
}
