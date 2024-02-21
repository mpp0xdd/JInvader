package jinvader.common;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public abstract class Space implements Drawable, Rectangular {

  private List<Alien> aliens;
  private List<AliensLaser> aliensLasers;
  private LaserCannon laserCannon;
  private final Random random;

  public Space() {
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
    if (aliens.stream().allMatch(Alien::isDead)) {
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
    List<Alien> aliveAliens = aliens.stream().filter(Alien::isAlive).toList();
    if (aliveAliens.isEmpty()) return;

    aliensLasers.stream()
        .filter(Laser::isFireable)
        .forEach(
            laser -> {
              laser.setBattery(randomElement(aliveAliens));
              laser.fire();
            });
  }

  public void moveAliens() {
    aliens.forEach(Alien::move);
  }

  public void moveLasers() {
    laserCannon.getLasers().forEach(Laser::move);
    aliensLasers.forEach(Laser::move);
  }

  public void defeatAliens() {
    for (LaserCannonsLaser laser : laserCannon.getLasers()) {
      if (laser.isFireable()) continue;

      int defeats = 0;
      for (Alien alien : aliens) {
        if (alien.isAlive() && laser.intersects(alien)) {
          alien.die();
          defeats++;
        }
      }
      if (defeats > 0) laser.enableToFire();
    }
  }

  public void defeatLaserCannon() {
    if (aliensLasers.stream() //
        .filter(Laser::isFiring)
        .anyMatch(laserCannon::intersects)) {
      initializeComponent();
    }
  }

  protected List<Alien> getAliens() {
    return aliens;
  }

  protected List<AliensLaser> getAliensLasers() {
    return aliensLasers;
  }

  protected LaserCannon getLaserCannon() {
    return laserCannon;
  }

  protected abstract List<Alien> newAliens();

  protected abstract List<AliensLaser> newAliensLasers();

  protected abstract LaserCannon newLaserCannon();

  private void initializeComponent() {
    this.aliens = newAliens();
    this.aliensLasers = newAliensLasers();
    this.laserCannon = newLaserCannon();
  }

  private <T> T randomElement(List<T> list) {
    return list.get(random.nextInt(list.size()));
  }
}
