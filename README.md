# ProB Parsers Library

[![Build Status](https://travis-ci.org/bendisposto/probparsers.svg?branch=develop)](https://travis-ci.org/bendisposto/probparsers)


## Using
Releases are on [Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cde.hhu.stups), Snapshots on https://oss.sonatype.org/content/repositories/snapshots/. You can include the B parser in a gradle build script like this:

<pre>
def parser_version = '2.9.11' // development version: 2.9.12-SNAPSHOT
dependencies {
	compile group: "de.hhu.stups", name: "bparser", version: parser_version
	compile group: "de.hhu.stups", name: "ltlparser", version: parser_version// optional
	compile group: "de.hhu.stups", name: "parserbase", version: parser_version
	compile group: "de.hhu.stups", name: "prologlib", version: parser_version
}
</pre>

The repository contains some additional parsers:


* answerparser - Parses the answers sent by probcli
* bparser	- Parser for classical B
* cliparser	- Commandline interface for the classical B + LTL parser
* eventbstruct	- Parser for the Camille structural syntax
* ltlparser	- Parser for LTL and CTL
* parserbase	- Interface classes for the Parsers
* prologlib - Library to produce valid Prolog terms  
* theorymapping	- Parser for Theory mapping files (translation of EventB operators to Prolog predicates)
* translator - Translates B values into Java objects.
* unicode - Conversion of EventB Unicode Strings to ASCII and vice versa

## Building
Run the 'deploy' target with gradle.

On windows you need to install the program 'patch.exe'.

The artifacts are copied to the build folder.

# Bugs
Please report bugs and feature requests at https://probjira.atlassian.net


## Content

- bparser - parser for classical B (more information in http://www.stups.uni-duesseldorf.de/w/An_Object_Oriented_Parser_For_B_Specifications)
- prologlib - library to construct and manipulate well-formed prolog terms
- parserbase - library for uniform access to the formal language parsers (e.g. to embed a language into ltl)
- ltlparser - parser for LTL formulas - the parser delegates formulas in { } to a formalism specific parser (e.g. to the classical B parser).
              Also contains a parser for CTL
- answerparser - parser to read answers from the ProB prolog core
- unicode - lexer that transforms Event-B expressions and predicates from ASCII to Unicode syntax and vice3 versa (note: this is not extensible!)
- cliparser - glue code for embedding the parser in the prolog core (deprecated!)
- translator - translator for B expressions to java

## Contributors
The libraries contain contributions from (in alphabetical order)
Jens Bendisposto, Marc Büngener, Fabian Fritz, Dominik Hansen, Sebastian Krings, Michael Leuschel, Daniel Plagge, David Schneider

## Licence

The ProB Parser Library source code is distributed under the Eclipse Public License - v 1.0 (see epl-v10.html)

The Parser Library comes with ABSOLUTELY NO WARRANTY OF ANY KIND !
This software is distributed in the hope that it will be useful
but WITHOUT ANY WARRANTY. The author(s) do not accept responsibility
to anyone for the consequences of using it or for whether it serves
any particular purpose or works at all. No warranty is made about
the software or its performance.


(c) 2011-2018 STUPS group, University of Düsseldorf
