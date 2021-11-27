@echo off
set module=%1%

pushd src
cd main
cd java
cd jasmine

mkdir %module%
cd %module%

rem 创建 application 目录
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

rem 创建 business 目录
mkdir business
cd business
mkdir conversion
mkdir domain
mkdir dto
mkdir helper
mkdir service
cd ../

rem 创建 persistent 目录
mkdir persistent
cd persistent
mkdir cond
mkdir dao
mkdir mapper
mkdir model

rem 创建 constant 目录
mkdir constant

popd
@echo on