package org.cloverservices.invisregions.entity;

import com.massivecraft.massivecore.command.editor.annotation.EditorName;
import com.massivecraft.massivecore.store.Entity;
import com.massivecraft.massivecore.util.MUtil;

import java.util.List;

@EditorName(value="config")
public class MConf extends Entity<MConf> {

    protected static transient MConf i;

    public List<String> deniedRegions = MUtil.list("spawn", "crates");

    public static MConf get() {
        return i;
    }

    public static void set(MConf conf) {
        i = conf;
    }
}
