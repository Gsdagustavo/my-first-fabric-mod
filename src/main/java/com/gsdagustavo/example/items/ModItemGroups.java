package com.gsdagustavo.example.items;

import com.gsdagustavo.example.MyFirstMod;
import com.gsdagustavo.example.block.ModBlocks;
import com.gsdagustavo.example.util.ResourceKeyUtils;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModItemGroups {
  public static final ResourceKey<CreativeModeTab> PINK_GARNET_ITEMS_CREATIVE_TAB_KEY = ResourceKeyUtils.keyOfTab(
      "pink_garnet_items_creative_tab");

  public static final ResourceKey<CreativeModeTab> PINK_GARNET_BLOCKS_CREATIVE_TAB_KEY = ResourceKeyUtils.keyOfTab(
      "pink_garnet_blocks_creative_tab"
  );
  public static final CreativeModeTab PINK_GARNET_ITEMS_CREATIVE_TAB;
  public static final CreativeModeTab PINK_GARNET_BLOCKS_CREATIVE_TAB;

  static {
    PINK_GARNET_ITEMS_CREATIVE_TAB = FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.PINK_GARNET)).title(
        Component.translatable("itemGroup.my-first-mod.pink_garnet_items")).displayItems((itemDisplayParameters, output) -> {
      output.accept(ModItems.PINK_GARNET);
      output.accept(ModItems.RAW_PINK_GARNET);
    }).build();
  }

  static {
    PINK_GARNET_BLOCKS_CREATIVE_TAB = FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.PINK_GARNET)).title(
        Component.translatable("itemGroup.my-first-mod.pink_garnet_blocks")).displayItems((itemDisplayParameters, output) -> {
      output.accept(ModBlocks.PINK_GARNET);
      output.accept(ModBlocks.RAW_PINK_GARNET);
      output.accept(ModBlocks.PINK_GARNET_ORE);
      output.accept(ModBlocks.DEEPSLATE_PINK_GARNET_ORE);
    }).build();
  }

  public static void initialize() {
    MyFirstMod.LOGGER.info("Registering Mod Item Groups for " + MyFirstMod.MOD_ID);
    Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, PINK_GARNET_ITEMS_CREATIVE_TAB_KEY, PINK_GARNET_ITEMS_CREATIVE_TAB);
    Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, PINK_GARNET_BLOCKS_CREATIVE_TAB_KEY, PINK_GARNET_BLOCKS_CREATIVE_TAB);
  }
}
