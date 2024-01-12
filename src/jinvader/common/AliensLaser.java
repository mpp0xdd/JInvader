package jinvader.common;

import java.util.Objects;

public abstract class AliensLaser extends Laser<Alien> {
  public AliensLaser(Alien battery) {
    super(battery);
  }

  @Override
  protected void setBattery(Alien battery) {
    if (Objects.isNull(getBattery())) {
      super.setBattery(battery);
    } else if (isFireable()) {
      super.setBattery(battery);
    } else {
      // nop
    }
  }
}
