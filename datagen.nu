#!bin/nu

./gradlew rundata

print "\nFormatting...\n"

let files = fd | parse '{name}' | filter {|item| $item.name | str contains ".json"}

for file in $files {
	const TABS = "\t"
	const SPACES = "  "

	let name = $file.name
	let text = cat $name | str replace $SPACES $TABS --all
	[$text, "\n"] | str join | save -f $name
}
