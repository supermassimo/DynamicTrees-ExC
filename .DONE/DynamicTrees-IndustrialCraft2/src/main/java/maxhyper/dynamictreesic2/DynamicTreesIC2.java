package maxhyper.dynamictreesic2;

import com.ferreusveritas.dynamictrees.ModConstants;

import maxhyper.dynamictreesic2.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid= DynamicTreesIC2.MODID, name= DynamicTreesIC2.NAME, dependencies = DynamicTreesIC2.DEPENDENCIES, updateJSON = "https://github.com/supermassimo/DynamicTrees-ExC/tree/1.12.2/.DONE/DynamicTrees-IndustrialCraft2/version_info.json?raw=true")
public class DynamicTreesIC2 {
	
	public static final String MODID = "dynamictreesic2";
	public static final String NAME = "Dynamic Trees for Industrial Craft 2";
	public static final String DEPENDENCIES = "required-after:" + ModConstants.DYNAMICTREES_LATEST
			+ ";required-after:ic2";
	
	@Mod.Instance
	public static DynamicTreesIC2 instance;
	
	@SidedProxy(clientSide = "maxhyper.dynamictreesic2.proxy.ClientProxy", serverSide = "maxhyper.dynamictreesic2.proxy.CommonProxy") //com.
	public static CommonProxy proxy;

	public static Logger logger;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		proxy.preInit();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		proxy.postInit();
	}
	
}
