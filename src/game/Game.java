package game;


import display.GameRenderer;
import display.Screen;
import entity.GameObject;
import entity.Player;
import input.KeyboardManager;
import input.MouseManager;
import sprites.SpriteSheetManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.*;
import java.util.logging.Logger;
import java.util.stream.Stream;


/**
 * Created by SeventhSense on 8/18/2017.
 */
public final class Game implements GameObject {
    private static final Logger logger = Logger.getLogger(Game.class.getName());

    /*
        The singleton game object
     */
    private static final Game GAME = new Game();
    private final int GAME_WIDTH = 800;
    private final int GAME_HEIGHT = 600;
    /*
        The game screen
    */
    private final Screen screen = new Screen("Game", GAME_WIDTH, GAME_HEIGHT);
    /*
        The renderer which draws to the screen, provides convenience methods on top of the graphics2D object specific to this game.
    */
    private final GameRenderer gameRenderer = new GameRenderer(screen);

    /*
        The sprite manager, loads sprites from the res/spritesheets folder
        Sprite sheets can then be retrieved using spriteSheetManager.getSpriteSheet("parentfolder.spritesheetname");
    */
    private final SpriteSheetManager spriteSheetManager = new SpriteSheetManager();

    /*
        The game service. The main game loop runs on this service. Note that the uncaught exception handler
        will only work on tasks submitted to service via the .submit method.

        Other tasks submitted that throw exceptions will be handled by the games `handleGameLoopExceptions` method
    */
    private final ScheduledExecutorService gameService = Executors.newSingleThreadScheduledExecutor(r -> {
        Thread thread = new Thread(r);
        thread.setName("MainGameThread");
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.setUncaughtExceptionHandler((t, e) -> {
            e.printStackTrace();
        });
        return thread;
    });

    /*
        The ScheduledFuture returned by the game services scheduled executor service.
        This will be awaited by the thread the submits the game task to the scheduled executor service
        allowing it to handle any exceptions thrown by the main game loop.
    */
    private ScheduledFuture<?> gameFuture;

    /*
        The game start time, set when the game is first started.
    */
    private long gameStartTime;

    private long lastTickTime = 0l;

    private GameObject player;

    private boolean initialized = false;

    private Game() {
    }

    /**
     * Get game.
     *
     * Note that the game is initialized here due to game objects
     * having to be created AFTER the game constructor has run.
     *
     * If objects are created within the Game constructor,
     * they are unable to access to the Game singleton instance
     * due to the game object still being constructed whilst the objects are created.
     *
     * @return the game
     */
    public static Game get() {
        if (!GAME.initialized) {
            GAME.initialized = !GAME.initialized;
            GAME.init();
        }
        return GAME;
    }

    private final void loadResources() {
        Stream.of((Runnable) () -> {
            try {
                spriteSheetManager.loadSpriteSheets();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).parallel().forEach(r -> r.run());
    }

    private final void init() {
        //Loads game resources in parallel.
        loadResources();

        screen.getCanvas().requestFocus();
        screen.getCanvas().addKeyListener(new KeyboardManager());
        screen.getCanvas().addMouseListener(new MouseManager());

        //Start the game on the scheduled executor service.
        gameStartTime = System.currentTimeMillis();

        player = new Player();

        gameFuture = getGameService().scheduleAtFixedRate(this::tick, 0, 1000l / 60l, TimeUnit.MILLISECONDS);

        //Blocks the current thread and waits for any exceptions triggered on the game thread. Any game set-up should happen before
        //this method is called.
        handleGameLoopExceptions();
    }

    public void render(GameRenderer g) {
        Graphics2D graphics = g.getGraphics();

        if (graphics == null) return;

        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, screen.getWidth(), screen.getHeight());
        player.render(g);
        screen.render();
    }

    @Override
    public void update(double deltaT) {
        player.update(deltaT);
    }

    /**
     * Tick int.
     *
     * @return the int
     */
    public int tick() {
        double deltaTime = (System.currentTimeMillis() - lastTickTime) / 1000d;
        lastTickTime = System.currentTimeMillis();
        update(deltaTime);
        render(gameRenderer);
        return 1;
    }

    public int getGameWidth() {
        return this.GAME_WIDTH;
    }

    public int getGameHeight() {
        return this.GAME_HEIGHT;
    }

    /**
     * Get game service scheduled executor service.
     *
     * @return the scheduled executor service
     */
    public ScheduledExecutorService getGameService() {
        return gameService;
    }

    /**
     * Gets sprite sheet manager.
     *
     * @return the sprite sheet manager
     */
    public SpriteSheetManager getSpriteSheetManager() {
        return spriteSheetManager;
    }

    /**
     * Gets game renderer.
     *
     * @return the game renderer
     */
    public GameRenderer getGameRenderer() {
        return gameRenderer;
    }

    /**
     * Get game start time long.
     *
     * @param unit the unit
     * @return the long
     */
    public long getGameStartTime(TimeUnit unit) {
        return unit.convert(gameStartTime, TimeUnit.MILLISECONDS);
    }

    private final void handleGameLoopExceptions() {
        try {
            logger.info(Thread.currentThread().getName() + " thread - handling game loop exceptions.");
            gameFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(screen.getFrame(), e.getMessage(), "Error encountered", JOptionPane.ERROR_MESSAGE);
        }
    }
}
