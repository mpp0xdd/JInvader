package jinvader.common;

import jglib.util.spec.Drawable;
import jglib.util.spec.Rectangular;

public interface InvaderComponent extends Drawable, Rectangular {
  Space getSpace();
}
