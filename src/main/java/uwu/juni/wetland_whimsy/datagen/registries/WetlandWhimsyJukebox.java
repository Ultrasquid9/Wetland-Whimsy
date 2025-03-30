package uwu.juni.wetland_whimsy.datagen.registries;

import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.JukeboxSong;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsySounds;

public class WetlandWhimsyJukebox {
	public static final ResourceKey<JukeboxSong> NUKE_THE_SWAMPS = ResourceKey.create(
		Registries.JUKEBOX_SONG, 
		WetlandWhimsy.rLoc("nuke_the_swamps")
	);

	public static final ResourceKey<JukeboxSong> DISK69 = ResourceKey.create(
		Registries.JUKEBOX_SONG, 
		WetlandWhimsy.rLoc("disk69")
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

		context.register(
			DISK69, 
			new JukeboxSong(
				WetlandWhimsySounds.DISK69,
				Component.translatable(Util.makeDescriptionId("jukebox_song", DISK69.location())),
				35, 
				1
			)
		);
	}
}
