package think.in.java.chapter10;

interface Game { boolean move();}
interface GameFactory{ Game getGame();}


class Checkers implements Game{
    private Checkers(){}

    private int moves = 0;

    private static final int MOVES = 3;

    @Override
    public boolean move() {
        System.out.println("Checkers move " + moves);
        return ++moves != MOVES;
    }

    // 嵌套类构造 单例模式
    public static GameFactory factory = new GameFactory() {
        @Override
        public Game getGame() {
            return new Checkers();
        }
    };
}

class Chess implements Game{
    private Chess(){}

    int moves = 0;
    private static final int MOVES = 4;
    @Override
    public boolean move() {
        System.out.println("Chess move " + moves);
        return ++ moves != MOVES;
    }

    public static GameFactory factory = new GameFactory() {
        @Override
        public Game getGame() {
            return new Chess();
        }
    };
}

public class Games {

    public static void playGame(GameFactory factory){
        Game game = factory.getGame();
        while (game.move()){
            ;
        }

    }

    public static void main(String[] args) {
        playGame(Chess.factory);
        playGame(Checkers.factory);
    }
}
