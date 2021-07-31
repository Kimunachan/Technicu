package com.github.technicu.blocks.metalPress;

import com.github.technicu.Technicu;
import com.github.technicu.capabilities.ModEnergyHandler;
import com.github.technicu.recipes.pressing.ModPressingRecipe;
import com.github.technicu.setup.ModRecipes;
import com.github.technicu.setup.ModTileEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class MetalPressTileEntity extends LockableLootTileEntity implements ITickableTileEntity, INamedContainerProvider
{
    private int progress = 0;
    public static final int MAX_ENERGY = 25000;

    private IInventory inventory;

    public MetalPressTileEntity() {
        super(ModTileEntityTypes.METAL_PRESS.get());



        this.inventory = this;
    }

    LazyOptional<IEnergyStorage> energyStorageLazyOptional = LazyOptional.of(()-> new ModEnergyHandler(MAX_ENERGY,0,0,10000));

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityEnergy.ENERGY){
            return energyStorageLazyOptional.cast();
        }

        return super.getCapability(cap, side);
    }


    @Override
    protected void invalidateCaps() {
        super.invalidateCaps();
        energyStorageLazyOptional.invalidate();
    }

    private final IIntArray fields = new IIntArray()
    {
        @Override
        public int get(int index)
        {
            switch (index)
            {
                case 0:
                    return progress;
                default:
                    return 0;
            }
        }

        @Override
        public void set(int index, int value)
        {
            switch (index)
            {
                case 0:
                    progress = value;
                    break;
            }
        }

        @Override
        public int getCount()
        {
            return 1;
        }
    };

    void encodeExtraData(PacketBuffer data)
    {
        data.writeByte(fields.getCount());
    }

    //<editor-fold desc="InventoryStuff">
    public static int slots = 2;
    public static final int WORK_TIME = 20 * 2;

    protected NonNullList<ItemStack> items = NonNullList.withSize(slots,ItemStack.EMPTY);

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> itemsIn) {
        this.items = itemsIn;
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container."+ Technicu.MOD_ID + ".metal_press");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory playerInventory) {
        return new MetalPressContainer(id,playerInventory,this);
    }

    @Override
    public int getContainerSize() {
        return slots;
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        super.load(state, nbt);
        this.items = NonNullList.withSize(getContainerSize(),ItemStack.EMPTY);
        if(!this.tryLoadLootTable(nbt)){
            ItemStackHelper.loadAllItems(nbt,this.items);
        }

    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        super.save(nbt);
        if(!this.trySaveLootTable(nbt)){
            ItemStackHelper.saveAllItems(nbt,this.items);
        }

        return nbt;
    }

    @Nullable
    public ModPressingRecipe getRecipe(ItemStack item) {
        if (level == null || getItem(0).isEmpty()) {
            return null;
        }

        Set<IRecipe<?>> recipes = findRecipesByType(ModRecipes.PRESSING_TYPE, this.level);

        for (IRecipe<?> iRecipe : recipes)
        {
            ModPressingRecipe recipe = (ModPressingRecipe) iRecipe;

            if (recipe.matches(new RecipeWrapper((IItemHandlerModifiable) this.inventory), this.level))
            {
                return recipe;
            }
        }

        return null;
    }

    public static Set<IRecipe<?>> findRecipesByType(IRecipeType<ModPressingRecipe> typeIn, World world)
    {
        return world != null ? world.getRecipeManager().getRecipes().stream().filter(recipe -> recipe.getType() == typeIn).collect(Collectors.toSet()) : Collections.EMPTY_SET;
    }

    @OnlyIn(Dist.CLIENT)
    public static Set<IRecipe<?>> findRecipesByType(IRecipeType<ModPressingRecipe> typeIn)
    {
        ClientWorld world = Minecraft.getInstance().level;

        return world != null ? world.getRecipeManager().getRecipes().stream().filter(recipe -> recipe.getType() == typeIn).collect(Collectors.toSet()) : Collections.EMPTY_SET;
    }

    public static Set<ItemStack> getAllRecipeInputs(IRecipeType<ModPressingRecipe> typeIn, World world)
    {
        Set<ItemStack> inputs = new HashSet<>();
        Set<IRecipe<?>> recipes = findRecipesByType(typeIn, world);

        for (IRecipe<?> recipe : recipes)
        {
            NonNullList<Ingredient> ingredients = recipe.getIngredients();

            ingredients.forEach(ingredient ->
            {
                for (ItemStack stack : ingredient.getItems())
                {
                    inputs.add(stack);
                }
            });
        }

        return inputs;
    }

    @Override
    public void tick()
    {
        if (level != null && level.isClientSide())
        {
            if (this.getRecipe(inventory.getItem(0)) != null)
            {
                if (progress != WORK_TIME)
                {
                    progress++;
                }
                else
                {
                    progress = 0;

                    ItemStack output = getRecipe(this.inventory.getItem(0)).getResultItem();

                    this.inventory.setItem(1, output.copy());
                    this.inventory.removeItem(0, 1);
                }
            }
        }
    }
    //</editor-fold>
}
