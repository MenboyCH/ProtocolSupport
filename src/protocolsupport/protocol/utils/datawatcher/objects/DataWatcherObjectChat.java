package protocolsupport.protocol.utils.datawatcher.objects;

import io.netty.buffer.ByteBuf;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.api.chat.ChatAPI;
import protocolsupport.api.chat.components.BaseComponent;
import protocolsupport.protocol.serializer.StringSerializer;
import protocolsupport.protocol.utils.datawatcher.ReadableDataWatcherObject;

public class DataWatcherObjectChat extends ReadableDataWatcherObject<BaseComponent> {

	@Override
	public void readFromStream(ByteBuf from) {
		value = ChatAPI.fromJSON(StringSerializer.readVarIntUTF8String(from));
	}

	@Override
	public void writeToStream(ByteBuf to, ProtocolVersion version, String locale) {
		StringSerializer.writeString(to, version, ChatAPI.toJSON(value));
	}

}
