package com.gsdagustavo.example.items;

import com.gsdagustavo.example.MyFirstMod;
import com.gsdagustavo.example.items.custom.ChiselItem;
import com.gsdagustavo.example.util.ResourceKeyUtils;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class ModItems {
  public static final Item PINK_GARNET = register("pink_garnet", Item::new, new Item.Properties());
  public static final Item RAW_PINK_GARNET = register("raw_pink_garnet", Item::new, new Item.Properties());
  public static final ChiselItem CHISEL = register("chisel", ChiselItem::new, new Item.Properties());

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

  public static void initialize() {
    MyFirstMod.LOGGER.info("Registering Mod Items for " + MyFirstMod.MOD_ID);

    ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(entries -> {
      entries.accept(PINK_GARNET);
      entries.accept(RAW_PINK_GARNET);
    });

    ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> {
      entries.accept(CHISEL);
    });
  }
}
