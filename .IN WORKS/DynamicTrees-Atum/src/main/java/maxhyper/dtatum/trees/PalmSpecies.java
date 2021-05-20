package maxhyper.dtatum.trees;

import com.ferreusveritas.dynamictrees.api.TreeHelper;
import com.ferreusveritas.dynamictrees.api.network.MapSignal;
import com.ferreusveritas.dynamictrees.api.registry.TypedRegistry;
import com.ferreusveritas.dynamictrees.blocks.branches.BranchBlock;
import com.ferreusveritas.dynamictrees.blocks.leaves.DynamicLeavesBlock;
import com.ferreusveritas.dynamictrees.blocks.leaves.LeavesProperties;
import com.ferreusveritas.dynamictrees.systems.nodemappers.FindEndsNode;
import com.ferreusveritas.dynamictrees.trees.Family;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.trees.species.NetherFungusSpecies;
import com.ferreusveritas.dynamictrees.util.BranchDestructionData;
import com.ferreusveritas.dynamictrees.util.CoordUtils;
import com.ferreusveritas.dynamictrees.util.SafeChunkBounds;
import maxhyper.dtatum.growthlogic.DTAtumGrowthLogicKits;
import maxhyper.dtatum.leavesProperties.PalmLeavesProperties;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class PalmSpecies extends Species {
    public static final TypedRegistry.EntryType<Species> TYPE = createDefaultType(PalmSpecies::new);

    public PalmSpecies(ResourceLocation name, Family family, LeavesProperties leavesProperties) {
        super(name, family, leavesProperties);
        setGrowthLogicKit(DTAtumGrowthLogicKits.PALM); //palm growth logic kit by default
    }

    @Override
    public boolean postGrow(World world, BlockPos rootPos, BlockPos treePos, int soilLife, boolean natural) {
        BlockState trunkBlockState = world.getBlockState(treePos);
        BranchBlock branch = TreeHelper.getBranch(trunkBlockState);
        if (branch == null) return false;
        FindEndsNode endFinder = new FindEndsNode();
        MapSignal signal = new MapSignal(endFinder);
        branch.analyse(trunkBlockState, world, treePos, Direction.DOWN, signal);
        List<BlockPos> endPoints = endFinder.getEnds();

        for (BlockPos endPoint: endPoints) {
            TreeHelper.ageVolume(world, endPoint, 2, 3, 3, SafeChunkBounds.ANY);
        }

        // Make sure the bottom block is always just a little thicker that the block above it.
        int radius = branch.getRadius(world.getBlockState(treePos.above()));
        if (radius != 0) {
            branch.setRadius(world, treePos, radius + 1, null);
        }

        return super.postGrow(world, rootPos, treePos, soilLife, natural);
    }

    public boolean transitionToTree(World world, BlockPos pos) {
        //Ensure planting conditions are right
        Family family = getFamily();
        if(world.isEmptyBlock(pos.above()) && isAcceptableSoil(world, pos.below(), world.getBlockState(pos.below()))) {
            BranchBlock branch = family.getBranch();
            if (branch == null) return false;
            branch.setRadius(world, pos, (int)family.getPrimaryThickness(), null);//set to a single branch with 1 radius
            world.setBlockAndUpdate(pos.above(), getLeavesProperties().getDynamicLeavesState());//Place 2 leaf blocks on top
            world.setBlockAndUpdate(pos.above(2), getLeavesProperties().getDynamicLeavesState().setValue(DynamicLeavesBlock.DISTANCE, 3));
            placeRootyDirtBlock(world, pos.below(), 15);//Set to fully fertilized rooty dirt underneath
            return true;
        }
        return false;
    }

    @Override
    public void postGeneration(World worldObj, IWorld world, BlockPos rootPos, Biome biome, int radius, List<BlockPos> endPoints, SafeChunkBounds safeBounds, BlockState initialDirtState) {
        for (BlockPos endPoint : endPoints) {
            TreeHelper.ageVolume(world, endPoint, 1, 2, 3, safeBounds);
        }
        super.postGeneration(worldObj, world, rootPos, biome, radius, endPoints, safeBounds, initialDirtState);
    }

    @Nullable
    @Override
    public HashMap<BlockPos, BlockState> getFellingLeavesClusters(BranchDestructionData destructionData) {

        if(destructionData.getNumEndpoints() < 1) {
            return null;
        }

        HashMap<BlockPos, BlockState> leaves = new HashMap<>();
        BlockPos relPos = destructionData.getEndPointRelPos(0).above();//A palm tree is only supposed to have one endpoint at it's top.
        LeavesProperties leavesProperties = getCommonSpecies().getLeavesProperties();

        leaves.put(relPos, leavesProperties.getDynamicLeavesState(4));//The barky overlapping part of the palm frond cluster
        leaves.put(relPos.above(), leavesProperties.getDynamicLeavesState(3));//The leafy top of the palm frond cluster

        //The 4 corners and 4 sides of the palm frond cluster
        for(int hydro = 1; hydro <= 2; hydro++) {
            BlockState extState = leavesProperties.getDynamicLeavesState(hydro);
            for(CoordUtils.Surround surr : PalmLeavesProperties.DynamicPalmLeavesBlock.hydroSurroundMap[hydro]) {
                leaves.put(relPos.offset(surr.getOpposite().getOffset()), extState);
            }
        }

        return leaves;
    }

}
