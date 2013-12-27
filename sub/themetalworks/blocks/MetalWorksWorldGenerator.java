package sub.themetalworks.blocks;

import java.util.Random;

import sub.themetalworks.TheMetalWorksHub;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.IWorldGenerator;

public class MetalWorksWorldGenerator implements IWorldGenerator
{
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        switch (world.provider.dimensionId)
        {
            case -1:
                generateNether(world, random, chunkX * 16, chunkZ * 16);
                break;

            case 0:
                generateSurface(world, random, chunkX * 16, chunkZ * 16);
                break;
        }
    }

    private void generateSurface(World world, Random random, int i, int j)
    {
        for (int k = 0; k < 20; k++)
        {
            int NickelOreXCoord = i + random.nextInt(16);
            int NickelOreYCoord = random.nextInt(64);
            int NickelOreZCoord = j + random.nextInt(16);
            (new WorldGenMinable(TheMetalWorksHub.NickelOre.blockID, 13)).generate(world, random, NickelOreXCoord, NickelOreYCoord, NickelOreZCoord);
            (new WorldGenMinable(TheMetalWorksHub.TitaniumOre.blockID, 13)).generate(world, random, NickelOreXCoord, NickelOreYCoord, NickelOreZCoord);
        }
    }

    private void generateNether(World world, Random random, int i, int j)
    {
        for (int k = 0; k < 20; k++)
        {
            int NickelOreXCoord = i + random.nextInt(16);
            int NickelOreYCoord = random.nextInt(64);
            int NickelOreZCoord = j + random.nextInt(16);
            (new WorldGenMinable(TheMetalWorksHub.NickelOre.blockID, 13)).generate(world, random, NickelOreXCoord, NickelOreYCoord, NickelOreZCoord);
        }
    }
}
