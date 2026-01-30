package com.gsdagustavo.example.util;

import com.gsdagustavo.example.MyFirstMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ResourceKeyUtils {
  public static ResourceKey<Block> keyOfBlock(String name) {
    return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MyFirstMod.MOD_ID, name));
  }

  public static ResourceKey<Item> keyOfItem(String name) {
    return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MyFirstMod.MOD_ID, name));
  }
}