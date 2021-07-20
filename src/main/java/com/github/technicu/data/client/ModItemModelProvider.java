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
        withExistingParent("machine_block", modLoc("block/machine_block"));

        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        //builder("example_item", itemGenerated);
    }

    private ItemModelBuilder builder(String name, ModelFile itemGenerated)
    {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }
}
