package binnie.extratrees.block.decor;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

import net.minecraftforge.common.ForgeHooks;

import binnie.extratrees.block.IPlankType;
import binnie.extratrees.block.WoodManager;

public class MultiFenceRecipeSize implements IRecipe {
	private ItemStack cached = ItemStack.EMPTY;

	public MultiFenceRecipeSize() {
	}

	@Override
	public boolean matches(final InventoryCrafting inv, final World world) {
		if(inv.getSizeInventory()!=9) return false;
		StringBuilder recipePattern = new StringBuilder();
		List<IPlankType> types = new ArrayList<>();
		for (int i = 0; i < inv.getSizeInventory(); ++i) {
			ItemStack stack = inv.getStackInSlot(i);
			boolean isEmpty = stack.isEmpty();
			IPlankType type;
			if (isEmpty) {
				type = null;
			} else {
				type = WoodManager.getPlankType(stack);
			}
			if (!isEmpty && type == null) {
				return false;
			}
			if (isEmpty) {
				recipePattern.append(" ");
			} else {
				if (!types.contains(type)) {
					types.add(type);
					if (types.size() > 2) {
						return false;
					}
				}
				recipePattern.append(types.indexOf(type));
			}
		}
		if (types.isEmpty()) {
			return false;
		}
		for (MultiFenceRecipePattern pattern : MultiFenceRecipePattern.VALUES) {
			if (pattern.matches(recipePattern.toString()) && types.size()>0 && pattern.getTypeCount()<=types.size()) {
				cached = pattern.createFence(types.get(0), types.get(pattern.getTypeCount() - 1));
				return true;
			}
		}
		this.cached = ItemStack.EMPTY;
		return false;
	}

	@Override
	public ItemStack getCraftingResult(final InventoryCrafting inv) {
		return this.getRecipeOutput();
	}

	@Override
	public int getRecipeSize() {
		return 3;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return (this.cached.isEmpty()) ? new ItemStack(Blocks.OAK_FENCE) : this.cached;
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
		return ForgeHooks.defaultRecipeGetRemainingItems(inv);
	}
}
