package uwu.juni.wetland_whimsy.datapacks;

import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public record Incense(Item item, ResourceLocation lootTable, List<ResourceLocation> entities) {
	public static final Codec<Incense> CODEC = RecordCodecBuilder.create(
		instance -> instance.group(
			BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(Incense::item),
			ResourceLocation.CODEC.fieldOf("loot_table").forGetter(Incense::lootTable),
			Codec.list(ResourceLocation.CODEC).fieldOf("entities").forGetter(Incense::entities)
		)
		.apply(instance, Incense::new)	
	);
}
