package com.github.technicu.data.client;

import com.github.technicu.Technicu;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider
{
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper)
    {
        super(generator, Technicu.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {
        withExistingParent("aluminum_block", modLoc("block/aluminum_block"));
        withExistingParent("aluminum_ore", modLoc("block/aluminum_ore"));
        withExistingParent("bronze_block", modLoc("block/bronze_block"));
        withExistingParent("copper_block", modLoc("block/copper_block"));
        withExistingParent("copper_ore", modLoc("block/copper_ore"));
        withExistingParent("electrum_block", modLoc("block/electrum_block"));
        withExistingParent("machine_block", modLoc("block/machine_block"));
        withExistingParent("machine_controller", modLoc("block/machine_controller"));
        withExistingParent("machine_energy_port", modLoc("block/machine_energy_port"));
        withExistingParent("nickel_block", modLoc("block/nickel_block"));
        withExistingParent("nickel_ore", modLoc("block/nickel_ore"));
        withExistingParent("platinum_block", modLoc("block/platinum_block"));
        withExistingParent("platinum_ore", modLoc("block/platinum_ore"));
        withExistingParent("steel_block", modLoc("block/steel_block"));
        withExistingParent("tin_block", modLoc("block/tin_block"));
        withExistingParent("tin_ore", modLoc("block/tin_ore"));

        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        builder("aluminum_ingot", itemGenerated);
        builder("bronze_ingot", itemGenerated);
        builder("copper_ingot", itemGenerated);
        builder("electrum_ingot", itemGenerated);
        builder("nickel_ingot", itemGenerated);
        builder("platinum_ingot", itemGenerated);
        builder("steel_ingot", itemGenerated);
        builder("tin_ingot", itemGenerated);

        builder("aluminum_rod", itemGenerated);
        builder("bronze_rod", itemGenerated);
        builder("copper_rod", itemGenerated);
        builder("electrum_rod", itemGenerated);
        builder("iron_rod", itemGenerated);
        builder("nickel_rod", itemGenerated);
        builder("platinum_rod", itemGenerated);
        builder("steel_rod", itemGenerated);
        builder("tin_rod", itemGenerated);
    }

    private ItemModelBuilder builder(String name, ModelFile itemGenerated)
    {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }
}
