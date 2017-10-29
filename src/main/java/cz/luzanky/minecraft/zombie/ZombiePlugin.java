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
import org.bukkit.util.Vector;

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
                Vector directionVector = playerLocation.getDirection().normalize();
                Vector directionVectorNormalized = new Vector(valueOf(directionVector.getX()), valueOf(directionVector.getY()), valueOf(directionVector.getZ()));
                LOG.info(directionVectorNormalized.toString());

                for (int i = 0; i < 10; i++) {
                    Location loc = playerLocation.clone().add(directionVectorNormalized.clone().multiply(i));
                    loc.getBlock().setType(Material.AIR);
                    loc = loc.clone().add(0,1,0);
                    loc.getBlock().setType(Material.AIR);
                }
                
                ja.sendMessage("Zombie vytvorena. Tak bacha :)");
                LOG.info("Auuuu!");
                return true;
            }
        }

         if (label.equalsIgnoreCase("zed")) {
             if (sender instanceof Player) {
                 Player ja = (Player) sender;
                 final Location playerLocation = ja.getLocation();


                 Vector directionVector = playerLocation.getDirection().normalize();
                 Vector directionVectorNormalized = new Vector(valueOf(directionVector.getX()), valueOf(directionVector.getY()), valueOf(directionVector.getZ()));
                 LOG.info(directionVectorNormalized.toString());
                 Location initialLoc = playerLocation.clone().add(directionVectorNormalized);
                 for (int i = 0; i < 10; i++) {
                     Location blockLoc = initialLoc.clone().add(i, 0, 0);

                     blockLoc.getBlock().setType(Material.GOLD_ORE);
                 }

                 ja.sendMessage("Zed postavena.");
                 return true;
             }
         }
        return false;
    }

    private double valueOf(double x) {
        if (x > 0.5) {
            return 1;
        }
        if (x < -0.5) {
            return -1;
        }
        return 0;
    }


}
