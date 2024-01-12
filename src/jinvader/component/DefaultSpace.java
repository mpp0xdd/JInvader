package jinvader.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import jglib.util.GameUtilities;
import jinvader.common.Alien;
import jinvader.common.AliensLaser;
import jinvader.common.LaserCannon;
import jinvader.common.Space;
import jinvader.common.IntRange;

public class DefaultSpace extends Space {

  @Override
  public void draw(Graphics g) {
    if (g instanceof Graphics2D g2) {
      g2.setColor(Color.BLACK);
      g2.fill(asRectangle());
      getAliens().forEach(alien -> alien.draw(g2));
      getAliensLasers().forEach(laser -> laser.draw(g2));
      getLaserCannon().draw(g2);
      if (isGameOver()) {
        g2.setColor(Color.WHITE);
        g2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 43));
        GameUtilities.drawStringAfterCentering(g2, width() / 2, height() / 2, "Game Over!");
      }
      return;
    }
    throw (new RuntimeException("Drawing failed because it is not a Graphics2D instance."));
  }

  @Override
  public int width() {
    return 640;
  }

  @Override
  public int height() {
    return 480;
  }

  @Override
  protected List<Alien> newAliens() {
    List<Alien> aliens = new ArrayList<>();
    final int rows = 5;
    final int columns = 11;
    final int margin = 25;
    final int moveLength = 150;
    final Point point = new Point(50, 50);

    List<Alien> firstRow = new ArrayList<>();
    for (int j = 0; j < columns; j++) {
      Alien alien = newAlien(point, IntRange.of(point.x, point.x + moveLength));
      firstRow.add(alien);
      point.translate(alien.width() + margin, 0);
    }
    aliens.addAll(firstRow);

    for (int i = 1; i < rows; i++) {
      List<Alien> row = new ArrayList<>();
      point.y = firstRow.get(0).y() + (firstRow.get(0).height() + margin) * i;
      for (int j = 0; j < columns; j++) {
        point.x = firstRow.get(j).x();
        row.add(newAlien(point, IntRange.of(point.x, point.x + moveLength)));
      }
      aliens.addAll(row);
    }

    return Collections.unmodifiableList(aliens);
  }

  @Override
  protected List<AliensLaser> newAliensLasers() {
    List<AliensLaser> aliensLasers = new ArrayList<>();
    for (int i = 0; i < 25; i++) {
      aliensLasers.add(newAliensLaser());
    }
    return Collections.unmodifiableList(aliensLasers);
  }

  @Override
  protected LaserCannon newLaserCannon() {
    return new DefaultLaserCannon(this, new Point(0, height() - 30));
  }

  private Alien newAlien(Point point, IntRange range) {
    return new DefaultAlien(this, point, range);
  }

  private AliensLaser newAliensLaser() {
    return new DefaultAliensLaser(getAliens().get(0));
  }
}
