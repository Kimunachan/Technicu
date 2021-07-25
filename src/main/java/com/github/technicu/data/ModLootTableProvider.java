package com.github.technicu.data;

import com.github.technicu.setup.ModBlocks;
import com.github.technicu.setup.Registration;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.loot.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ModLootTableProvider extends LootTableProvider
{
    public ModLootTableProvider(DataGenerator generator)
    {
        super(generator);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables()
    {
        return ImmutableList.of(Pair.of(ModBlockLootTables::new, LootParameterSets.BLOCK));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker) {
        map.forEach((resourceLocation, lootTable) -> LootTableManager.validate(validationtracker, resourceLocation, lootTable));
    }

    public static class ModBlockLootTables extends BlockLootTables
    {
        @Override
        protected void addTables()
        {
            dropSelf(ModBlocks.ALLOY_SMELTER.get());
            dropSelf(ModBlocks.ALUMINUM_BLOCK.get());
            dropSelf(ModBlocks.ALUMINUM_ORE.get());
            dropSelf(ModBlocks.BRONZE_BLOCK.get());
            dropSelf(ModBlocks.COPPER_BLOCK.get());
            dropSelf(ModBlocks.COPPER_ORE.get());
            dropSelf(ModBlocks.COPPER_FENCE.get());
            dropSelf(ModBlocks.COPPER_FENCE_GATE.get());
            dropSelf(ModBlocks.ELECTRUM_BLOCK.get());
            dropSelf(ModBlocks.ENERGY_PORT.get());
            dropSelf(ModBlocks.FURNACE_GENERATOR.get());
            dropSelf(ModBlocks.MACHINE_BLOCK.get());
            dropSelf(ModBlocks.MACHINE_CONTROLLER.get());
            dropSelf(ModBlocks.METAL_PRESS.get());
            dropSelf(ModBlocks.NICKEL_BLOCK.get());
            dropSelf(ModBlocks.NICKEL_ORE.get());
            dropSelf(ModBlocks.PLATINUM_BLOCK.get());
            dropSelf(ModBlocks.PLATINUM_ORE.get());
            dropSelf(ModBlocks.STEEL_BLOCK.get());
            dropSelf(ModBlocks.STEEL_FENCE.get());
            dropSelf(ModBlocks.STEEL_FENCE_GATE.get());
            dropSelf(ModBlocks.TIN_BLOCK.get());
            dropSelf(ModBlocks.TIN_ORE.get());

            //dropOther(ModBlocks.MACHINE_BLOCK.get(), ModItems.RUBY.get());

        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return Registration.BLOCKS.getEntries().stream().map(RegistryObject::get).collect(Collectors.toList());
        }
    }
}
