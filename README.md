# ProB Parser Library

## Building
Run the 'deploy' target with gradle. If you don't have gradle installed, you can use the provided wrapper 
   ./gradlew uberjar

On windows you need to install the program 'patch.exe'. 

The artifacts are copied to the build folder. 

# Bugs
Please report bugs and feature requests at http://jira.cobra.cs.uni-duesseldorf.de/


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
Jens Bendisposto, Marc Büngener, Fabian Fritz, Sebastian Krings, Michael Leuschel, Daniel Plagge, David Schneider

## Licence 

The ProB Parser Library source code is distributed under the Eclipse Public License - v 1.0 (see epl-v10.html) 

The Parser Library comes with ABSOLUTELY NO WARRANTY OF ANY KIND !
This software is distributed in the hope that it will be useful
but WITHOUT ANY WARRANTY. The author(s) do not accept responsibility
to anyone for the consequences of using it or for whether it serves
any particular purpose or works at all. No warranty is made about
the software or its performance.


## Commercial Support 
For availability of commercial support, please contact Formal Mind (http://www.formalmind.com/).

(c) 2011 STUPS group, University of Düsseldorf
