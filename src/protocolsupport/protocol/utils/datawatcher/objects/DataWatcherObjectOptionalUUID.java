package protocolsupport.protocol.utils.datawatcher.objects;

import java.util.UUID;

import io.netty.buffer.ByteBuf;
import protocolsupport.api.ProtocolType;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.serializer.MiscSerializer;
import protocolsupport.protocol.utils.datawatcher.ReadableDataWatcherObject;

public class DataWatcherObjectOptionalUUID extends ReadableDataWatcherObject<UUID> {

	@Override
	public void readFromStream(ByteBuf from) {
		if (from.readBoolean()) {
			value = MiscSerializer.readUUID(from);
		}
	}

	@Override
	public void writeToStream(ByteBuf to, ProtocolVersion version, String locale) {
		to.writeBoolean(value != null);
		if (value != null) {
			if (version.getProtocolType() == ProtocolType.PE) {
				MiscSerializer.writePEUUID(to, value);
			} else {
				MiscSerializer.writeUUID(to, value);
			}
		}
	}

}
