import subprocess
import os

# Constants 
fake_ender_campfire_file = "/ender_campfire_fire.png"
fake_ender_campfire_dir = "src/main/resources/assets/endergetic/textures/block"

fake_cupric_campfire_file = "/cupric_campfire_fire.png"
fake_cupric_campfire_dir = "src/main/resources/assets/caverns_and_chasms/textures/block"

processor_list_dir = "src/generated/resources/data/wetland_whimsy/worldgen/processor_list/"

# Creating fake campfire fire textures, since datagen otherwise does not think that they exist
def make_campfire(dir, file):
	os.makedirs(dir, exist_ok=True)
	open(dir + file, "w")

make_campfire(fake_ender_campfire_dir, fake_ender_campfire_file)
make_campfire(fake_cupric_campfire_dir, fake_cupric_campfire_file)

# Running the datagen
subprocess.run(["./gradlew", "rundata"])

# Removing the fake campfire fire textures
os.remove(fake_ender_campfire_dir + fake_ender_campfire_file)
os.remove(fake_cupric_campfire_dir + fake_cupric_campfire_file)

# Removing the autogenerated processor lists, since those were written manually 
os.remove(processor_list_dir + "dungeon.json")
os.remove(processor_list_dir + "limestone_rubble.json")

# The biome is drunk it thinks it has a "lost_goat" field 
marsh_file = "src/generated/resources/data/wetland_whimsy/worldgen/biome/marsh.json"

def kill(line):
	str = "/" + line + "/d"
	subprocess.run(["sed", "-i", str, marsh_file])

kill("end_creature")
kill("lost_goat")
