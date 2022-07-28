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
mkdir controller
cd ../

rem 创建 business 目录
mkdir business
cd business
mkdir service
cd ../

rem 创建 persistence 目录
mkdir persistence
cd persistence
mkdir entity
mkdir dao
mkdir mapper
cd ../

rem 创建 constant 目录
mkdir constant

rem 创建 dto 目录
mkdir dto

popd
@echo on