package org.cloverservices.invisregions.coll;

import com.massivecraft.massivecore.store.Coll;
import com.massivecraft.massivecore.store.MStore;
import org.cloverservices.invisregions.CloverInvisRegions;
import org.cloverservices.invisregions.entity.MConf;

public class MConfColl extends Coll<MConf> {

    private CloverInvisRegions plugin;

    public MConfColl(final CloverInvisRegions plugin) {
        super("cloverinvisregions_conf", MConf.class, MStore.getDb(), plugin);

        this.plugin = plugin;
    }

    public void setActive(boolean active) {
        super.setActive(active);
        if (!active) {
            return;
        }
        MConf.set(this.get("instance", true));
    }
}
