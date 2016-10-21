package binnie.genetics.item;

import binnie.Binnie;
import binnie.botany.Botany;
import binnie.core.BinnieCore;
import binnie.core.IInitializable;
import binnie.core.Mods;
import binnie.core.liquid.ItemFluidContainer;
import binnie.core.resource.BinnieIcon;
import binnie.extrabees.ExtraBees;
import binnie.extratrees.ExtraTrees;
import binnie.genetics.CreativeTabGenetics;
import binnie.genetics.Genetics;
import forestry.api.recipes.RecipeManagers;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ModuleItem implements IInitializable {
    public static BinnieIcon iconNight;
    public static BinnieIcon iconDaytime;
    public static BinnieIcon iconAllDay;
    public static BinnieIcon iconRain;
    public static BinnieIcon iconNoRain;
    public static BinnieIcon iconSky;
    public static BinnieIcon iconNoSky;
    public static BinnieIcon iconFire;
    public static BinnieIcon iconNoFire;
    public static BinnieIcon iconAdd;
    public static BinnieIcon iconArrow;
    public static BinnieIcon iconAdd0;
    public static BinnieIcon iconArrow0;
    public static BinnieIcon iconAdd1;
    public static BinnieIcon iconArrow1;

    @Override
    public void preInit() {
        Genetics.itemSerum = new ItemSerum();
        Genetics.itemSerumArray = new ItemSerumArray();
        Genetics.itemSequencer = new ItemSequence();
        Genetics.itemGenetics = Binnie.Item.registerMiscItems(GeneticsItems.values(), CreativeTabGenetics.instance);
        BinnieCore.proxy.registermodel(GameRegistry.register(Genetics.itemSerum), 0);
        BinnieCore.proxy.registermodel(GameRegistry.register(Genetics.itemSerumArray), 0);
        BinnieCore.proxy.registermodel(GameRegistry.register(Genetics.itemSequencer), 0);
        GameRegistry.register(Genetics.itemGenetics);
        for (GeneticsItems type : GeneticsItems.values()) {
            BinnieCore.proxy.registermodel(Genetics.itemGenetics, type.ordinal(), new ModelResourceLocation(Genetics.itemGenetics.getRegistryName(), "type=" + type.name().toLowerCase()));
        }

        Genetics.database = new ItemDatabase();
        Genetics.analyst = new ItemAnalyst();
        Genetics.registry = new ItemRegistry();
        Genetics.masterRegistry = new ItemMasterRegistry();
        GameRegistry.register(Genetics.database);
        GameRegistry.register(Genetics.analyst);
        GameRegistry.register(Genetics.registry);
        GameRegistry.register(Genetics.masterRegistry);
        BinnieCore.proxy.registermodel(GameRegistry.register(Genetics.database), 0, new ModelResourceLocation(Genetics.database.getRegistryName(), "type=database"));
        BinnieCore.proxy.registermodel(GameRegistry.register(Genetics.database), 1, new ModelResourceLocation(Genetics.database.getRegistryName(), "type=master_database"));
        BinnieCore.proxy.registermodel(GameRegistry.register(Genetics.analyst), 0);
        BinnieCore.proxy.registermodel(GameRegistry.register(Genetics.registry), 0);
        BinnieCore.proxy.registermodel(GameRegistry.register(Genetics.masterRegistry), 0);

        Binnie.Liquid.createLiquids(GeneticLiquid.values(), ItemFluidContainer.LiquidGenetics);
    }

    @Override
    public void init() {
        ModuleItem.iconNight = Binnie.Resource.getItemIcon(BinnieCore.instance, "gui/analyst.night");
        ModuleItem.iconDaytime = Binnie.Resource.getItemIcon(BinnieCore.instance, "gui/analyst.day");
        ModuleItem.iconAllDay = Binnie.Resource.getItemIcon(BinnieCore.instance, "gui/analyst.allday");
        ModuleItem.iconRain = Binnie.Resource.getItemIcon(BinnieCore.instance, "gui/analyst.rain");
        ModuleItem.iconNoRain = Binnie.Resource.getItemIcon(BinnieCore.instance, "gui/analyst.norain");
        ModuleItem.iconSky = Binnie.Resource.getItemIcon(BinnieCore.instance, "gui/analyst.sky");
        ModuleItem.iconNoSky = Binnie.Resource.getItemIcon(BinnieCore.instance, "gui/analyst.nosky");
        ModuleItem.iconFire = Binnie.Resource.getItemIcon(BinnieCore.instance, "gui/analyst.fire");
        ModuleItem.iconNoFire = Binnie.Resource.getItemIcon(BinnieCore.instance, "gui/analyst.nofire");
        ModuleItem.iconAdd = Binnie.Resource.getItemIcon(BinnieCore.instance, "gui/analyst.add");
        ModuleItem.iconArrow = Binnie.Resource.getItemIcon(BinnieCore.instance, "gui/analyst.arrow");
        ModuleItem.iconAdd0 = Binnie.Resource.getItemIcon(BinnieCore.instance, "gui/analyst.add.0");
        ModuleItem.iconArrow0 = Binnie.Resource.getItemIcon(BinnieCore.instance, "gui/analyst.arrow.0");
        ModuleItem.iconAdd1 = Binnie.Resource.getItemIcon(BinnieCore.instance, "gui/analyst.add.1");
        ModuleItem.iconArrow1 = Binnie.Resource.getItemIcon(BinnieCore.instance, "gui/analyst.arrow.1");
    }

    @Override
    public void postInit() {
        GameRegistry.addShapelessRecipe(GeneticsItems.DNADye.get(8), new Object[]{Items.GLOWSTONE_DUST, new ItemStack(Items.DYE, 1, 5)});
        GameRegistry.addRecipe(new ShapedOreRecipe(GeneticsItems.LaboratoryCasing.get(1), new Object[]{"iii", "iYi", "iii", 'i', "ingotIron", 'Y', Mods.Forestry.item("sturdyMachine")}));
        GameRegistry.addRecipe(new ShapelessOreRecipe(GeneticsItems.DNADye.get(2), new Object[]{"dyePurple", "dyeMagenta", "dyePink"}));
        GameRegistry.addRecipe(new ShapelessOreRecipe(GeneticsItems.FluorescentDye.get(2), new Object[]{"dyeOrange", "dyeYellow", "dustGlowstone"}));
        GameRegistry.addRecipe(new ShapelessOreRecipe(GeneticsItems.GrowthMedium.get(2), new Object[]{new ItemStack(Items.DYE, 1, 15), Items.SUGAR}));
        GameRegistry.addRecipe(new ShapedOreRecipe(GeneticsItems.EmptySequencer.get(1), new Object[]{" p ", "iGi", " p ", 'i', "ingotGold", 'G', Blocks.GLASS_PANE, 'p', Items.PAPER}));
        GameRegistry.addRecipe(new ShapedOreRecipe(GeneticsItems.EmptySerum.get(1), new Object[]{" g ", " G ", "GGG", 'g', "ingotGold", 'G', Blocks.GLASS_PANE}));
        GameRegistry.addRecipe(new ShapedOreRecipe(GeneticsItems.EmptyGenome.get(1), new Object[]{"sss", "sss", "sss", 's', GeneticsItems.EmptySerum.get(1)}));
        GameRegistry.addRecipe(new ShapedOreRecipe(GeneticsItems.Cylinder.get(8), new Object[]{" g ", "g g", "   ", 'g', Blocks.GLASS_PANE}));
        GameRegistry.addRecipe(new ShapedOreRecipe(GeneticsItems.IntegratedCircuit.get(1), new Object[]{"l g", " c ", "g l", 'c', Mods.Forestry.stack("chipsets", 1, 1), 'l', new ItemStack(Items.DYE, 1, 4), 'g', "dustGlowstone"}));
        GameRegistry.addRecipe(new ShapedOreRecipe(GeneticsItems.IntegratedCircuit.get(1), new Object[]{"g l", " c ", "l g", 'c', Mods.Forestry.stack("chipsets", 1, 1), 'l', new ItemStack(Items.DYE, 1, 4), 'g', "dustGlowstone"}));
        GameRegistry.addRecipe(new ShapedOreRecipe(GeneticsItems.IntegratedCasing.get(1), new Object[]{"ccc", "cdc", "ccc", 'c', GeneticsItems.IntegratedCircuit.get(1), 'd', GeneticsItems.LaboratoryCasing.get(1)}));
        GameRegistry.addRecipe(new ShapedOreRecipe(GeneticsItems.IntegratedCPU.get(1), new Object[]{"ccc", "cdc", "ccc", 'c', GeneticsItems.IntegratedCircuit.get(1), 'd', Items.DIAMOND}));
        RecipeManagers.carpenterManager.addRecipe(100, Binnie.Liquid.getLiquidStack("water", 2000), (ItemStack) null, new ItemStack(Genetics.database), new Object[]{"X#X", "YEY", "RDR", '#', Blocks.GLASS_PANE, 'X', Items.DIAMOND, 'Y', Items.DIAMOND, 'R', Items.REDSTONE, 'D', Items.ENDER_EYE, 'E', Blocks.OBSIDIAN});
        GameRegistry.addSmelting(Genetics.itemSequencer, GeneticsItems.EmptySequencer.get(1), 0.0f);
        GameRegistry.addSmelting(Genetics.itemSerum, GeneticsItems.EmptySerum.get(1), 0.0f);
        GameRegistry.addSmelting(Genetics.itemSerumArray, GeneticsItems.EmptyGenome.get(1), 0.0f);
        final Item[] arr$;
        final Item[] lyzers = arr$ = new Item[]{Mods.Forestry.item("beealyzer"), Mods.Forestry.item("treealyzer"), Mods.Forestry.item("flutterlyzer")};
        for (final Item a : arr$) {
            for (final Item b : lyzers) {
                for (final Item c : lyzers) {
                    if (a != b && a != c && b != c) {
                        GameRegistry.addShapedRecipe(new ItemStack(Genetics.analyst), new Object[]{" b ", "fct", " d ", 'c', GeneticsItems.IntegratedCircuit.get(1), 'b', a, 't', b, 'f', c, 'd', new ItemStack(Items.DIAMOND)});
                    }
                }
            }
        }
        final Item[] dbs = {ExtraBees.dictionary, ExtraTrees.itemDictionary, ExtraTrees.itemDictionaryLepi, Botany.database};
        if (BinnieCore.isBotanyActive() && BinnieCore.isExtraBeesActive() && BinnieCore.isExtraTreesActive()) {
            for (final Item a2 : dbs) {
                for (final Item b2 : dbs) {
                    for (final Item c2 : dbs) {
                        for (final Item d : dbs) {
                            if (a2 != b2 && a2 != c2 && a2 != d && b2 != c2 && b2 != d && c2 != d) {
                                //	GameRegistry.addShapedRecipe(new ItemStack(Genetics.registry), new Object[] { " b ", "fct", " l ", 'c', GeneticsItems.IntegratedCircuit.get(1), 'b', a2, 't', b2, 'f', c2, 'l', d });
                            }
                        }
                    }
                }
            }
        }
    }
}