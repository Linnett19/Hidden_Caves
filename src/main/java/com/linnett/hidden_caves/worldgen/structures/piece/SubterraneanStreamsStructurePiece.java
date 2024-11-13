package com.linnett.hidden_caves.worldgen.structures.piece;
import com.linnett.hidden_caves.block.ModBlocks;
import com.linnett.hidden_caves.misc.HCmath;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import org.apache.commons.lang3.mutable.MutableBoolean;


public class SubterraneanStreamsStructurePiece  extends AbstractCaveGenerationStructurePiece{
    public SubterraneanStreamsStructurePiece(BlockPos chunkCorner, BlockPos holeCenter, int bowlHeight, int bowlRadius) {
        super(HCstrucurePieceRegistry.SUBTERRANEAN_STREAMS.get(), chunkCorner, holeCenter, bowlHeight, bowlRadius);
    }

    public SubterraneanStreamsStructurePiece(CompoundTag tag) {
        super(HCstrucurePieceRegistry.SUBTERRANEAN_STREAMS.get(), tag);
    }

    public SubterraneanStreamsStructurePiece(StructurePieceSerializationContext structurePieceSerializationContext, CompoundTag tag) {
        this(tag);
    }

    public void postProcess(WorldGenLevel level, StructureManager featureManager, ChunkGenerator chunkGen, RandomSource random, BoundingBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
        int cornerX = this.chunkCorner.getX();
        int cornerY = this.chunkCorner.getY();
        int cornerZ = this.chunkCorner.getZ();
        BlockPos.MutableBlockPos carve = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos carveBelow = new BlockPos.MutableBlockPos();
        carve.set(cornerX, cornerY, cornerZ);
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                MutableBoolean doFloor = new MutableBoolean(false);
                for (int y = 15; y >= 0; y--) {
                    carve.set(cornerX + x, Mth.clamp(cornerY + y, level.getMinBuildHeight(), level.getMaxBuildHeight()), cornerZ + z);
                    if (inCircle(carve) && !checkedGetBlock(level, carve).is(Blocks.BEDROCK)) {
                        checkedSetBlock(level, carve, Blocks.CAVE_AIR.defaultBlockState());
                        surroundCornerOfLiquid(level, carve);
                        carveBelow.set(carve.getX(), carve.getY() - 1, carve.getZ());
                        doFloor.setTrue();
                    } else if (doFloor.isTrue()) {
                        break;
                    }
                }
            }
        }
    }

    private void surroundCornerOfLiquid(WorldGenLevel level, BlockPos.MutableBlockPos center) {
        BlockPos.MutableBlockPos offset = new BlockPos.MutableBlockPos();
        for (Direction dir : Direction.values()) {
            offset.set(center);
            offset.move(dir);
            BlockState state = checkedGetBlock(level, offset);
            if (!state.getFluidState().isEmpty()) {
                checkedSetBlock(level, offset, ModBlocks.RIVER_PEBBLES.get().defaultBlockState());
            }
        }
    }

    private boolean inCircle(BlockPos.MutableBlockPos carve) {
        float pillarNoise = (HCmath.sampleNoise3D(carve.getX(), (int) (carve.getY() * 0.4F), carve.getZ(), 500) + 1.0F) * 0.5F;
        float verticalNoise = (HCmath.sampleNoise2D(carve.getX(), carve.getZ(), 800) + 1.0F) * 0.2F - (HCmath.smin(HCmath.sampleNoise2D(carve.getX(), carve.getZ(), 100), -0.5F, 0.1F) + 0.5F) * 0.7F;
        double distToCenter = carve.distToLowCornerSqr(this.holeCenter.getX(), carve.getY(), this.holeCenter.getZ());
        float f = getHeightOf(carve);
        float f1 = (float) Math.pow(canyonStep(f, 6), 2.5F);
        float rawHeight = Math.abs(this.holeCenter.getY() - carve.getY()) / (float) (height * 0.5F);
        float reverseRawHeight = 1F - rawHeight;
        double yDist = HCmath.smin((float) Math.pow(reverseRawHeight, 0.3F), 1.0F, 0.1F);
        double targetRadius = (yDist * (radius * pillarNoise * f1) * radius);
        return distToCenter < targetRadius && rawHeight < 1 - verticalNoise;
    }

    private void decorateFloor(WorldGenLevel level, RandomSource rand, BlockPos.MutableBlockPos carveBelow) {
        float floorNoise = (HCmath.sampleNoise3D(carveBelow.getX(),carveBelow.getY(), carveBelow.getZ(), 30) + 1.0F) * 0.5F;

        for (int i = 0; i < Math.ceil(floorNoise * 3); i++) {
            carveBelow.move(0, 1, 0);
            if(i > 0)
            {
                if(floorNoise< 0.33f)
                    checkedSetBlock(level, carveBelow, ModBlocks.MOSSY_RIVER_PEBBLES.get().defaultBlockState());
            }
            else if(i != 0)
            {
                checkedSetBlock(level, carveBelow, ModBlocks.RIVER_PEBBLES.get().defaultBlockState());

            }

        }
    }
    private float getHeightOf(BlockPos.MutableBlockPos carve) {
        int halfHeight = this.height / 2;
        if (carve.getY() > this.holeCenter.getY() + halfHeight + 1 || carve.getY() < this.holeCenter.getY() - halfHeight) {
            return 0.0F;
        } else {
            return 1F - ((this.holeCenter.getY() + halfHeight - carve.getY()) / (float) (height * 2));
        }
    }

    private float canyonStep(float heightScale, int scaleTo) {
        int clampTo100 = (int) ((heightScale) * scaleTo * scaleTo);
        return Mth.clamp((float) (Math.round(clampTo100 / (float) scaleTo)) / (float) scaleTo, 0F, 1F);
    }


}
