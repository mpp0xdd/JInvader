package jinvader.common;

import java.awt.Point;
import java.awt.Rectangle;

public abstract class AbstractAlien implements InvaderComponent {

  private final AbstractSpace space;
  private final Point point;

  public AbstractAlien(AbstractSpace space, Point point) {
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
}
