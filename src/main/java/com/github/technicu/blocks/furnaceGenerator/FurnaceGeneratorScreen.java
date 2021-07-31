package com.github.technicu.blocks.furnaceGenerator;

import com.github.technicu.Technicu;
import com.github.technicu.blocks.alloySmelter.AlloySmelterContainer;
import com.github.technicu.blocks.alloySmelter.AlloySmelterTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.concurrent.atomic.AtomicInteger;

@OnlyIn(Dist.CLIENT)
public class FurnaceGeneratorScreen extends ContainerScreen<FurnaceGeneratorContainer> {

    //<editor-fold>
    private static final ResourceLocation FURNACE_GENERATOR = new ResourceLocation(Technicu.MOD_ID,"textures/gui/furnace_generator.png");
    private static final TranslationTextComponent TITLE = new TranslationTextComponent("container." + Technicu.MOD_ID + ".furnace_generator");

    private FurnaceGeneratorContainer container;
    public FurnaceGeneratorScreen(FurnaceGeneratorContainer screenContainer, PlayerInventory playerInventory, ITextComponent title) {
        super(screenContainer, playerInventory, title);
        this.container = screenContainer;

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
        this.font.draw(matrixStack,TITLE,6, 6, 0x555555);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY)
    {
        RenderSystem.color4f(1f,1f,1f,1f);

        this.minecraft.textureManager.bind(FURNACE_GENERATOR);

        int x= (this.width - this.getXSize()) / 2;
        int y = (this.height-this.getYSize()) /2;
        this.blit(matrixStack,x,y,0,0,this.getXSize(),this.getYSize());
        AtomicInteger current = new AtomicInteger();

        this.container.getCapabilityFromTE().ifPresent(iEnergyStorage -> {
            current.set(iEnergyStorage.getEnergyStored());
        });

        int pixel = current.get() != 0 ? current.get() * 50 / 25000 : 0;

        this.blit(matrixStack, getGuiLeft()+121, getGuiTop()+18+(50-pixel), 176, (50-pixel)+14, 12, 50);
    }

    @Override
    protected void renderTooltip(MatrixStack matrixStack, int mouseX, int mouseY) {
        super.renderTooltip(matrixStack, mouseX, mouseY);

        if(mouseX >= getGuiLeft()+121 && mouseX < getGuiLeft()+121+12){
            if(mouseY >= getGuiTop()+18 && mouseY < getGuiTop()+18+50){

                AtomicInteger current = new AtomicInteger();

                this.container.getCapabilityFromTE().ifPresent(iEnergyStorage -> {
                    current.set(iEnergyStorage.getEnergyStored());
                });
                this.renderTooltip(matrixStack, new StringTextComponent(current.get() + "/"+ FurnaceGeneratorTileEntity.MAX_ENERGY),mouseX,mouseY);
            }
        }


    }
    //</editor-fold>
}
