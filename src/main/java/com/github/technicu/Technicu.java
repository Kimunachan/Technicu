package com.github.technicu;

import com.github.technicu.setup.Registration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod("technicu")
public class Technicu
{
    public static final String MOD_ID = "technicu";

    public Technicu() {
        Registration.register();

        MinecraftForge.EVENT_BUS.register(this);
    }
}
