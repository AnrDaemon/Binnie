package binnie.extrabees.products;

import binnie.core.BinnieCore;
import binnie.core.IInitializable;
import binnie.core.Mods;
import binnie.core.item.IColoredItem;
import binnie.extrabees.ExtraBees;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ModuleProducts implements IInitializable {
    @Override
    public void preInit() {
        ExtraBees.honeyCrystal = new ItemHoneyCrystal("honeyCrystal");
        ExtraBees.honeyCrystalEmpty = new ItemHoneyCrystalEmpty("honeyCrystalEmpty");
        ExtraBees.honeyDrop = new ItemHoneyDrop();
        ExtraBees.comb = new ItemHoneyComb();
        ExtraBees.propolis = new ItemPropolis();

        GameRegistry.register(ExtraBees.honeyCrystal);
        GameRegistry.register(ExtraBees.honeyCrystalEmpty);
        GameRegistry.register(ExtraBees.comb);


        ExtraBees.proxy.registermodel(ExtraBees.honeyCrystal, 0);
        ExtraBees.proxy.registermodel(ExtraBees.honeyCrystalEmpty, 0);
        for (EnumHoneyComb c : EnumHoneyComb.values()) {
            if (c.isActive())
                ExtraBees.proxy.registermodel(ExtraBees.comb, c.ordinal());
        }


        OreDictionary.registerOre("ingotIron", Items.IRON_INGOT);
        OreDictionary.registerOre("ingotGold", Items.GOLD_INGOT);
        OreDictionary.registerOre("gemDiamond", Items.DIAMOND);
    }

    @Override
    public void init() {
        ItemHoneyComb.addSubtypes();
        BinnieCore.proxy.registerItemColors((IColoredItem) ExtraBees.comb);
    }

    @Override
    public void postInit() {
        GameRegistry.addRecipe(new ItemStack(ExtraBees.honeyCrystalEmpty), new Object[]{"#@#", "@#@", "#@#", '@', Mods.Forestry.stack("honeyDrop"), '#', EnumHoneyDrop.ENERGY.get(1)});
        for (final EnumHoneyComb info : EnumHoneyComb.values()) {
            info.addRecipe();
        }
        for (final EnumHoneyDrop info2 : EnumHoneyDrop.values()) {
            info2.addRecipe();
        }
        for (final EnumPropolis info3 : EnumPropolis.values()) {
            info3.addRecipe();
        }
    }
}