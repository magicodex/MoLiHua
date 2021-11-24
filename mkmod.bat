@echo off
set module=%1%

pushd src
cd main
cd java
cd jasmine

mkdir %module%
cd %module%

mkdir application
cd application
mkdir web
cd web
mkdir controller
mkdir conversion
mkdir dto
mkdir validation
cd ../
cd ../

mkdir business
cd business
mkdir conversion
mkdir domain
mkdir dto
mkdir helper
mkdir service
cd ../

mkdir persistent
cd persistent
mkdir cond
mkdir dao
mkdir mapper
mkdir model

mkdir constant

popd
@echo on