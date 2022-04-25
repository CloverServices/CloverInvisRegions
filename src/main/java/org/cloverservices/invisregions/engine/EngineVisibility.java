package org.cloverservices.invisregions.engine;

import com.massivecraft.massivecore.Engine;
import com.massivecraft.massivecore.util.MUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.cloverservices.invisregions.CloverInvisRegions;
import org.cloverservices.invisregions.entity.MConf;
import org.cloverservices.invisregions.utils.EntityHider;

public class EngineVisibility extends Engine {

    private final CloverInvisRegions plugin;
    private final EntityHider entityHider;

    public EngineVisibility(final CloverInvisRegions plugin) {
        this.plugin = plugin;
        this.entityHider = new EntityHider(plugin, EntityHider.Policy.BLACKLIST);
    }

    @EventHandler
    public void onPlayerMove(final PlayerMoveEvent event) {

        final Player player = event.getPlayer();

        if (MUtil.isNpc(player)) return;
        if (!(event.getFrom().getBlockX() == event.getTo().getBlockX()) && (event.getFrom().getBlockZ() == event.getTo().getBlockZ()) && (event.getFrom().getBlockY() == event.getTo().getBlockY())) return;
        if (!isInRegion(player)) return;

        for (final Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            this.entityHider.hideEntity(player, onlinePlayer);
        }
    }

    private boolean isInRegion(final Player player) {
        final Location loc = player.getLocation();
        return this.plugin.getWorldGuard().getRegionManager(loc.getWorld()).getApplicableRegions(loc).getRegions().stream().anyMatch(region -> MConf.get().deniedRegions.contains(region.getId()));
    }
}
