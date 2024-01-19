package net.liopyu.entityjs.item;

import dev.latvian.mods.kubejs.generator.AssetJsonGenerator;
import dev.latvian.mods.kubejs.item.ItemBuilder;
import dev.latvian.mods.kubejs.util.UtilsJS;
import net.liopyu.entityjs.builders.ArrowEntityBuilder;
import net.liopyu.entityjs.builders.ArrowEntityJSBuilder;
import net.liopyu.entityjs.entities.ArrowEntityJS;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;


public class ArrowItemBuilder extends ItemBuilder {
    public transient final ArrowEntityBuilder<?> parent;

    public transient boolean canBePickedUp;

    public ArrowItemBuilder(ResourceLocation i, ArrowEntityBuilder<?> parent) {
        super(i);
        this.parent = parent;
        canBePickedUp = true;
    }

    public ArrowItemBuilder canBePickedup(boolean canBePickedUp) {
        this.canBePickedUp = canBePickedUp;
        return this;
    }

    public transient ArrowEntityJSBuilder builder;

    @Override
    public Item createObject() {
        return new ArrowItem(createItemProperties()) {
            @Override
            public ArrowEntityJS createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter) {
                ArrowEntityJS arrow = new ArrowEntityJS(pLevel, pShooter, builder);
                arrow.setPickUpItem(canBePickedUp ? pStack : ItemStack.EMPTY);
                return arrow;
            }
        };
    }


    @Override
    public void generateAssetJsons(AssetJsonGenerator generator) {
        if (modelJson != null) {
            generator.json(AssetJsonGenerator.asItemModelLocation(id), modelJson);
            return;
        }

        generator.itemModel(id, m -> {
            m.parent(id.getPath());

            if (!parentModel.isEmpty()) {
                m.parent(parentModel);

                if (textureJson.size() == 0) {
                    texture(newID("item/", "").toString());
                }
                m.textures(textureJson);
            } else {
                m.parent("item/generated");

                if (textureJson.size() != 0) {
                    m.textures(textureJson);
                }
            }
        });
    }
}
