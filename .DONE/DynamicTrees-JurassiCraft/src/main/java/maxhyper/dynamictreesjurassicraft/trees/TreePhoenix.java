package maxhyper.dynamictreesjurassicraft.trees;

import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.trees.TreeFamily;
import maxhyper.dynamictreesjurassicraft.DynamicTreesJurassiCraft;
import maxhyper.dynamictreesjurassicraft.ModContent;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.List;
import java.util.Objects;

public class TreePhoenix extends Palm {

	public static Block leavesBlock = Block.getBlockFromName("jurassicraft:phoenix_leaves");
	public static Block logBlock = Block.getBlockFromName("jurassicraft:phoenix_log");
	public static Block saplingBlock = Block.getBlockFromName("jurassicraft:phoenix_sapling");

	public class SpeciesCalamites extends Palm.SpeciesPalm {

		SpeciesCalamites(TreeFamily treeFamily) {
			super(treeFamily.getName(), treeFamily, ModContent.phoenixLeavesProperties);

			setBasicGrowingParameters(0.3f, 12.0f, upProbability, lowestBranchHeight, 0.9f);

		}
	}

	public TreePhoenix() {
		super(new ResourceLocation(DynamicTreesJurassiCraft.MODID, "phoenix"));

		setPrimitiveLog(logBlock.getDefaultState(), new ItemStack(logBlock, 1, 0));

		ModContent.phoenixLeavesProperties.setTree(this);

		addConnectableVanillaLeaves((state) -> state.getBlock() == leavesBlock);
	}
	@Override
	public ItemStack getPrimitiveLogItemStack(int qty) {
		ItemStack stack = new ItemStack(Objects.requireNonNull(logBlock));
		stack.setCount(MathHelper.clamp(qty, 0, 64));
		return stack;
	}

	@Override
	public void createSpecies() {
		setCommonSpecies(new SpeciesCalamites(this));
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
