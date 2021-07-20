package com.github.technicu.blocks.machineController;

import com.github.technicu.Technicu;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MachineControllerBasicScreen extends ContainerScreen<MachineControllerBasicContainer> {


    private static final ResourceLocation MACHINE_CONTROLLER_BASIC_GUI = new ResourceLocation(Technicu.MOD_ID,"textures/gui/multiblock_basic_gui.png");

    public MachineControllerBasicScreen(MachineControllerBasicContainer screenContainer, PlayerInventory playerInventory, ITextComponent title) {
        super(screenContainer, playerInventory, title);

        this.leftPos=0;
        this.topPos=0;
        this.width=175;
        this.height=201;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack,mouseX,mouseY,partialTicks);
        this.renderTooltip(matrixStack,mouseX,mouseY);
    }

    @Override
    protected void renderLabels(MatrixStack matrixStack, int x, int y) {
        this.font.draw(matrixStack,this.inventory.getDisplayName(),this.inventoryLabelX,this.inventoryLabelY,4210752);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1f,1f,1f,1f);
        this.minecraft.textureManager.bind(MACHINE_CONTROLLER_BASIC_GUI);

        int x= (this.width - this.getXSize()) / 2;
        int y = (this.height-this.getYSize()) /2;
        this.blit(matrixStack,x,y,0,0,this.getXSize(),this.getYSize());
    }
}
