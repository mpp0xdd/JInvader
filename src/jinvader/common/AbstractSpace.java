package jinvader.common;

import java.awt.Rectangle;
import java.util.List;
import java.util.Random;

public abstract class AbstractSpace implements Drawable, Rectangular {

  private List<AbstractAlien> aliens;
  private List<AbstractAliensLaser> aliensLasers;
  private AbstractLaserCannon laserCannon;
  private final Random random;

  public AbstractSpace() {
    initializeComponent();
    this.random = new Random();
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

  public boolean isGameOver() {
    return aliens.stream().filter(AbstractAlien::isAlive).count() == 0L;
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

  public final void fireAliensLasers() {
    List<AbstractAlien> aliveAliens = aliens.stream().filter(AbstractAlien::isAlive).toList();
    if (aliveAliens.isEmpty()) return;

    aliensLasers.stream()
        .filter(AbstractAliensLaser::isFireable)
        .forEach(
            laser -> {
              aliveAliens.stream()
                  .skip(random.nextLong(aliveAliens.size()))
                  .findFirst()
                  .ifPresent(laser::setBattery);
              laser.fire();
            });
  }

  public final void moveAliens() {
    aliens.forEach(AbstractAlien::move);
  }

  public final void moveLasers() {
    laserCannon.getLasers().forEach(AbstractLaser::move);
    aliensLasers.forEach(AbstractAliensLaser::move);
  }

  public final void defeatAliens() {
    laserCannon.getLasers().stream()
        .filter(AbstractLaser::isFiring)
        .forEach(
            laser -> {
              aliens.stream()
                  .filter(AbstractAlien::isAlive)
                  .filter(alien -> intersects(alien, laser))
                  .forEach(
                      alien -> {
                        alien.die();
                        laser.enableToFire();
                      });
            });
  }

  public final void defeatLaserCannon() {
    aliensLasers.stream()
        .filter(AbstractAliensLaser::isFiring)
        .filter(laser -> intersects(laser, laserCannon))
        .findFirst()
        .ifPresent(
            laser -> {
              laser.enableToFire();
              initializeComponent();
            });
  }

  protected List<AbstractAlien> getAliens() {
    return aliens;
  }

  protected List<AbstractAliensLaser> getAliensLasers() {
    return aliensLasers;
  }

  protected final AbstractLaserCannon getLaserCannon() {
    return laserCannon;
  }

  protected abstract List<AbstractAlien> newAliens();

  protected abstract List<AbstractAliensLaser> newAliensLasers();

  protected abstract AbstractLaserCannon newLaserCannon();

  private void initializeComponent() {
    this.aliens = newAliens();
    this.aliensLasers = newAliensLasers();
    this.laserCannon = newLaserCannon();
  }

  private boolean intersects(Rectangular r1, Rectangular r2) {
    return r1.asRectangle().intersects(r2.asRectangle());
  }
}
