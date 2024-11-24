package jinvader;

import jglib.component.GameWindow;
import jglib.core.Game;
import jinvader.component.DefaultSpace;
import jinvader.screen.MainScreen;

public class JInvader extends Game {

  public static void main(String[] args) {
    launch(JInvader.class);
  }

  @Override
  protected void start() {
    GameWindow window = new GameWindow("JInvader");
    MainScreen screen = new MainScreen(new DefaultSpace());

    window.switchGameScreen(screen);
    window.setVisible(true);
    screen.startGameLoop();
  }
}
