JFLAGS = -g
JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
    AIPlayer.java \
	Player.java \
	Board.java \
	GameHeuristics.java
	PLGame.java \
	LadderPatternStrategy.java \
	Main.java \
	MiniMaxAIPlayer.java \
	Position.java \
	SearchLists.java \
	WinDetectionStrategy.java \
	Node.java \
	Tree.java \
	TreeInterface.java 



default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class