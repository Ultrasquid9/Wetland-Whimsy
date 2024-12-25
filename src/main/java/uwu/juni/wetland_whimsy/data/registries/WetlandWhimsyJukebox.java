package uwu.juni.wetland_whimsy.data.registries;

import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsySounds;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.JukeboxSong;

public class WetlandWhimsyJukebox {
	public static final ResourceKey<JukeboxSong> NUKE_THE_SWAMPS = ResourceKey.create(
		Registries.JUKEBOX_SONG, 
		ResourceLocation.fromNamespaceAndPath(
			WetlandWhimsy.MODID,	
			"nuke_the_swamps"
		)
	);

	public static void bootstap(BootstrapContext<JukeboxSong> context) {
		context.register(
			NUKE_THE_SWAMPS, 
			new JukeboxSong(
				WetlandWhimsySounds.NUKE_THE_SWAMPS,
				Component.translatable(Util.makeDescriptionId("jukebox_song", NUKE_THE_SWAMPS.location())),
				222, 
				6
			)
		);
	}
}
