package maxhyper.dynamictreesttf.trees.species;

import com.ferreusveritas.dynamictrees.ModConstants;
import com.ferreusveritas.dynamictrees.items.Seed;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.trees.TreeFamily;
import maxhyper.dynamictreesttf.DynamicTreesTTF;
import maxhyper.dynamictreesttf.ModContent;
import maxhyper.dynamictreesttf.genfeatures.FeatureGenWeb;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import twilightforest.biomes.TFBiomes;

public class SpeciesOakSpooky extends Species {

    public SpeciesOakSpooky(TreeFamily treeFamily) {
        super(new ResourceLocation(DynamicTreesTTF.MODID, treeFamily.getName().getResourcePath() + "spooky"), treeFamily);

        setBasicGrowingParameters(tapering, signalEnergy, upProbability, lowestBranchHeight, growthRate);

        envFactor(BiomeDictionary.Type.COLD, 0.75f);
        envFactor(BiomeDictionary.Type.HOT, 0.50f);
        envFactor(BiomeDictionary.Type.DRY, 0.50f);
        envFactor(BiomeDictionary.Type.FOREST, 1.05f);

        setupStandardSeedDropping();

        addGenFeature(new FeatureGenWeb(this, 0.75f));

        getFamily().addSpeciesLocationOverride((access, trunkPos) -> {
            if(Species.isOneOfBiomes(access.getBiome(trunkPos), TFBiomes.spookyForest)) {
                return this;
            }
            return Species.NULLSPECIES;
        });
    }

    @Override
    public ResourceLocation getSaplingName() {
        return new ResourceLocation(ModConstants.MODID, "oak");
    }

    @Override
    public boolean isBiomePerfect(Biome biome) {
        return biome == TFBiomes.spookyForest;
    }

    @Override
    public ItemStack getSeedStack(int qty) {
        return getFamily().getCommonSpecies().getSeedStack(qty);
    }

    @Override
    public Seed getSeed() {
        return getFamily().getCommonSpecies().getSeed();
    }

}