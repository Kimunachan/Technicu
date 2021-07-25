package com.github.technicu.blocks.energyPort;

import com.github.technicu.Technicu;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.concurrent.atomic.AtomicInteger;

@OnlyIn(Dist.CLIENT)
public class EnergyPortScreen extends ContainerScreen<EnergyPortContainer>
{
    //<editor-fold>
    private static final ResourceLocation ENERGY_PORT = new ResourceLocation(Technicu.MOD_ID,"textures/gui/energy_port.png");
    private static final TranslationTextComponent TITLE = new TranslationTextComponent("container." + Technicu.MOD_ID + ".energy_port");

    EnergyPortContainer container;

    public EnergyPortScreen(EnergyPortContainer screenContainer, PlayerInventory playerInventory, ITextComponent title) {
        super(screenContainer, playerInventory, title);

        this.container = screenContainer;
        this.leftPos=0;
        this.topPos=0;
        this.width=175;
        this.height=86;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack,mouseX,mouseY,partialTicks);
        this.renderTooltip(matrixStack,mouseX,mouseY);
    }

    @Override
    protected void renderLabels(MatrixStack matrixStack, int x, int y) {
        this.font.draw(matrixStack,TITLE,6, 6, 0x555555);
        RenderSystem.scalef(.89F,.89F,.89F);

        AtomicInteger current = new AtomicInteger();

        this.container.getCapabilityFromTE().ifPresent(iEnergyStorage -> {
            current.set(iEnergyStorage.getEnergyStored());
        });

        this.font.draw(matrixStack,current + "/" + EnergyPortTileEntity.MAX_ENERGY, 68/.89F, 39/.89F, 0x575757);
        RenderSystem.scalef(1.11F,1.11F,1.11F);


    }

    @SuppressWarnings("deprecation")
    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY)
    {
        RenderSystem.color4f(1f,1f,1f,1f);

        this.minecraft.textureManager.bind(ENERGY_PORT);

        int x = (width - this.getXSize()) / 2;
        int y = (height - this.getYSize()) / 2;
        this.blit(matrixStack, x, y, 0,0,this.getXSize(),this.getYSize());

        AtomicInteger current = new AtomicInteger();

        this.container.getCapabilityFromTE().ifPresent(iEnergyStorage -> {
            current.set(iEnergyStorage.getEnergyStored());
        });

        int pixel = current.get() != 0 ? current.get() * 49 / 25000 : 0;

        this.blit(matrixStack, getGuiLeft()+44, getGuiTop()+18+(50-pixel), 176, 50-pixel, 12, 49);
    }
    //</editor-fold>
}
