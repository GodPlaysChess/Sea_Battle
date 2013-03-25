public class Main {

    public static final int FRAMES_PER_SECOND = 25;
    public final int SKIP_TICKS = 1000 / FRAMES_PER_SECOND;
    public final int MAX_FRAMESKIP = 6;


    public static void main(String[] args) {
        boolean game_is_running = true;
        Game game = new Game();
        long next_game_tick = System.currentTimeMillis();

        while (game_is_running) {
            game.update();
            game.display();

        try {
            Thread.sleep(50);
        } catch (InterruptedException Ignored) {
        }
        }
    }

}
