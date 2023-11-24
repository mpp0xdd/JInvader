package jinvader.common;

import java.util.Objects;

public abstract class AbstractAliensLaser extends AbstractLaser<AbstractAlien> {
  public AbstractAliensLaser(AbstractAlien battery) {
    super(battery);
  }

  @Override
  protected void setBattery(AbstractAlien battery) {
    if (Objects.isNull(getBattery())) {
      super.setBattery(battery);
    } else if (isFireable()) {
      super.setBattery(battery);
    } else {
      // nop
    }
  }
}
