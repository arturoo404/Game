package com.arturoo404.game.generate;

import com.arturoo404.game.entity.EntityType;
import com.arturoo404.game.entity.wolf.Wolf;
import com.arturoo404.game.file.EntityMainModel;
import com.arturoo404.game.file.EntityModel;
import com.arturoo404.game.file.FileReader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EntityGenerator {
    private final AnchorPane pane;
    private  List<Wolf> wolfList;

    public EntityGenerator(AnchorPane pane) {
        this.pane = pane;
        wolfList = new ArrayList<>();
    }


    public void init() throws IOException {
        FileReader fileReader = new FileReader();
        final EntityMainModel entityModelFile = fileReader.entityModelFilesRead();
        createList(entityModelFile);
    }

    private void createList(EntityMainModel entityModelFile){
        GenerateEntityModel entityGenerator  = new GenerateEntityModel(pane);
        for (EntityModel model : entityModelFile.entityModel()) {
            if (model.entityType().equals(EntityType.WOLF)){
                wolfList.addAll(entityGenerator.generateWolfModel(model));
            }
        }
    }

}
