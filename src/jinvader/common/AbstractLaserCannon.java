package jinvader.common;

import java.awt.Point;
import java.util.List;

public abstract class AbstractLaserCannon implements InvaderComponent, Speedy {

  private final AbstractSpace space;
  private final Point point;
  private final List<AbstractLaserCannonsLaser> lasers;
  private long lastFireTimeMillis;

  public AbstractLaserCannon(AbstractSpace space, Point point) {
    this.space = space;
    this.point = new Point(point);
    this.lasers = newLasers();
    this.lastFireTimeMillis = 0L;
  }

  @Override
  public AbstractSpace getSpace() {
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
    if (System.currentTimeMillis() - lastFireTimeMillis < fireInterval()) {
      return;
    }

    lasers.stream()
        .filter(AbstractLaser::isFireable)
        .findFirst()
        .ifPresent(
            laser -> {
              laser.fire();
              lastFireTimeMillis = System.currentTimeMillis();
            });
  }

  protected abstract long fireInterval();

  protected abstract List<AbstractLaserCannonsLaser> newLasers();

  protected List<AbstractLaserCannonsLaser> getLasers() {
    return lasers;
  }
}
