package com.kawaiiwolf.kawaiicrops.block;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import com.kawaiiwolf.kawaiicrops.tileentity.TileEntityKawaiiCuttingBoard;

public class BlockKawaiiCuttingBoard extends BlockKawaiiCookingBlock {

	protected BlockKawaiiCuttingBoard() {
		super(Material.wood, "cuttingboard");
		maxY = 0.125d;
		minX = minZ = 0.0625d;
		maxX = maxZ = 1.0d - minX;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityKawaiiCuttingBoard();
	}

}