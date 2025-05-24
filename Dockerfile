FROM openjdk:17-alpine

COPY brotli-1.0.9-r5.apk brotli.apk
COPY brotli-libs-1.0.9-r5.apk brotlilib.apk
COPY bzip2-1.0.8-r1.apk bzip2.apk
COPY libpng-1.6.37-r1.apk libpng.apk
COPY zlib-1.2.12-r3.apk zlib.apk
COPY expat-2.5.0-r0.apk expat.apk
COPY freetype-2.10.4-r3.apk freetype.apk
COPY libuuid-2.37.4-r0.apk libuuid.apk
COPY fontconfig-2.13.1-r4.apk fc.apk
COPY libbz2-1.0.8-r1.apk libbz2.apk

RUN apk add --allow-untrusted libbz2.apk brotlilib.apk expat.apk freetype.apk libuuid.apk fc.apk brotli.apk bzip2.apk libpng.apk zlib.apk

COPY fonts/ttf-dejavu /usr/share/fonts/
RUN fc-cache -f
RUN fc-list


