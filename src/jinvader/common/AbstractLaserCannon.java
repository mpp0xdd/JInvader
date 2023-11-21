package jinvader.common;

import java.awt.Point;
import java.awt.Rectangle;

public abstract class AbstractLaserCannon implements Drawable, Rectangular {

  private final AbstractSpace space;
  private final Point point;

  public AbstractLaserCannon(AbstractSpace space, Point point) {
    this.space = space;
    this.point = new Point(point);
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
}
