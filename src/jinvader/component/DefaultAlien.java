package jinvader.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import jinvader.common.Alien;
import jinvader.common.Space;
import jinvader.common.IntRange;

class DefaultAlien extends Alien {

  public DefaultAlien(Space space, Point point, IntRange horizontalRange) {
    super(space, point, horizontalRange);
  }

  @Override
  public void draw(Graphics g) {
    if (isDead()) return;

    if (g instanceof Graphics2D g2) {
      g2.setColor(Color.WHITE);
      g2.fill(asRectangle());
      return;
    }
    throw (new RuntimeException("Drawing failed because it is not a Graphics2D instance."));
  }

  @Override
  public int width() {
    return 15;
  }

  @Override
  public int height() {
    return 10;
  }

  @Override
  public int speed() {
    return 1;
  }
}
