package teamHTBP.vida.helper.guidebookHelper.components;

import com.mojang.blaze3d.vertex.PoseStack;

public class CraftingGuidebookComponent implements IGuidebookComponent{
    public int x = 0;
    public int y = 0;

    @Override
    public String getType() {
        return "craft";
    }

    @Override
    public int x() {
        return x;
    }

    @Override
    public int y() {
        return y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {

    }
}
