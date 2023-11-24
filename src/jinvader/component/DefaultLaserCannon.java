package jinvader.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import jinvader.common.AbstractLaser;
import jinvader.common.AbstractLaserCannon;
import jinvader.common.AbstractSpace;

class DefaultLaserCannon extends AbstractLaserCannon {

  public DefaultLaserCannon(AbstractSpace space, Point point) {
    super(space, point);
  }

  @Override
  public void draw(Graphics g) {
    if (g instanceof Graphics2D g2) {
      // Draw this.
      g2.setColor(Color.GREEN);
      g2.fill(asRectangle());
      // Draw lasers.
      getLasers().forEach(laser -> laser.draw(g2));
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

  @Override
  protected long fireInterval() {
    return 255L;
  }

  @Override
  protected List<AbstractLaser<?>> newLasers() {
    List<AbstractLaser<?>> lasers = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      lasers.add(new DefaultLaserCannonsLaser(this));
    }
    return Collections.unmodifiableList(lasers);
  }
}
