package com.github.technicu.items;

import com.github.technicu.Technicu;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class RedstoneCrystalItem extends Item {

    public RedstoneCrystalItem(Properties properties) {
        super(properties);
    }

    @Override
    public ITextComponent getName(ItemStack itemStack) {
        if(itemStack.getItem().equals(this)){
            ItemStack item = new ItemStack(this);
            CompoundNBT nbt = item.serializeNBT();
            if(nbt.contains("charged")){
                if(nbt.getBoolean("charged")){
                    return new TranslationTextComponent("item."+Technicu.MOD_ID + ".charged_redstone_crystal");
                } else {
                    return new TranslationTextComponent("item."+Technicu.MOD_ID + ".redstone_crystal");
                }
            }
        }
            return super.getName(itemStack);
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        if(stack.getItem().equals(this)){
            return 1;
        }
        return super.getItemStackLimit(stack);
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        if(stack.getItem().equals(this)){
            return false;
        }
        return super.isDamageable(stack);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        if(playerEntity.getItemInHand(Hand.MAIN_HAND).getItem().equals(this)){
            ItemStack item = playerEntity.getItemInHand(Hand.MAIN_HAND);
            CompoundNBT nbt = item.serializeNBT();

            if(!nbt.getBoolean("charged")){
                nbt.putBoolean("charged",true);

                item.setTag(nbt);
            } else {
                nbt.putBoolean("charged",false);
                item.setTag(nbt);
            }
        }
        return super.use(world, playerEntity, hand);
    }
}
