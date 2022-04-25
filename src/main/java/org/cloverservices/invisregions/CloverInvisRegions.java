package org.cloverservices.invisregions;

import com.massivecraft.massivecore.MassivePlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import lombok.Getter;
import org.cloverservices.invisregions.coll.MConfColl;
import org.cloverservices.invisregions.engine.EngineVisibility;

@Getter
public final class CloverInvisRegions extends MassivePlugin {

    public WorldGuardPlugin worldGuard;
    public MConfColl mConfColl;

    @Override
    public void onEnableInner() {
        this.worldGuard = (WorldGuardPlugin)this.getServer().getPluginManager().getPlugin("WorldGuard");

        this.mConfColl = new MConfColl(this);
        this.mConfColl.setActive(true);

        this.activate(new EngineVisibility(this));
    }

}
