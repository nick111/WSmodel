JAVAC = javac
PRONAME = Main
PROGRAM_WS = WSmodel.class
PROGRAM_RND = Rndgraph.class
PROGRAM_DC = Degreecal.class
SRCS_WS = WSmodel.java
SRCS_RND = Rndgraph.java
SRCS_DC = Degreecal.java

all: compile test

compile: $(SRCS_WS) $(SRCS_RND) $(SRCS_DC)
	$(JAVAC) $(SRCS_WS) $(SRCS_RND) $(SRCS_DC)

test: $(PROGRAM_WS) $(PROGRAM_RND) $(PROGRAM_DC)
	@./test.sh

.PHONY: clean
clean:
	$(RM) $(PROGRAM_WS) $(PROGRAM_RND) $(PROGRAM_DC) *.csv *.pdf