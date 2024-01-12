package jinvader.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import jinvader.common.Alien;
import jinvader.common.AliensLaser;

class DefaultAliensLaser extends AliensLaser {

  public DefaultAliensLaser(Alien alien) {
    super(alien);
  }

  @Override
  public void draw(Graphics g) {
    if (g instanceof Graphics2D g2) {
      g2.setColor(Color.ORANGE);
      g2.fill(asRectangle());
      return;
    }
    throw (new RuntimeException("Drawing failed because it is not a Graphics2D instance."));
  }

  @Override
  public void fire() {
    if (isFireable() && getBattery().isAlive()) {
      point.move(
          getBattery().x() + getBattery().width() / 2, getBattery().y() + getBattery().height());
    }
  }

  @Override
  public void move() {
    if (isFiring()) {
      point.translate(0, speed());
    }
  }

  @Override
  public int width() {
    return 2;
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
