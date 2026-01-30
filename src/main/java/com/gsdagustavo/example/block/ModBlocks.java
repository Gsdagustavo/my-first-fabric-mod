package com.gsdagustavo.example.block;

import com.gsdagustavo.example.MyFirstMod;
import com.gsdagustavo.example.items.ModItems;
import com.gsdagustavo.example.util.ResourceKeyUtils;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class ModBlocks {
  public static final Block PINK_GARNET = register(
      "pink_garnet_block", Block::new,
      BlockBehaviour.Properties.of().sound(SoundType.AMETHYST).strength(6f).speedFactor(1.2f), true
  );

  private static Block register(
      String name,
      Function<BlockBehaviour.Properties, Block> blockFactory,
      BlockBehaviour.Properties settings,
      boolean shouldRegisterItem
  ) {
    final var key = ResourceKeyUtils.keyOfBlock(name);
    final var block = blockFactory.apply(settings.setId(key));

    if (shouldRegisterItem) {
      ModItems.registerBlockItem(name, block);
    }

    return Registry.register(BuiltInRegistries.BLOCK, key, block);
  }

  public static void initialize() {
    MyFirstMod.LOGGER.info("Registering Mod Blocks for " + MyFirstMod.MOD_ID);

    ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(entries -> {
      entries.accept(PINK_GARNET.asItem());
    });
  }
}
