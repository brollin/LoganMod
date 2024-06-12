#!/bin/bash

# extract audio from mpg4 video with ffmpeg
# usage: ./extract_audio.sh video.mp4 audio.ogg

ffmpeg -i $1 -vn -acodec libvorbis -ab 128k -ar 44100 -ac 2 $2

