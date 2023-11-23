package jinvader.common;

import java.awt.Point;
import java.awt.Rectangle;

public abstract class AbstractAlien implements InvaderComponent, Speedy {

  private final AbstractSpace space;
  private final Point point;
  private final IntRange horizontalRange;
  private Direction direction;

  public AbstractAlien(AbstractSpace space, Point point, IntRange horizontalRange) {
    this.space = space;
    this.point = new Point(point);
    this.horizontalRange = horizontalRange;
    this.direction = Direction.RIGHT;
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

  public void move() {
    switch (direction) {
      case LEFT -> {
        point.translate(-speed(), 0);
        if (point.x < horizontalRange.startInclusive()) {
          point.x = horizontalRange.startInclusive();
          direction = Direction.RIGHT;
        }
      }
      case RIGHT -> {
        point.translate(speed(), 0);
        if (point.x + width() > horizontalRange.endInclusive()) {
          point.x = horizontalRange.endInclusive() - width();
          direction = Direction.LEFT;
        }
      }
    }
  }

  private enum Direction {
    LEFT,
    RIGHT,
    ;
  }
}
