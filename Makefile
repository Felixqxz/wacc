# Sample Makefile for the WACC Compiler lab: edit this to build your own comiler
# Locations

# the make rules

all: rules

# runs the antlr build script then attempts to compile all .java files within src
rules:
	mvn clean install

clean:
	mvn clean

.PHONY: all rules clean
