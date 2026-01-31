package com.gsdagustavo.example.block;

import com.gsdagustavo.example.MyFirstMod;
import com.gsdagustavo.example.util.ResourceKeyUtils;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class ModBlocks {
  public static final Block PINK_GARNET = register(
      "pink_garnet_block", Block::new,
      BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).sound(SoundType.AMETHYST), true
  );

  public static final Block RAW_PINK_GARNET = register(
      "raw_pink_garnet_block", Block::new,
      BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).sound(SoundType.AMETHYST), true
  );

  public static final Block PINK_GARNET_ORE = register(
      "pink_garnet_ore", Block::new,
      BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE).sound(SoundType.AMETHYST), true
  );

  public static final Block DEEPSLATE_PINK_GARNET_ORE = register(
      "deepslate_pink_garnet_ore", Block::new,
      BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE).sound(SoundType.AMETHYST), true
  );

  private static Block register(
      String name,
      Function<BlockBehaviour.Properties, Block> blockFactory,
      BlockBehaviour.Properties settings,
      boolean shouldRegisterItem
  ) {
    final var blockKey = ResourceKeyUtils.keyOfBlock(name);
    final var block = blockFactory.apply(settings.setId(blockKey));

    if (shouldRegisterItem) {
      final var itemKey = ResourceKeyUtils.keyOfItem(name);
      final var blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
      Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
    }

    return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
  }

  public static void initialize() {
    MyFirstMod.LOGGER.info("Registering Mod Blocks for " + MyFirstMod.MOD_ID);

    ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(entries -> {
      entries.accept(PINK_GARNET.asItem());
      entries.accept(RAW_PINK_GARNET.asItem());
      entries.accept(PINK_GARNET_ORE.asItem());
      entries.accept(DEEPSLATE_PINK_GARNET_ORE.asItem());
    });
  }
}
