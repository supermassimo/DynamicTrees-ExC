package maxhyper.dynamictreestheaether.trees;

import com.ferreusveritas.dynamictrees.ModTrees;
import com.ferreusveritas.dynamictrees.api.TreeRegistry;
import com.ferreusveritas.dynamictrees.blocks.BlockRooty;
import com.ferreusveritas.dynamictrees.systems.featuregen.FeatureGenFruit;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.trees.TreeFamily;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.blocks.natural.BlockAetherLog;
import com.gildedgames.the_aether.blocks.util.EnumLogType;
import com.gildedgames.the_aether.items.ItemsAether;
import maxhyper.dynamictreestheaether.ModConfigs;
import maxhyper.dynamictreestheaether.ModContent;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.List;
import java.util.Objects;

public class ALTreeCrystal extends TreeFamily {

	public static Block leavesBlock = BlocksAether.crystal_leaves;
	public static Block logBlock = BlocksAether.aether_log;

	public class SpeciesCrystal extends Species {

		SpeciesCrystal(TreeFamily treeFamily) {
			super(treeFamily.getName(), treeFamily, ModContent.crystalLeavesProperties);

			setBasicGrowingParameters(0.2f, 8.0f, 3, 3, 0.6f);
			setGrowthLogicKit(TreeRegistry.findGrowthLogicKit(ModTrees.CONIFER));

			envFactor(Type.COLD, 1.4f);
			envFactor(Type.HOT, 1.2f);

			addGenFeature(new FeatureGenFruit(ModContent.blockWhiteApple).setFruitingRadius(3));
			generateSeed();
			clearAcceptableSoils();
			addAcceptableSoils(ModContent.AETHERLIKE);
		}
//		@Override
//		public BlockRooty getRootyBlock(World world, BlockPos pos) {
//			return ModContent.rootyDirtAether;
//		}
	}

	public ALTreeCrystal() {
		super(new ResourceLocation(maxhyper.dynamictreestheaether.DynamicTreesTheAether.MODID, "crystal"));

		setPrimitiveLog(logBlock.getDefaultState().withProperty(BlockAetherLog.wood_type, EnumLogType.Skyroot), new ItemStack(logBlock, 1, 0));

		ModContent.crystalLeavesProperties.setTree(this);
		ModContent.crystalFruitLeavesProperties.setTree(this);

		addConnectableVanillaLeaves((state) -> state.getBlock() == leavesBlock);
	}
	@Override
	public ItemStack getPrimitiveLogItemStack(int qty) {
		ItemStack stack = new ItemStack(Objects.requireNonNull(logBlock), qty, 0);
		stack.setCount(MathHelper.clamp(qty, 0, 64));
		return stack;
	}

	@Override
	public ItemStack getStick(int qty) {
		return new ItemStack(ItemsAether.skyroot_stick, qty);
	}

	@Override
	public void createSpecies() {
		setCommonSpecies(new SpeciesCrystal(this));
	}

	@Override
	public void registerSpecies(IForgeRegistry<Species> speciesRegistry) {
		super.registerSpecies(speciesRegistry);
	}

	@Override
	public List<Item> getRegisterableItems(List<Item> itemList) {
		return super.getRegisterableItems(itemList);
	}

}
