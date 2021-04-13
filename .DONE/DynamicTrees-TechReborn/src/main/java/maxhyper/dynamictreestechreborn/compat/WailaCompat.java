package maxhyper.dynamictreestechreborn.compat;

import maxhyper.dynamictreestechreborn.blocks.BlockDynamicBranchRubber;
import mcp.mobius.waila.api.IWailaPlugin;
import mcp.mobius.waila.api.IWailaRegistrar;
import mcp.mobius.waila.api.WailaPlugin;

@WailaPlugin
public class WailaCompat implements IWailaPlugin {
	
	@Override
	public void register(IWailaRegistrar registrar) {
		WailaBranchHandlerRubber branchHandler = new WailaBranchHandlerRubber();
		
		registrar.registerBodyProvider(branchHandler, BlockDynamicBranchRubber.class);
		registrar.registerNBTProvider(branchHandler, BlockDynamicBranchRubber.class);
	}
	
}
