package com.gsdagustavo.example.items;

import com.gsdagustavo.example.MyFirstMod;
import com.gsdagustavo.example.util.ResourceKeyUtils;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Function;

public class ModItems {
  public static final Item PINK_GARNET = register("pink_garnet", Item::new, new Item.Properties());
  public static final Item RAW_PINK_GARNET = register("raw_pink_garnet", Item::new, new Item.Properties());

  private static <GenericItem extends Item> GenericItem register(
      String name,
      Function<Item.Properties, GenericItem> itemFactory,
      Item.Properties settings
  ) {
    final var key = ResourceKeyUtils.keyOfItem(name);

    GenericItem item = itemFactory.apply(settings.setId(key));

    Registry.register(BuiltInRegistries.ITEM, key, item);

    return item;
  }

  public static Item registerBlockItem(String name, Block block) {
    final var key = ResourceKeyUtils.keyOfItem(name);
    final var blockItem = new BlockItem(block, new Item.Properties().setId(key).useBlockDescriptionPrefix());
    return Registry.register(BuiltInRegistries.ITEM, key, blockItem);
  }

  public static void initialize() {
    MyFirstMod.LOGGER.info("Registering Mod Items for " + MyFirstMod.MOD_ID);

    // Add all items to creative mode tab
    ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(
        entries -> {
          entries.accept(ModItems.PINK_GARNET);
          entries.accept(ModItems.RAW_PINK_GARNET);
        });

    // Make the item compostable
    CompostingChanceRegistry.INSTANCE.add(ModItems.PINK_GARNET, 0.3F);

    // Make the item a burnable fuel
    FuelRegistryEvents.BUILD.register((builder, context) -> builder.add(ModItems.PINK_GARNET, 5 * 20));
  }
}
