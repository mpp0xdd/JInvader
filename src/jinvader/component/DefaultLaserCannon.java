package jinvader.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import jinvader.common.AbstractLaserCannon;
import jinvader.common.AbstractSpace;

class DefaultLaserCannon extends AbstractLaserCannon {

  public DefaultLaserCannon(AbstractSpace space, Point point) {
    super(space, point);
  }

  @Override
  public void draw(Graphics g) {
    if (g instanceof Graphics2D g2) {
      g2.setColor(Color.GREEN);
      g2.fill(asRectangle());
      return;
    }
    throw (new RuntimeException("Drawing failed because it is not a Graphics2D instance."));
  }

  @Override
  public int width() {
    return 10;
  }

  @Override
  public int height() {
    return 10;
  }

  @Override
  public int speed() {
    return 5;
  }
}
