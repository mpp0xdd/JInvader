package jinvader.common;

import java.awt.Point;

public abstract class Alien implements InvaderComponent, Speedy {

  private final Space space;
  private final Point point;
  private final IntRange horizontalRange;
  private Direction direction;
  private boolean isAlive;

  public Alien(Space space, Point point, IntRange horizontalRange) {
    this.space = space;
    this.point = new Point(point);
    this.horizontalRange = horizontalRange;
    this.direction = Direction.RIGHT;
    this.isAlive = true;
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
