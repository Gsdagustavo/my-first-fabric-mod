package com.gsdagustavo.example.items.custom;

import com.gsdagustavo.example.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;

public class ChiselItem extends Item {
  private static final Map<Block, Block> CHISEL_MAP = Map.of(
      Blocks.DIAMOND_BLOCK,
      Blocks.DIAMOND_ORE,
      Blocks.PODZOL,
      ModBlocks.PINK_GARNET,
      ModBlocks.PINK_GARNET,
      ModBlocks.RAW_PINK_GARNET,
      ModBlocks.RAW_PINK_GARNET,
      ModBlocks.PINK_GARNET
  );

  public ChiselItem(Properties properties) {
    super(properties.stacksTo(1)
                    .durability(32)
                    .rarity(Rarity.RARE)
                    .repairable(Items.IRON_BARS));
  }

  @Override
  public InteractionResult useOn(UseOnContext context) {
    var level = context.getLevel();
    if (level.isClientSide()) {
      return InteractionResult.PASS;
    }

    var player = context.getPlayer();
    if (player == null) {
      return InteractionResult.PASS;
    }

    var pos = context.getClickedPos();
    var state = level.getBlockState(pos);
    var target = CHISEL_MAP.get(state.getBlock());

    if (target == null) {
      return InteractionResult.PASS;
    }

    spawnParticles((ServerLevel) level, pos, state);
    level.setBlock(pos, target.defaultBlockState(), 3);
    context.getItemInHand().hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
    playUseSound(level, pos);

    return InteractionResult.SUCCESS;
  }

  private void playUseSound(Level level, BlockPos pos) {
    level.playSound(
        null,
        pos,
        SoundEvents.GRINDSTONE_USE,
        SoundSource.BLOCKS,
        1.0f,
        1.0f
    );
  }

  private void spawnParticles(
      ServerLevel level,
      BlockPos pos,
      BlockState state
  ) {
    final var particleCount = 75;
    final var particleSpread = 0.35f;
    final var particleSpeed = 0.0f;
    level.sendParticles(
        new BlockParticleOption(ParticleTypes.BLOCK, state),
        pos.getX() + 0.5,
        pos.getY() + 0.5,
        pos.getZ() + 0.5,
        particleCount,
        particleSpread,
        particleSpread,
        particleSpread,
        particleSpeed
    );
  }
}
