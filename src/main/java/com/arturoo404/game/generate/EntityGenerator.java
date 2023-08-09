package com.arturoo404.game.generate;
import com.arturoo404.game.entity.EntityController;
import com.arturoo404.game.entity.EntityType;
import com.arturoo404.game.entity.LivingEntities;
import com.arturoo404.game.file.EntityMainModel;
import com.arturoo404.game.file.EntityModel;
import com.arturoo404.game.file.FileReader;
import com.arturoo404.game.player.Player;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;

import java.io.IOException;


@Getter
public class EntityGenerator {
    private final AnchorPane pane;
    private final LivingEntities livingEntities;
    private final Player player;

    private EntityController entityController;

    public EntityGenerator(AnchorPane pane, Player player) {
        this.pane = pane;
        this.player = player;
        livingEntities = new LivingEntities();
        entityController = new EntityController(livingEntities, player);
    }


    public void init() throws IOException {
        final EntityMainModel entityModelFile = FileReader.entityModelFilesRead();
        createList(entityModelFile);
    }

    private void createList(EntityMainModel entityModelFile){
        GenerateEntityModel entityGenerator  = new GenerateEntityModel(pane);
        for (EntityModel model : entityModelFile.entityModel()) {
            if (model.entityType().equals(EntityType.WOLF)){
                livingEntities.setWolves(entityGenerator.generateWolfModel(model, this.livingEntities, entityModelFile.difficulty()));
            }
        }

        entityController.init();
    }

}
