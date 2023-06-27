package com.arturoo404.game.generate;

import com.arturoo404.game.file.FileReader;
import com.opencsv.exceptions.CsvException;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MapGenerator {

    private final AnchorPane pane;

    public MapGenerator(AnchorPane pane) {
        this.pane = pane;
    }

    /**
     * Generates the map from the csv file
     * @return
     * @throws IOException
     * @throws CsvException
     */
    public List<Rectangle> init() throws IOException, CsvException {
        List<Rectangle> rectangleList = new ArrayList<>();

        FileReader csvReader = new FileReader();
        final List<String[]> read = csvReader.read();

        if (read.size() > 0){
            AtomicInteger xPos = new AtomicInteger();
            AtomicInteger yPos = new AtomicInteger();

            read.forEach(a -> {
                if (a.length > 0){
                    for (String val : a){
                        switch (val){
                            case "0":
                                break;
                            case "1":
                                rectangleList.add(rectangleGen(xPos.get(), yPos.get(), 1));
                                break;
                            case "2":
                                rectangleList.add(rectangleGen(xPos.get(), yPos.get(), 2));
                                break;
                            case "3":
                                rectangleGen(xPos.get(), yPos.get(), 3);
                                break;
                        }
                        xPos.set(xPos.get() + 120);
                    }
                    yPos.set(yPos.get() + 120);
                    xPos.set(0);
                }
            });
        }
        return rectangleList;
    }

    /**
     * Generates a rectangle based on the parameters
     * @param x
     * @param y
     * @param i
     * @return
     */
    private Rectangle rectangleGen(int x, int y, int i) {
        Rectangle rectangle = new Rectangle(x, y, 120, 120);

        switch (i) {
            case 1 -> rectangle.setFill(new ImagePattern(new Image(getClass().getResourceAsStream("/txt/block/blackBrick.png"))));
            case 2 -> rectangle.setFill(new ImagePattern(new Image(getClass().getResourceAsStream("/txt/block/grassDirt.png"))));
            case 3 -> rectangle.setFill(new ImagePattern(new Image(getClass().getResourceAsStream("/txt/block/wood.png"))));
        }
        pane.getChildren().add(rectangle);
        return rectangle;
    }
}
