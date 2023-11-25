package jinvader.common;

import java.awt.Point;
import java.awt.Rectangle;

public abstract class AbstractAlien implements InvaderComponent, Speedy {

  private final AbstractSpace space;
  private final Point point;
  private final IntRange horizontalRange;
  private Direction direction;
  private boolean isAlive;

  public AbstractAlien(AbstractSpace space, Point point, IntRange horizontalRange) {
    this.space = space;
    this.point = new Point(point);
    this.horizontalRange = horizontalRange;
    this.direction = Direction.RIGHT;
    this.isAlive = true;
  }

  @Override
  public AbstractSpace getSpace() {
    return space;
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

  public boolean isAlive() {
    return isAlive;
  }

  public boolean isDead() {
    return !isAlive();
  }

  public void die() {
    isAlive = false;
  }

  public void move() {
    if (isDead()) return;

    switch (direction) {
      case LEFT -> {
        point.translate(-speed(), 0);
        if (point.x < horizontalRange.startInclusive()) {
          point.x = horizontalRange.startInclusive();
          point.y += height();
          direction = Direction.RIGHT;
        }
      }
      case RIGHT -> {
        point.translate(speed(), 0);
        if (point.x + width() > horizontalRange.endInclusive()) {
          point.x = horizontalRange.endInclusive() - width();
          point.y += height();
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
