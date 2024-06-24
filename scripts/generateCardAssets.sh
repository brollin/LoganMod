#!/bin/bash

# generate various card assets from cards, puts into images folder
# source: https://github.com/JohnnyBazooka89/StSModdingToolCardImagesCreator
# usage: scripts/generateCardAssets.sh

cd scripts
java -jar StSCardImagesCreator-0.0.5-jar-with-dependencies.jar

# move generated assets to cards folder
cd ..
cp scripts/images/Skills/dogeandroll* src/main/resources/loganmod/images/cards/
cp scripts/images/Skills/loganwail* src/main/resources/loganmod/images/cards/
cp scripts/images/Skills/pure-logan* src/main/resources/loganmod/images/cards/
cp scripts/images/Skills/dogeandroll* src/main/resources/loganmod/images/cards/
cp scripts/images/Attacks/logan-bite* src/main/resources/loganmod/images/cards/
cp scripts/images/Attacks/logan-feed* src/main/resources/loganmod/images/cards/
cp scripts/images/Attacks/dramatic-logan* src/main/resources/loganmod/images/cards/
