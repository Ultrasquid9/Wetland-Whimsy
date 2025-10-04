package uwu.juni.wetland_whimsy.datapacks;

import java.util.List;

import org.joml.Vector3f;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.LootTable;

public record Incense(Item item, ResourceLocation lootTable, Vector3f color, List<EntityType<?>> entities) {
	public static final Codec<Incense> CODEC = RecordCodecBuilder.create(
		instance -> instance.group(
			BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(Incense::item),
			ResourceLocation.CODEC.fieldOf("loot_table").forGetter(Incense::lootTable),
			ExtraCodecs.VECTOR3F.fieldOf("color").forGetter(Incense::color),
			BuiltInRegistries.ENTITY_TYPE.byNameCodec().listOf().fieldOf("entities").forGetter(Incense::entities)
		)
		.apply(instance, Incense::new)	
	);

	public ResourceKey<LootTable> getLootKey() {
		return ResourceKey.create(
			Registries.LOOT_TABLE, 
			lootTable
		);
	}
}
