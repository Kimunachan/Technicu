package com.github.technicu.setup;

import com.github.technicu.Technicu;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class ModItems
{
    //public static final RegistryObject<PeridotSword> PERIDOT_SWORD = Registration.ITEMS.register("peridot_sword", () -> new PeridotSword(ModItemTier.PERIDOT, new Item.Properties().tab(Arcadia.MOD_TAB_TOOLS)));

    public static final RegistryObject<Item> ALUMINUM_INGOT = Registration.ITEMS.register("aluminum_ingot", () -> new Item(new Item.Properties().tab(ModTabs.MOD_TAB_ITEMS)));
    public static final RegistryObject<Item> BRONZE_INGOT = Registration.ITEMS.register("bronze_ingot", () -> new Item(new Item.Properties().tab(ModTabs.MOD_TAB_ITEMS)));
    public static final RegistryObject<Item> COPPER_INGOT = Registration.ITEMS.register("copper_ingot", () -> new Item(new Item.Properties().tab(ModTabs.MOD_TAB_ITEMS)));
    public static final RegistryObject<Item> ELECTRUM_INGOT = Registration.ITEMS.register("electrum_ingot", () -> new Item(new Item.Properties().tab(ModTabs.MOD_TAB_ITEMS)));
    public static final RegistryObject<Item> NICKEL_INGOT = Registration.ITEMS.register("nickel_ingot", () -> new Item(new Item.Properties().tab(ModTabs.MOD_TAB_ITEMS)));
    public static final RegistryObject<Item> PLATINUM_INGOT = Registration.ITEMS.register("platinum_ingot", () -> new Item(new Item.Properties().tab(ModTabs.MOD_TAB_ITEMS)));
    public static final RegistryObject<Item> STEEL_INGOT = Registration.ITEMS.register("steel_ingot", () -> new Item(new Item.Properties().tab(ModTabs.MOD_TAB_ITEMS)));
    public static final RegistryObject<Item> TIN_INGOT = Registration.ITEMS.register("tin_ingot", () -> new Item(new Item.Properties().tab(ModTabs.MOD_TAB_ITEMS)));

    public static final RegistryObject<Item> ALUMINIUM_ROD = Registration.ITEMS.register("aluminum_rod", () -> new Item(new Item.Properties().tab(ModTabs.MOD_TAB_ITEMS)));
    public static final RegistryObject<Item> BRONZE_ROD = Registration.ITEMS.register("bronze_rod", () -> new Item(new Item.Properties().tab(ModTabs.MOD_TAB_ITEMS)));
    public static final RegistryObject<Item> COPPER_ROD = Registration.ITEMS.register("copper_rod", () -> new Item(new Item.Properties().tab(ModTabs.MOD_TAB_ITEMS)));
    public static final RegistryObject<Item> ELECTRUM_ROD = Registration.ITEMS.register("electrum_rod", () -> new Item(new Item.Properties().tab(ModTabs.MOD_TAB_ITEMS)));
    public static final RegistryObject<Item> IRON_ROD = Registration.ITEMS.register("iron_rod", () -> new Item(new Item.Properties().tab(ModTabs.MOD_TAB_ITEMS)));
    public static final RegistryObject<Item> NICKEL_ROD = Registration.ITEMS.register("nickel_rod", () -> new Item(new Item.Properties().tab(ModTabs.MOD_TAB_ITEMS)));
    public static final RegistryObject<Item> PLATINUM_ROD = Registration.ITEMS.register("platinum_rod", () -> new Item(new Item.Properties().tab(ModTabs.MOD_TAB_ITEMS)));
    public static final RegistryObject<Item> STEEL_ROD = Registration.ITEMS.register("steel_rod", () -> new Item(new Item.Properties().tab(ModTabs.MOD_TAB_ITEMS)));
    public static final RegistryObject<Item> TIN_ROD = Registration.ITEMS.register("tin_rod", () -> new Item(new Item.Properties().tab(ModTabs.MOD_TAB_ITEMS)));

    static void register()
    {

    }
}
