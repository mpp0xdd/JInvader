package jinvader;

import jglib.component.GameWindow;
import jinvader.component.DefaultSpace;
import jinvader.screen.MainScreen;

public class JInvader {

  public static void main(String[] args) {
    GameWindow window = new GameWindow("JInvader");
    MainScreen screen = new MainScreen(new DefaultSpace());

    window.switchGameScreen(screen);
    window.setVisible(true);
    screen.startGameLoop();
  }
}
