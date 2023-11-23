package jinvader.common;

public final class IntRange {

  public static IntRange of(int startInclusive, int endInclusive) {
    return new IntRange(startInclusive, endInclusive);
  }

  private final int startInclusive;
  private final int endInclusive;

  private IntRange(int startInclusive, int endInclusive) {
    if (startInclusive >= endInclusive) {
      throw (new IllegalArgumentException(
          String.format(
              "Argument is invalid. startInclusive:%d, endInclusive:%d",
              startInclusive, endInclusive)));
    }
    this.startInclusive = startInclusive;
    this.endInclusive = endInclusive;
  }

  public int startInclusive() {
    return startInclusive;
  }

  public int endInclusive() {
    return endInclusive;
  }
}
