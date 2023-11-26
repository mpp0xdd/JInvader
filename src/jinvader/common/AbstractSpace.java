package jinvader.common;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;

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
  public final int x() {
    return 0;
  }

  @Override
  public final int y() {
    return 0;
  }

  public boolean isGameOver() {
    if (aliens.stream().allMatch(AbstractAlien::isDead)) {
      return true;
    }
    if (aliens.stream().anyMatch(Predicate.not(this::intersects))) {
      return true;
    }
    return false;
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
        .filter(AbstractLaser::isFireable)
        .forEach(
            laser -> {
              randomElement(aliveAliens).ifPresent(laser::setBattery);
              laser.fire();
            });
  }

  public final void moveAliens() {
    aliens.forEach(AbstractAlien::move);
  }

  public final void moveLasers() {
    laserCannon.getLasers().forEach(AbstractLaser::move);
    aliensLasers.forEach(AbstractLaser::move);
  }

  public final void defeatAliens() {
    laserCannon.getLasers().stream()
        .filter(AbstractLaser::isFiring)
        .forEach(
            laser -> {
              aliens.stream()
                  .filter(AbstractAlien::isAlive)
                  .filter(laser::intersects)
                  .forEach(
                      alien -> {
                        alien.die();
                        laser.enableToFire();
                      });
            });
  }

  public final void defeatLaserCannon() {
    aliensLasers.stream()
        .filter(AbstractLaser::isFiring)
        .filter(laserCannon::intersects)
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

  private <T> Optional<T> randomElement(List<T> list) {
    return list.stream().skip(random.nextLong(list.size())).findFirst();
  }
}
