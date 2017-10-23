package cz.luzanky.minecraft.zombie;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.plugin.java.JavaPlugin;

public class ZombiePlugin extends JavaPlugin {
    public static final Logger LOG = Logger.getLogger(ZombiePlugin.class.getName());
    
    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, "Zombie plugin se nacita...");
    }
    
     @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("zombie")) {
            if (sender instanceof Player) {
                Player ja = (Player) sender;
                final Location playerLocation = ja.getLocation();
                final Location zombieLocation = new Location(ja.getWorld(), playerLocation.getX() + 5, playerLocation.getY(), playerLocation.getZ());
                
//                Zombie zombie = ja.getWorld().spawn(zombieLocation,Zombie.class);

                LOG.info(playerLocation.toString());
                LOG.info(playerLocation.getDirection().normalize().toString());
                
                for (int i = 0; i < 10; i++) {
                    Location loc = playerLocation.clone().add(playerLocation.getDirection().normalize().multiply(i).setY(1));
                    loc.getBlock().setType(Material.AIR);
                    loc = playerLocation.clone().add(playerLocation.getDirection().normalize().multiply(i).setY(0));
                    loc.getBlock().setType(Material.AIR);
                    
                }
                
                ja.sendMessage("Zombie vytvorena. Tak bacha :)");
                LOG.info("Auuuu!");
                return true;
            }
        }
        return false;
    }
    
    
    
}
