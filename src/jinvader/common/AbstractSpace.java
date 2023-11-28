package jinvader.common;

import java.util.List;
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

  public void moveLaserCannonToLeft() {
    laserCannon.moveLeft();
  }

  public void moveLaserCannonToRight() {
    laserCannon.moveRight();
  }

  public void fireLaserCannon() {
    laserCannon.fire();
  }

  public void fireAliensLasers() {
    List<AbstractAlien> aliveAliens = aliens.stream().filter(AbstractAlien::isAlive).toList();
    if (aliveAliens.isEmpty()) return;

    aliensLasers.stream()
        .filter(AbstractLaser::isFireable)
        .forEach(
            laser -> {
              laser.setBattery(randomElement(aliveAliens));
              laser.fire();
            });
  }

  public void moveAliens() {
    aliens.forEach(AbstractAlien::move);
  }

  public void moveLasers() {
    laserCannon.getLasers().forEach(AbstractLaser::move);
    aliensLasers.forEach(AbstractLaser::move);
  }

  public void defeatAliens() {
    for (AbstractLaserCannonsLaser laser : laserCannon.getLasers()) {
      if (laser.isFireable()) continue;

      int defeats = 0;
      for (AbstractAlien alien : aliens) {
        if (alien.isAlive() && laser.intersects(alien)) {
          alien.die();
          defeats++;
        }
      }
      if (defeats > 0) laser.enableToFire();
    }
  }

  public void defeatLaserCannon() {
    if (aliensLasers.stream().filter(AbstractLaser::isFiring).anyMatch(laserCannon::intersects))
      initializeComponent();
  }

  protected List<AbstractAlien> getAliens() {
    return aliens;
  }

  protected List<AbstractAliensLaser> getAliensLasers() {
    return aliensLasers;
  }

  protected AbstractLaserCannon getLaserCannon() {
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

  private <T> T randomElement(List<T> list) {
    return list.get(random.nextInt(list.size()));
  }
}
