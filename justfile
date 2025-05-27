set shell := ["nu", "-c"]

run:
	./gradlew runclient

data: && fmt-json
	python datagen.py

build: data
	./gradlew build 

fmt-json:
	#!/bin/nu
	print "\nFormatting...\n"

	let files = fd | parse '{name}' | $in.name | filter {|file| $file | str contains ".json"}

	for file in $files {
		const TABS = "\t"
		const SPACES = "  "

		let text = cat $file | str trim -r | str replace $SPACES $TABS --all
		[$text, "\n"] | str join | save -f $file
	}
