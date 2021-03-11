package io.github.v2lenkagamine.common.tileentity;

import java.awt.Color;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import io.github.v2lenkagamine.core.init.TileEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;

public class RGBlockTE extends TileEntity{
	
	public Color color = new Color(0, 0, 0);

    public void setColor(Color color) {
        this.color = color;
        this.markDirty();
    }

    public RGBlockTE() {
        super(TileEntityTypes.RGBLOCK_TE.get());
    }

    @Override
    @Nonnull
    public CompoundNBT write(@Nonnull CompoundNBT compound) {
        System.out.println("writing Color " + getColorAsInt());
        final CompoundNBT target = super.write(compound);
        target.putInt("color", this.getColorAsInt());
        return compound;
    }

    @Override
    public void read(@Nonnull BlockState state,@Nonnull CompoundNBT nbt) {
        super.read(state, nbt);
        this.setColorFromInt(nbt.getInt("color"));
        if(world != null){
            updateTile();
        }
        System.out.println("setting Color " + nbt.getInt("color"));
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        final CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("color", this.getColorAsInt());
        return new SUpdateTileEntityPacket(this.getPos(), -1, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        System.out.println("Received Data Packet at " + pkt.getPos());
        this.setColorFromInt(pkt.getNbtCompound().getInt("color"));
    }

    @Override
    @Nonnull
    public CompoundNBT getUpdateTag() {
        System.out.println("Getting an Update Tag");
        CompoundNBT nbt = super.getUpdateTag();
        nbt.putInt("color", this.getColorAsInt());
        return nbt;
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        System.out.println("Handling Update Tag");
        this.setColorFromInt(tag.getInt("color"));
        System.out.println("setting Color " + tag.getInt("color"));
    }

    public int getColorAsInt() {
        final Color color = this.getColor();
        return ((color.getRed() & 0xFF) << 16) | ((color.getGreen() & 0xFF) << 8) | (color.getBlue() & 0xFF);
    }

    public void setColorFromInt(final int color) {
        System.out.println("New Color is now " + color);
        this.setColor(new Color((color >> 16) & 0xFF, (color >> 8) & 0xFF, color & 0xFF));
        this.markDirty();
    }

    public Color getColor() {
        return color;
    }
    public void updateTile() {
        this.requestModelDataUpdate();
        this.markDirty();
        if (this.getWorld() != null) {
            this.getWorld().notifyBlockUpdate(pos, this.getBlockState(), this.getBlockState(), 3);
        }
    }
}