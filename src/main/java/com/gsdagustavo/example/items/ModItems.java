package com.gsdagustavo.example.items;

import com.gsdagustavo.example.MyFirstMod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.datafix.fixes.BlockEntityFurnaceBurnTimeFix;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class ModItems {
  public static final Item PINK_GARNET = register("pink_garnet", Item::new, new Item.Properties());
  public static final Item RAW_PINK_GARNET = register("raw_pink_garnet", Item::new, new Item.Properties());

  public static <GenericItem extends Item> GenericItem register(
      String name,
      Function<Item.Properties, GenericItem> itemFactory,
      Item.Properties settings
  ) {
    ResourceKey<Item> itemKey = ResourceKey.create(
        Registries.ITEM,
        Identifier.fromNamespaceAndPath(MyFirstMod.MOD_ID, name)
    );

    GenericItem item = itemFactory.apply(settings.setId(itemKey));

    Registry.register(BuiltInRegistries.ITEM, itemKey, item);

    return item;
  }


  public static void initialize() {
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

    MyFirstMod.LOGGER.info("Registering Mod Items for " + MyFirstMod.MOD_ID);

  }
}
