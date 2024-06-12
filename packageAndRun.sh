#!/bin/sh

# package the mod
mvn package -f ./pom.xml

# (brittle) run the game via the ModTheSpire launcher
cd '/Users/ben.rollin/Library/Application Support/Steam/steamapps/common/SlayTheSpire/SlayTheSpire.app/Contents/Resources'
./jre/bin/java -jar '/Users/ben.rollin/Library/Application Support/Steam/steamapps/workshop/content/646570/1605060445/ModTheSpire.jar'

echo "running modded Slay the Spire, press CTRL+C to exit"
while true; do
    sleep 30
done
