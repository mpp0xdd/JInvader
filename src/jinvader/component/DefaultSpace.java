package jinvader.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import jinvader.common.AbstractAlien;
import jinvader.common.AbstractLaserCannon;
import jinvader.common.AbstractSpace;
import jinvader.common.IntRange;

public class DefaultSpace extends AbstractSpace {

  @Override
  public void draw(Graphics g) {
    if (g instanceof Graphics2D g2) {
      g2.setColor(Color.BLACK);
      g2.fill(asRectangle());
      getAlien().draw(g2);
      getLaserCannon().draw(g2);
      return;
    }
    throw (new RuntimeException("Drawing failed because it is not a Graphics2D instance."));
  }

  @Override
  public int width() {
    return 640;
  }

  @Override
  public int height() {
    return 480;
  }

  @Override
  protected AbstractAlien newAlien() {
    return new DefaultAlien(this, new Point(100, 100), IntRange.of(100, 300));
  }

  @Override
  protected AbstractLaserCannon newLaserCannon() {
    return new DefaultLaserCannon(this, new Point(0, height() - 30));
  }
}
