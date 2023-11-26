package jinvader.common;

import java.awt.Rectangle;

public interface Rectangular {

  Rectangle asRectangle();

  int x();

  int y();

  int width();

  int height();

  default boolean intersects(Rectangular other) {
    return this.asRectangle().intersects(other.asRectangle());
  }
}
