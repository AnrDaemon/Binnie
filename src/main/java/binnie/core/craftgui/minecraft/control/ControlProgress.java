package binnie.core.craftgui.minecraft.control;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import binnie.core.craftgui.CraftGUI;
import binnie.core.craftgui.IWidget;
import binnie.core.craftgui.geometry.Position;
import binnie.core.craftgui.resource.Texture;

public class ControlProgress extends ControlProgressBase {
	private Texture progressBlank;
	private Texture progressBar;
	private Position direction;

	public ControlProgress(final IWidget parent, final int x, final int y, final Texture progressBlank, final Texture progressBar, final Position dir) {
		super(parent, x, y, (progressBlank == null) ? 0 : progressBlank.width(), (progressBlank == null) ? 0 : progressBlank.height());
		this.progressBlank = progressBlank;
		this.progressBar = progressBar;
		this.progress = 0.0f;
		this.direction = dir;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onRenderBackground(int guiWidth, int guiHeight) {
		CraftGUI.render.texture(this.progressBlank, this.getArea());
		CraftGUI.render.texturePercentage(this.progressBar, this.getArea(), this.direction, this.progress);
	}
}
