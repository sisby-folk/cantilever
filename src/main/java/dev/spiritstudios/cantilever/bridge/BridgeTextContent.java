package dev.spiritstudios.cantilever.bridge;

import net.minecraft.text.*;

import java.util.Optional;

public record BridgeTextContent(Text content) implements TextContent {
	@Override
	public <T> Optional<T> visit(StringVisitable.Visitor<T> visitor) {
		Optional<T> visitResult = content.visit(visitor);
		if (visitResult.isEmpty()) {
			// This is a workaround for the game resolving the Bridge content as empty. Don't ask why!
			return visitor.accept("");
		}
		return visitResult;
	}

	@Override
	public <T> Optional<T> visit(StringVisitable.StyledVisitor<T> visitor, Style style) {
		return content.visit(visitor, style);
	}
}
