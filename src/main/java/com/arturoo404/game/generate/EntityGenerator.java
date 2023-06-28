package com.arturoo404.game.generate;

import com.arturoo404.game.file.EntityModelFile;
import com.arturoo404.game.file.FileReader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class EntityGenerator {
    private final AnchorPane pane;

    public EntityGenerator(AnchorPane pane) {
        this.pane = pane;
    }


    public void init() throws IOException {
        FileReader fileReader = new FileReader();
        final EntityModelFile entityModelFile = fileReader.entityModelFilesRead();
    }
}
