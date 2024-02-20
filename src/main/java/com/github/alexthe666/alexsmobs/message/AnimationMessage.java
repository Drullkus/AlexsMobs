package com.github.alexthe666.alexsmobs.message;

import net.minecraft.network.FriendlyByteBuf;

public class AnimationMessage {
	private final int entityID;
	private final int index;

	public static AnimationMessage fromBuf(FriendlyByteBuf buf) {
		return new AnimationMessage(buf.readInt(), buf.readInt());
	}

	public AnimationMessage(int entityID, int index) {
		this.entityID = entityID;
		this.index = index;
	}

	public void write(FriendlyByteBuf buf) {
		buf.writeInt(this.entityID);
		buf.writeInt(this.index);
	}
}
