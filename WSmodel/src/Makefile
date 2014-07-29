JAVAC = javac
PRONAME = Main
PROGRAM_WS = WSmodel.class
PROGRAM_RND = Rndgraph.class
PROGRAM_DC = Degreecal.class
PROGRAM_TR = Triangle.class
PROGRAM_BA = BAModel.class
SRCS_WS = WSmodel.java
SRCS_RND = Rndgraph.java
SRCS_DC = Degreecal.java
SRCS_TR = Triangle.java
SRCS_BA = BAModel.java

all: compile test

compile: $(SRCS_WS) $(SRCS_RND) $(SRCS_DC) $(SRCS_BA)
	$(JAVAC) $(SRCS_WS) $(SRCS_RND) $(SRCS_DC) $(SRCS_TR) $(SRCS_BA)

test: $(PROGRAM_WS) $(PROGRAM_RND) $(PROGRAM_DC) $(PROGRAM_TR) $(PROGRAM_BA)
	@./test.sh

.PHONY: clean
clean:
	$(RM) $(PROGRAM_WS) $(PROGRAM_RND) $(PROGRAM_DC) $(PROGRAM_TR) $(PROGRAM_BA) *.csv *.pdf