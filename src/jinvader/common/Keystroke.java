package jinvader.common;

public enum Keystroke {
  PRESSED,
  NOT_PRESSED,
  ;

  public boolean isPressed() {
    return this.equals(PRESSED);
  }
}
