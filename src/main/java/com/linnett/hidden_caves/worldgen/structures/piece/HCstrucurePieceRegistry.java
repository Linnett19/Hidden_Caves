package com.linnett.hidden_caves.worldgen.structures.piece;

import com.linnett.hidden_caves.Hidden_caves;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class HCstrucurePieceRegistry {
    public static final DeferredRegister<StructurePieceType> DEF_REG = DeferredRegister.create(Registries.STRUCTURE_PIECE, Hidden_caves.MOD_ID);
    public static final RegistryObject<StructurePieceType> SUBTERRANEAN_STREAMS = DEF_REG.register("forlorn_canyon", () -> SubterraneanStreamsStructurePiece::new);
}
