
package pl.smpickaxe.utils;

import org.bukkit.entity.EntityType;

public class EntityTypesInSpawner {

    public static EntityType RandomEntityType(){
        int rand = (int) (Math.random() * (18 - 1 + 1) + 1);
        switch(rand){
            case 1:return EntityType.CAVE_SPIDER;
            case 2:return EntityType.CHICKEN;
            case 3:return EntityType.COW;
            case 4:return EntityType.CREEPER;
            case 5:return EntityType.ENDERMAN;
            case 6:return EntityType.GHAST;
            case 7:return EntityType.HOGLIN;
            case 8:return EntityType.PHANTOM;
            case 9:return EntityType.PIGLIN;
            case 10:return EntityType.POLAR_BEAR;
            case 11:return EntityType.RABBIT;
            case 12:return EntityType.SHEEP;
            case 13:return EntityType.SPIDER;
            case 14:return EntityType.SKELETON;
            case 15:return EntityType.SQUID;
            case 16:return EntityType.WITHER_SKELETON;
            case 17:return EntityType.ZOMBIE;
            case 18:return EntityType.PIG;
            default:return EntityType.PIG;


        }

    }
}


