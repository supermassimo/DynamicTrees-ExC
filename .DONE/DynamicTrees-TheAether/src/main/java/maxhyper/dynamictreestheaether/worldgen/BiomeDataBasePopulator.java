package maxhyper.dynamictreestheaether.worldgen;

import com.ferreusveritas.dynamictrees.api.TreeRegistry;
import com.ferreusveritas.dynamictrees.api.worldgen.BiomePropertySelectors;
import com.ferreusveritas.dynamictrees.api.worldgen.IBiomeDataBasePopulator;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.worldgen.BiomeDataBase;

import com.ferreusveritas.dynamictrees.worldgen.BiomeDataBasePopulatorJson;
import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.world.AetherWorld;
import maxhyper.dynamictreestheaether.DynamicTreesTheAether;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;

public class BiomeDataBasePopulator implements IBiomeDataBasePopulator {

    public static final String RESOURCEPATH = "worldgen/default.json";

    private final BiomeDataBasePopulatorJson jsonPopulator;

    public BiomeDataBasePopulator (){
        jsonPopulator = new BiomeDataBasePopulatorJson(new ResourceLocation(DynamicTreesTheAether.MODID, RESOURCEPATH));
    }

    public void populate(BiomeDataBase dbase) {
        jsonPopulator.populate(dbase);

    }
}

