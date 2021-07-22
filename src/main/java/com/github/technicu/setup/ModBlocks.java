package com.github.technicu.setup;

import com.github.technicu.blocks.alloySmelter.AlloySmelterBlock;
import com.github.technicu.blocks.energyPort.EnergyPortBlock;
import com.github.technicu.blocks.furnaceGenerator.FurnaceGeneratorBlock;
import com.github.technicu.blocks.machineController.MachineControllerBlock;
import com.github.technicu.blocks.metalPress.MetalPressBlock;
import com.github.technicu.blocks.steelFence.SteelFenceBlock;
import com.github.technicu.blocks.steelFence.SteelFenceGateBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks extends Blocks {
    public static final RegistryObject<AlloySmelterBlock> ALLOY_SMELTER = register("alloy_smelter", () -> new AlloySmelterBlock(AbstractBlock.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3, 10).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
    public static final RegistryObject<Block> ALUMINUM_BLOCK = register("aluminum_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3, 10).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
    public static final RegistryObject<Block> ALUMINUM_ORE = register("aluminum_ore", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3, 10).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
    public static final RegistryObject<Block> BRONZE_BLOCK = register("bronze_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3, 10).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
    public static final RegistryObject<Block> COPPER_BLOCK = register("copper_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3, 10).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
    public static final RegistryObject<Block> COPPER_ORE = register("copper_ore", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3, 10).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
    public static final RegistryObject<Block> ELECTRUM_BLOCK = register("electrum_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3, 10).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
    public static final RegistryObject<EnergyPortBlock> ENERGY_PORT = register("energy_port", () -> new EnergyPortBlock(AbstractBlock.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3, 10).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
    public static final RegistryObject<FurnaceGeneratorBlock> FURNACE_GENERATOR = register("furnace_generator", () -> new FurnaceGeneratorBlock(AbstractBlock.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3, 10).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
    public static final RegistryObject<Block> MACHINE_BLOCK = register("machine_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3, 10).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
    public static final RegistryObject<MachineControllerBlock> MACHINE_CONTROLLER = register("machine_controller", () -> new MachineControllerBlock(AbstractBlock.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3, 10).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
    public static final RegistryObject<MetalPressBlock> METAL_PRESS = register("metal_press", () -> new MetalPressBlock(AbstractBlock.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3, 10).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
    public static final RegistryObject<Block> NICKEL_BLOCK = register("nickel_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3, 10).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
    public static final RegistryObject<Block> NICKEL_ORE = register("nickel_ore", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3, 10).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
    public static final RegistryObject<Block> PLATINUM_BLOCK = register("platinum_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3, 10).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
    public static final RegistryObject<Block> PLATINUM_ORE = register("platinum_ore", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3, 10).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
    public static final RegistryObject<Block> STEEL_BLOCK = register("steel_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3, 10).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
    public static final RegistryObject<SteelFenceBlock> STEEL_FENCE = register("steel_fence", () -> new SteelFenceBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3, 10).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
    public static final RegistryObject<SteelFenceGateBlock> STEEL_FENCE_GATE = register("steel_fence_gate", () -> new SteelFenceGateBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3, 10).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
    public static final RegistryObject<Block> TIN_BLOCK = register("tin_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3, 10).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
    public static final RegistryObject<Block> TIN_ORE = register("tin_ore", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3, 10).harvestTool(ToolType.PICKAXE).harvestLevel(2)));

    static void register() {
    }

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block) {
        return Registration.BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
        RegistryObject<T> ret = registerNoItem(name, block);
        Registration.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties().tab(ModTabs.MOD_TAB_BLOCKS)));

        return ret;
    }
}
