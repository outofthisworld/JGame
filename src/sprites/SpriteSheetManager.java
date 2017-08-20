package sprites;

import utils.ImmutablePair;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by SeventhSense on 8/19/2017.
 */
public class SpriteSheetManager {

    private static final String SPRITE_SHEET_DIRECTORY = "src/res/spritesheets";
    private static final Pattern SPRITE_SHEET_MATCHER = Pattern.compile(".*\\[(\\d+)x(\\d+)\\].*");
    private final Map<String, SpriteSheet> spriteSheets = new HashMap<>();
    private final List<CompletableFuture<ImmutablePair<String, SpriteSheet>>> completableFutures = new ArrayList<>();
    private boolean isLoaded = false;

    /**
     * Load sprite sheets boolean.
     *
     * @return the boolean
     * @throws ExecutionException    the execution exception
     * @throws InterruptedException  the interrupted exception
     * @throws IOException           the io exception
     * @throws CancellationException the cancellation exception
     * @throws CompletionException   the completion exception
     */
    public boolean loadSpriteSheets() throws ExecutionException, InterruptedException, IOException, CancellationException, CompletionException {
        if (isLoaded) return false;

        File file = new File(SPRITE_SHEET_DIRECTORY);

        Files.walkFileTree(Paths.get(file.getAbsolutePath()), new SpriteSheetVisitor());

        try {
            CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[]{})).join();

            for (int i = 0; i < completableFutures.size(); i++) {
                CompletableFuture<ImmutablePair<String, SpriteSheet>> cF = completableFutures.get(i);
                ImmutablePair<String, SpriteSheet> immutablePair = cF.get();

                if (immutablePair == null) {
                    throw new IOException("Unable to load sprite sheet");
                }

                spriteSheets.put(immutablePair.getFirst(), immutablePair.getSecond());
            }

        } catch (CompletionException | CancellationException | InterruptedException | ExecutionException e) {
            throw e;
        }


        return isLoaded = true;
    }

    /**
     * Gets sprite sheet.
     *
     * @param key the key
     * @return the sprite sheet
     */
    public SpriteSheet getSpriteSheet(String key) {
        return spriteSheets.get(key);
    }

    private final class SpriteSheetVisitor implements FileVisitor<Path> {
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            completableFutures.add(CompletableFuture.supplyAsync(() -> {
                String fileName = file.getFileName().toString();
                String fileNameNoSuffix = file.getParent().getFileName() + "." + fileName.substring(0, fileName.lastIndexOf("."));

                Matcher m = SPRITE_SHEET_MATCHER.matcher(fileName);


                if (m.matches()) {
                    try {
                        return new ImmutablePair<>(fileNameNoSuffix.replaceAll("\\[.*\\]", ""),
                                new SpriteSheet(ImageIO.read(file.toFile()),
                                        Integer.valueOf(m.group(1)), Integer.valueOf(m.group(2))));
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


                System.out.println("returning null no matches on " + fileName);
                return null;
            }));

            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            return FileVisitResult.CONTINUE;
        }
    }
}
