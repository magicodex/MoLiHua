@echo off
set module=%1%

pushd jasmine-demo
cd src
cd main
cd java
cd jasmine
cd demo

mkdir %module%
cd %module%

rem 创建 application 目录
mkdir application
cd application
mkdir web
cd web
mkdir controller
mkdir dto
mkdir adapter
cd ../
cd ../

rem 创建 business 目录
mkdir business
cd business
mkdir service
mkdir dto
mkdir adapter
mkdir domain
cd ../

rem 创建 persistence 目录
mkdir persistence
cd persistence
mkdir entity
mkdir dao
mkdir mapper
mkdir param
cd ../

rem 创建 constant 目录
mkdir constant

popd
@echo on