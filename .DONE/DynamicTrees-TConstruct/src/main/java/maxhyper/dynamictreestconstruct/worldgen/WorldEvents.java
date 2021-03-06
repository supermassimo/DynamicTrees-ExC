package maxhyper.dynamictreestconstruct.worldgen;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import slimeknights.tconstruct.world.entity.EntityBlueSlime;
import slimeknights.tconstruct.world.worldgen.MagmaSlimeIslandGenerator;
import slimeknights.tconstruct.world.worldgen.SlimeIslandGenerator;

public class WorldEvents {

    // Custom slime spawning on slime islands
    Biome.SpawnListEntry magmaSlimeSpawn = new Biome.SpawnListEntry(EntityMagmaCube.class, 150, 4, 6);
    Biome.SpawnListEntry blueSlimeSpawn = new Biome.SpawnListEntry(EntityBlueSlime.class, 15, 2, 4);

    @SubscribeEvent
    public void extraSlimeSpawn(WorldEvent.PotentialSpawns event) {
        if(event.getType() == EnumCreatureType.MONSTER) {
            // inside a magma slime island?
            if(DynamicMagmaSlimeIslandGenerator.INSTANCE.isSlimeIslandAt(event.getWorld(), event.getPos().down(3))) {
                // spawn magma slime, pig zombies have weight 100
                event.getList().clear();
                event.getList().add(magmaSlimeSpawn);
            }
            // inside a slime island?
            if(DynamicSlimeIslandGenerator.INSTANCE.isSlimeIslandAt(event.getWorld(), event.getPos().down(3))) {
                // spawn blue slime, most regular mobs have weight 10
                event.getList().clear();
                event.getList().add(blueSlimeSpawn);
            }
        }
    }
}