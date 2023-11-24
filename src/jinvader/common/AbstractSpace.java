package jinvader.common;

import java.awt.Rectangle;
import java.util.List;

public abstract class AbstractSpace implements InvaderComponent {

  private final List<AbstractAlien> aliens;
  private final AbstractLaserCannon laserCannon;

  public AbstractSpace() {
    this.aliens = newAliens();
    this.laserCannon = newLaserCannon();
  }

  @Override
  public final Rectangle asRectangle() {
    return new Rectangle(x(), y(), width(), height());
  }

  @Override
  public final int x() {
    return 0;
  }

  @Override
  public final int y() {
    return 0;
  }

  public final void moveLaserCannonToLeft() {
    laserCannon.moveLeft();
  }

  public final void moveLaserCannonToRight() {
    laserCannon.moveRight();
  }

  public final void fireLaserCannon() {
    laserCannon.fire();
  }

  public final void moveAliens() {
    aliens.forEach(AbstractAlien::move);
  }

  public final void moveLasers() {
    laserCannon.getLasers().forEach(AbstractLaser::move);
  }

  public final void defeatAliens() {
    laserCannon.getLasers().stream()
        .filter(AbstractLaser::isFiring)
        .forEach(
            laser -> {
              aliens.stream()
                  .filter(AbstractAlien::isAlive)
                  .filter(alien -> alien.asRectangle().intersects(laser.asRectangle()))
                  .forEach(AbstractAlien::die);
            });
  }

  protected List<AbstractAlien> getAliens() {
    return aliens;
  }

  protected final AbstractLaserCannon getLaserCannon() {
    return laserCannon;
  }

  protected abstract List<AbstractAlien> newAliens();

  protected abstract AbstractLaserCannon newLaserCannon();
}
