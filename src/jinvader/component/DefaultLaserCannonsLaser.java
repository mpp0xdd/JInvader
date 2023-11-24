package jinvader.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import jinvader.common.AbstractLaser;
import jinvader.common.AbstractLaserCannon;

class DefaultLaserCannonsLaser extends AbstractLaser {

  public DefaultLaserCannonsLaser(AbstractLaserCannon laserCannon) {
    super(laserCannon);
  }

  @Override
  public void draw(Graphics g) {
    if (g instanceof Graphics2D g2) {
      g2.setColor(Color.RED);
      g2.fill(asRectangle());
      return;
    }
    throw (new RuntimeException("Drawing failed because it is not a Graphics2D instance."));
  }

  @Override
  public void fire() {
    if (isFireable()) {
      point.move(getBattery().x() + getBattery().width() / 2, getBattery().y());
    }
  }

  @Override
  public void move() {
    if (isFiring()) {
      point.translate(0, -speed());
    }
  }

  @Override
  public int width() {
    return 2;
  }

  @Override
  public int height() {
    return 8;
  }

  @Override
  public int speed() {
    return 10;
  }
}
